package com.example.administrator.basdf;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;

/**
 * Created by Administrator on 2016-05-04.
 */
public class CreateConnectionActivity extends Activity {

    private WifiP2pManager manager;
    private Channel channel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_connection);

        manager = (WifiP2pManager)getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);

    }
}
