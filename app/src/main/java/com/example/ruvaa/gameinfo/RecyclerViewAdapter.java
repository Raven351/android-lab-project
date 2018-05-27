package com.example.ruvaa.gameinfo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ruvaa on 2018-05-25.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapter"; //debuging
    private ArrayList<String> mGameTitles  = new ArrayList<>();
    private ArrayList<String> mGameCovers = new ArrayList<>();
    private ArrayList<String> mGameURL = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<String> mGameTitles, ArrayList<String> mGameCovers, ArrayList<String> mGameURL, Context mContext) { //constructor - gets title of the game and url of it's cover and makes an recycle view item
        //with them
        this.mGameTitles = mGameTitles;
        this.mGameCovers = mGameCovers;
        this.mGameURL = mGameURL;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {     //responsible for recycling and putting view holders into right positions
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_single_game_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    //todo dokonczyc
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {        //called every time a new item is added to recycle view
        Log.d(TAG, "onBindViewHolder: called");
        Glide.with(mContext).asDrawable().load(mGameCovers.get(position)).into(holder.cover);
        holder.title.setText(mGameTitles.get(position));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + mGameTitles.get(position));    //TODO onClick -> new Activity do szczegolow gry
                Uri uri = Uri.parse(mGameURL.get(position)); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mGameTitles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView cover;
        TextView title;
        RelativeLayout layout;
        public ViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.gameCover);
            title = itemView.findViewById(R.id.gameTitle);
            layout = itemView.findViewById(R.id.gameItemLayout);
        }
    }

}
