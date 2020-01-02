package com.nsromapa.say.frenzapp_redesign.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nsromapa.say.frenzapp_redesign.R;
import com.nsromapa.say.frenzapp_redesign.models.StoryStatus;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import xute.storyview.StoryModel;
import xute.storyview.StoryView;

public class StoryStatusAdapter extends RecyclerView.Adapter<StoryStatusAdapter.ViewHolder> {
    private List<StoryStatus> statusList;
    private Context context;
    private Activity activity;


    public StoryStatusAdapter(List<StoryStatus> statusList, Context context, Activity activity) {
        this.statusList = statusList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public StoryStatusAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(context).inflate(R.layout.item_status, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoryStatusAdapter.ViewHolder holder, int position) {
        StoryStatus list = statusList.get(position);

        Glide.with(context)
                .load(list.getStories())
                .into(holder.first_stat);
        if (list.getPosterId().equals("2")){
            holder.storyView.setVisibility(View.GONE);
            holder.add_new_story.show();
            holder.add_new_story.setOnClickListener(v -> {
                Toasty.success(context, "Create new Story").show();
            });
        }else{
            holder.storyView.setVisibility(View.VISIBLE);
            holder.add_new_story.hide();
            holder.storyView.resetStoryVisits();
            ArrayList<StoryModel> uris = new ArrayList<>();
            uris.add(new StoryModel("https://www.planwallpaper.com/static/images/animals-4.jpg",
                    list.getPosterName(), list.getPostedTime()));
            uris.add(new StoryModel("https://static.boredpanda.com/blog/wp-content/uuuploads/albino-animals/albino-animals-3.jpg","Grambon","10:15 PM"));

            holder.storyView.setImageUris(uris);
        }

    }

    @Override
    public int getItemCount() {
        return statusList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        StoryView storyView;
        FloatingActionButton add_new_story;
        ImageView first_stat;
        TextView poster_info;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storyView = itemView.findViewById(R.id.storyView);
            add_new_story = itemView.findViewById(R.id.add_new_story);
            first_stat = itemView.findViewById(R.id.first_stat);
            poster_info = itemView.findViewById(R.id.poster_info);
        }
    }
}
