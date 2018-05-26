package com.example.ruvaa.gameinfo;

import android.content.Context;
import android.drm.DrmStore;
import android.util.Log;

import com.android.volley.VolleyError;
import com.igdb.api_android_java.callback.onSuccessCallback;
import com.igdb.api_android_java.model.APIWrapper;
import com.igdb.api_android_java.model.Parameters;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Wrapper;
import java.util.concurrent.BlockingQueue;

import static android.content.ContentValues.TAG;

/**
 * Created by ruvaa on 2018-05-25.
 */

public class GameData {
    String title;
    String coverUrl;
    JSONArray jsonArray;


    public GameData(int id, Context context, String url) {
        APIWrapper wrapper = new APIWrapper(context, "49147002af71997ed8b447357755a07b");
        Parameters params = new Parameters().addFields("*").addIds(String.valueOf(id));
        onSuccessCallback osc = new onSuccessCallback() {
            @Override
            public void onSuccess(JSONArray jsonArray) {
                GameData.this.jsonArray = jsonArray;
                Log.d(TAG, "onSuccess: " + jsonArray);
            }

            @Override
            public void onError(VolleyError volleyError) {

            }
        };
        wrapper.games(params, osc);
        Log.d(TAG, "GameData: " + jsonArray);
    }
}
