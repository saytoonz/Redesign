package com.nsromapa.say.frenzapp_redesign.ui.fragment.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nsromapa.say.frenzapp_redesign.R;
import com.nsromapa.say.frenzapp_redesign.adapters.StoryStatusAdapter;
import com.nsromapa.say.frenzapp_redesign.models.StoryStatus;

import java.util.ArrayList;
import java.util.List;


public class Stories extends Fragment {

    private List<StoryStatus> statusList;
    private StoryStatusAdapter statusAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stories, container, false);
        statusList = new ArrayList<>();
        RecyclerView storyRecycler = view.findViewById(R.id.story_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        statusAdapter = new StoryStatusAdapter(statusList, container.getContext(), getActivity());
        storyRecycler.setLayoutManager(linearLayoutManager);
        storyRecycler.setHasFixedSize(true);
        storyRecycler.setAdapter(statusAdapter);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getStatus();
    }

    private void getStatus() {

        statusList.add(new StoryStatus("1","2","Saytoonz 05",
                "https://www.planwallpaper.com/static/images/animals-4.jpg",
                "https://www.planwallpaper.com/static/images/animals-4.jpg",
                "now()","24hrs ago"));

        statusList.add(new StoryStatus("1","1","Saytoonz 05",
                "https://pbs.twimg.com/profile_images/990460935046230016/k__sGK8z_400x400.jpg",
                "https://pbs.twimg.com/profile_images/990460935046230016/k__sGK8z_400x400.jpg",
                "now()","24hrs ago"));

        statusList.add(new StoryStatus("1","1","Saytoonz 05",
                "https://pbs.twimg.com/profile_images/990460935046230016/k__sGK8z_400x400.jpg",
                "https://pbs.twimg.com/profile_images/990460935046230016/k__sGK8z_400x400.jpg",
                "now()","24hrs ago"));

        statusList.add(new StoryStatus("1","1","Saytoonz 05",
                "https://pbs.twimg.com/profile_images/990460935046230016/k__sGK8z_400x400.jpg",
                "https://pbs.twimg.com/profile_images/990460935046230016/k__sGK8z_400x400.jpg",
                "now()","24hrs ago"));

        statusList.add(new StoryStatus("1","1","Saytoonz 05",
                "https://pbs.twimg.com/profile_images/990460935046230016/k__sGK8z_400x400.jpg",
                "https://pbs.twimg.com/profile_images/990460935046230016/k__sGK8z_400x400.jpg",
                "now()","24hrs ago"));

        statusAdapter.notifyDataSetChanged();
    }
}
