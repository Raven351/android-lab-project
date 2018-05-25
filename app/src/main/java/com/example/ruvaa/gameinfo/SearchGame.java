package com.example.ruvaa.gameinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.igdb.api_android_java.model.APIWrapper;

public class SearchGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_game);
        String s = getIntent().getStringExtra("gameTitle");
        APIWrapper wrapper = new APIWrapper(this, "49147002af71997ed8b447357755a07b");
    }
}
