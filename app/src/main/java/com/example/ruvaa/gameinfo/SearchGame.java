package com.example.ruvaa.gameinfo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.onSuccessCallback;
import com.igdb.api_android_java.model.APIWrapper;
import com.igdb.api_android_java.model.Parameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SearchGame extends AppCompatActivity {

    private ArrayList<String> mGameTitles = new ArrayList<>();
    private ArrayList<String> mGameCovers = new ArrayList<>();
    private ArrayList<String> mGameIDs = new ArrayList<>();
    private ArrayList<String> mGameURL = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_game);
        String s = getIntent().getStringExtra("gameTitle");
        showGamesByIds(s);

    }

    private void showGamesByIds(String search){
        APIWrapper wrapper = new APIWrapper(this, "49147002af71997ed8b447357755a07b");
        Parameters params = new Parameters().addFields("*").addSearch(search);
        Log.d("search", "showGamesByIds: called " + search);
        wrapper.search( APIWrapper.Endpoint.GAMES, params, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                new JSONObject();
                JSONObject urlJson;
                String gameTitle; //game title
                String gameCoverURL; //game cover
                String gameID;
                String gameURL;
                try {
                    int arraySize = jsonArray.length();
                    for (int i=0; i<arraySize; i++){
                        gameTitle = jsonArray.getJSONObject(i).getString("name");
//                        urlJson = jsonArray.getJSONObject(i).getJSONObject("cover");
//                        String url = urlJson.getString("url");
                        try{
                            gameCoverURL = jsonArray.getJSONObject(i).getJSONObject("cover").getString("url");
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                            gameCoverURL = "https://images.igdb.com/igdb/image/upload/t_cover_big/nocover_qhhlj6.jpg";
                        }

                        gameID = jsonArray.getJSONObject(i).getString("id");
                        mGameTitles.add(gameTitle);
                        gameCoverURL = ("https:" + gameCoverURL);
                        mGameCovers.add(gameCoverURL);
                        mGameIDs.add(gameID);
                        gameURL = jsonArray.getJSONObject(i).getString("url");
                        mGameURL.add(gameURL);

                        Log.d("json length", "onSuccess: jsonsize: " +  jsonArray.length());

                    }
//                    urlJson = jsonArray.getJSONObject(0).getJSONObject("cover");
//                    String url = urlJson.getString("url");
                    Log.d("mGameCovers", "onSuccess: size = " + mGameCovers.size());
//                    Log.d("url", "onSuccess: " + mGameCovers.get(3));
//                    Log.d("url", "onSuccess: " + mGameCovers.get(1));
//                    initRecyclerView();
                    initRecyclerView();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.d("error", "onError: ");
            }
        });
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.searchList);
        RecyclerViewAdapterSearch adapter  = new RecyclerViewAdapterSearch(mGameTitles, mGameCovers, mGameIDs, mGameURL ,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
