package com.example.ruvaa.gameinfo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText gameTextBox = findViewById(R.id.editTextGameTitle);
        final Button searchButton = findViewById(R.id.buttonSearch);
        MyGames.mOwnedGameIDs.add("1234");
        MyGames.mOwnedGameIDs.add("18472");

        gameTextBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { //sprawia ze przycisk staje sie widoczny gdy w pasku wyszukiwania wpisany jest tekst
                if (charSequence.length() != 0) {
                    searchButton.setVisibility(View.VISIBLE);
                }
                else searchButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void openMyGames(View view) {
        Intent intent = new Intent(this, MyGames.class);
        startActivity(intent);
    }

    public void openWishlist(View view){
        Intent intent = new Intent(this, Wishlist.class);
        startActivity(intent);
    }

    public void openSearchGame(View view){
        EditText gameTextBox = findViewById(R.id.editTextGameTitle);
        Intent intent = new Intent(this, SearchGame.class);
        String gameTitle = gameTextBox.getText().toString();
        intent.putExtra("gameTitle", gameTitle);
        startActivity(intent);
    }

}
