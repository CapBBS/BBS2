package com.example.administrator.basdf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Administrator on 2016-05-04.
 */
public class MusicPlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_play);

        Toast.makeText(this, "음악을 재생할 Activity", Toast.LENGTH_SHORT).show();
        Intent fileBrowserIntent = new Intent(this, FileBrowser.class);
        startActivityForResult(fileBrowserIntent, Constants.FILE_REQUEST);
    }
}
