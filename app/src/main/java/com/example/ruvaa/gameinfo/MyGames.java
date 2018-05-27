package com.example.ruvaa.gameinfo;

import android.arch.core.executor.TaskExecutor;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonToken;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.igdb.api_android_java.callback.onSuccessCallback;
import com.igdb.api_android_java.model.APIWrapper;
import com.igdb.api_android_java.model.Parameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyGames extends AppCompatActivity {

    public static ArrayList<String> mOwnedGameIDs = new ArrayList<>();
    private ArrayList<String> mGameTitles = new ArrayList<>();
    private ArrayList<String> mGameCovers = new ArrayList<>();
    private static String mOwnedIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_games);

        //Recycle View Test
//        mGameCovers.add("https://images.igdb.com/igdb/image/upload/t_cover_big/tri1c6vbydeosoqajwt1.jpg");
//        mGameTitles.add("Witcher 3: Wild Hunt");
//        mGameCovers.add("https://images.igdb.com/igdb/image/upload/t_cover_big/fen88hu0vhcf3k3owkxd.jpg");
//        mGameTitles.add("Overwatch");
//        mOwnedIds = "1234";
//        mOwnedIds += ",18472";
        mOwnedIds =  returnIDsToSearch();
        showGamesByIds(mOwnedIds);
        //Log.d("owned games", "onCreate: owned game: " + gameData.getTitle());
        //new things for API

    }

    private void showGamesByIds(String IDs){
        APIWrapper wrapper = new APIWrapper(this, "49147002af71997ed8b447357755a07b");
        Parameters params = new Parameters().addFields("*").addIds(IDs);
        wrapper.games(params, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                new JSONObject();
                JSONObject urlJson;
                String gameTitle; //game title
                String gameCoverURL; //game cover
                try {
                    int arraySize = jsonArray.length();
                    for (int i=0; i<arraySize; i++){
                        gameTitle = jsonArray.getJSONObject(i).getString("name");
//                        urlJson = jsonArray.getJSONObject(i).getJSONObject("cover");
//                        String url = urlJson.getString("url");
                        gameCoverURL = jsonArray.getJSONObject(i).getJSONObject("cover").getString("url");
                        mGameTitles.add(gameTitle);
                        gameCoverURL = ("https:" + gameCoverURL);
                        mGameCovers.add(gameCoverURL);
                    }
//                    urlJson = jsonArray.getJSONObject(0).getJSONObject("cover");
//                    String url = urlJson.getString("url");
                    Log.d("mGameCovers", "onSuccess: size = " + mGameCovers.size());
//                    Log.d("url", "onSuccess: " + mGameCovers.get(3));
//                    Log.d("url", "onSuccess: " + mGameCovers.get(1));
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
        RecyclerView recyclerView = findViewById(R.id.myGameList);
        RecyclerViewAdapter adapter  = new RecyclerViewAdapter(mGameTitles, mGameCovers, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private String returnIDsToSearch(){
        String ids = "0";
        int gamesOwned = mOwnedGameIDs.size();
        for (int i = 0; i<gamesOwned; i++){
            if (i==0) ids = mOwnedGameIDs.get(0);
            else ids += "," + mOwnedGameIDs.get(i);
        }
        return ids;
    }


    private void unusedGetURLBASIC(String IDs){
        APIWrapper wrapper = new APIWrapper(this, "49147002af71997ed8b447357755a07b");
        Parameters params = new Parameters().addFields("*").addIds(IDs);
        wrapper.games(params, new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                new JSONObject();
                JSONObject urlJson;
                String titleJson;
                try {
                    titleJson = jsonArray.getJSONObject(1).getString("name");
                    urlJson = jsonArray.getJSONObject(0).getJSONObject("cover");
                    int arraysize = jsonArray.length();
                    String url = urlJson.getString("url");
                    mGameCovers.add(url);
                    Log.d("url", "onSuccess: " + titleJson);
                    Log.d("arraySize", "onSuccess: " + arraysize);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(VolleyError volleyError) {
                Log.d("error", "onError: ");
            }
        });
        Log.d("url", "addGameById: " + mGameCovers.size());
    }
}


