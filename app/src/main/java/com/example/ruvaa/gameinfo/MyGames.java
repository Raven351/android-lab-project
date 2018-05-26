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

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MyGames extends AppCompatActivity {

    static ArrayList<Integer> mOwnedGameIds = new ArrayList<>();
    private ArrayList<String> mGameTitles = new ArrayList<>();
    private ArrayList<String> mGameCovers = new ArrayList<>();
    private String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("created", "onCreate: CREATED");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_games);

        mGameCovers.add("https://images.igdb.com/igdb/image/upload/t_cover_big/tri1c6vbydeosoqajwt1.jpg");
        mGameTitles.add("Witcher 3: Wild Hunt");
        mGameCovers.add("https://images.igdb.com/igdb/image/upload/t_cover_big/fen88hu0vhcf3k3owkxd.jpg");
        mGameTitles.add("Overwatch");

        initRecyclerView();
        //new things


    }



    public void addGameById(int id) throws InterruptedException {
            mGameCovers.add(apiGetCoverURL(id));
//        APIWrapper wrapper = new APIWrapper(this, "49147002af71997ed8b447357755a07b");
//        Parameters params = new Parameters().addFields("*").addIds(String.valueOf(id));
//        wrapper.games(params, new onSuccessCallback() {
//            @Override
//            public void onSuccess(JSONArray jsonArray) {
//                new JSONObject();
//                JSONObject urlJson;
//                try {
//                    urlJson = jsonArray.getJSONObject(0).getJSONObject("cover");
//                    String url = urlJson.getString("url");
//                    mGameCovers.add(url);
//                    Log.d("url", "onSuccess: url = " + url);
//                    Log.d("url added", "onCreate: " + mGameCovers.size());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(VolleyError volleyError) {
//
//            }
//        });
//        Log.d("url", "addGameById: " + mGameCovers.size());
    }


    public String apiGetCoverURL(int id) {



//        final String[] s = new String[1];
//        final CountDownLatch lock = new CountDownLatch(1);
//        APIWrapper wrapper = new APIWrapper(this, "49147002af71997ed8b447357755a07b");
//        Parameters params = new Parameters().addFields("*").addIds(String.valueOf(id));
//        wrapper.games(params, new onSuccessCallback() {
//            @Override
//            public void onSuccess(JSONArray jsonArray) {
//                new JSONObject();
//                JSONObject urlJson;
//                try {
//                    lock.countDown();
//                    urlJson = jsonArray.getJSONObject(0).getJSONObject("cover");
//                    String url = urlJson.getString("url");
//                    mGameCovers.add(url);
//                    Log.d("url", "onSuccess: url = " + url);
//                    Log.d("url added", "onCreate: " + mGameCovers.size());
//                    s[0] = url;
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onError(VolleyError volleyError) {
//
//            }
//        });
//        lock.await(70000, TimeUnit.MILLISECONDS);
//        Log.d("url", "addGameById: " + mGameCovers.size());
//        return s[0];
//    }
        return null;
    }
    //todo sprobowac jeszcze raz z klasa GameData

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.myGameList);
        RecyclerViewAdapter adapter  = new RecyclerViewAdapter(mGameTitles, mGameCovers, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}