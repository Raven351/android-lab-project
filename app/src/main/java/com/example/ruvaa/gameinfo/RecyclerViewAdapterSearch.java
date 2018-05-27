package com.example.ruvaa.gameinfo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by ruvaa on 2018-05-25.
 */

public class RecyclerViewAdapterSearch extends RecyclerView.Adapter<RecyclerViewAdapterSearch.ViewHolder>{

    private static final String TAG = "RecyclerViewAdapterSearch"; //debuging
    private ArrayList<String> mGameTitles  = new ArrayList<>();
    private ArrayList<String> mGameCovers = new ArrayList<>();
    private ArrayList<String> mid = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterSearch(ArrayList<String> mGameTitles, ArrayList<String> mGameCovers,  ArrayList<String> mid, Context mContext) { //constructor - gets title of the game and url of it's cover and makes a recycle view item
        //with them
        this.mGameTitles = mGameTitles;
        this.mGameCovers = mGameCovers;
        this.mid = mid;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {     //responsible for recycling and putting view holders into right positions
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_single_game_list_search, parent, false);
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

            }
        });
        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on ADD ID: "+ mid.get(position));
                MyGames.mOwnedGameIDs.add(mid.get(position)); //onClick Listener for add button - adds ID of the game that has been passed from constructor
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
        Button addButton;
        public ViewHolder(View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.gameCover);
            title = itemView.findViewById(R.id.gameTitle);
            layout = itemView.findViewById(R.id.gameItemLayout);
            addButton = itemView.findViewById(R.id.add);
        }
    }

}
