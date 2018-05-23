package com.example.ruvaa.gameinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchGame extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_game);
        TextView textView = findViewById(R.id.textView);
        String s = getIntent().getStringExtra("gameTitle");
        textView.setText(s);
    }
}
