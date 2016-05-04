package com.example.administrator.basdf;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pDevice;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-05-04.
 */
public class CreateConnectionActivity extends Activity {

    private WifiP2pManager manager;
    private Channel channel;
    private IntentFilter wifiP2pIntentFilter;
    private WifiBroadcastReceiver wifiBroadcastReceiver;
    private ListView peerDeviceListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_connection);

        wifiP2pIntentFilter = new IntentFilter();
        wifiP2pIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        wifiP2pIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        wifiP2pIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        wifiP2pIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        manager = (WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);

        manager.discoverPeers(channel, null);

        peerDeviceListView = (ListView)findViewById(R.id.peer_list);

    }

    @Override
    protected void onResume() {
        super.onResume();
        wifiBroadcastReceiver = new WifiBroadcastReceiver(manager, channel, this);
        this.registerReceiver(wifiBroadcastReceiver, wifiP2pIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        this.unregisterReceiver(wifiBroadcastReceiver);
    }

    protected void displayPeers(WifiP2pDeviceList wifiP2pDeviceList) {
        ArrayList peerDeviceNameList = new ArrayList();
        for(WifiP2pDevice device : wifiP2pDeviceList.getDeviceList()) {
            peerDeviceNameList.add(device.deviceName);
        }
        ArrayAdapter peerNameData = new ArrayAdapter(this, android.R.layout.simple_list_item_1,peerDeviceNameList);
        peerDeviceListView.setAdapter(peerNameData);
        Toast.makeText(this, "피어를 찾음.",Toast.LENGTH_SHORT).show();
    }
}
