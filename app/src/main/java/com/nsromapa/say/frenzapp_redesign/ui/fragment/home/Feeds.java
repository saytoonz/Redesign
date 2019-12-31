package com.nsromapa.say.frenzapp_redesign.ui.fragment.home;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hoanganhtuan95ptit.autoplayvideorecyclerview.AutoPlayVideoRecyclerView;
import com.nsromapa.say.frenzapp_redesign.R;
import com.nsromapa.say.frenzapp_redesign.adapters.PostsAdapter;
import com.nsromapa.say.frenzapp_redesign.models.Post;
import com.nsromapa.say.frenzapp_redesign.ui.view.CenterLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.nsromapa.say.frenzapp_redesign.utils.Constants.NEWS_FEEDS;


public class Feeds extends Fragment {
    private View mView;
    private SwipeRefreshLayout refreshLayout;
    private PostsAdapter mAdapter_v19;
    AutoPlayVideoRecyclerView mPostsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView  = inflater.inflate(R.layout.fragment_feeds, container, false);
        refreshLayout = mView.findViewById(R.id.refreshLayout);
        mPostsRecyclerView = mView.findViewById(R.id.posts_recyclerview);
        mAdapter_v19 = new PostsAdapter(getActivity());
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refreshLayout.setOnRefreshListener(() -> {
            mAdapter_v19.notifyDataSetChanged();
            getAllPosts();
        });

        mPostsRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mPostsRecyclerView.setLayoutManager(new CenterLayoutManager(getActivity()));
        mPostsRecyclerView.setHasFixedSize(true);
        mPostsRecyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        mPostsRecyclerView.setAdapter(mAdapter_v19);

        getAllPosts();
    }

    private void getAllPosts() {

        mView.findViewById(R.id.default_item).setVisibility(View.GONE);
        mView.findViewById(R.id.error_view).setVisibility(View.GONE);
        refreshLayout.setRefreshing(true);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, NEWS_FEEDS,
                response -> {
                    refreshLayout.setRefreshing(false);
                    Log.e("Volley Result", "" + response);

                    try {
                        JSONObject jsonObject  = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Posts");

                        if (jsonArray.length()>0){
                            mView.findViewById(R.id.default_item).setVisibility(View.GONE);
                            mView.findViewById(R.id.error_view).setVisibility(View.GONE);

                            for (int i = 0 ; i < jsonArray.length(); i++){
                                JSONObject coming_post = jsonArray.getJSONObject(i);
                                JSONObject user_info = coming_post.getJSONObject("1");
                                mAdapter_v19.add(new Post(
                                        coming_post.getString("id"),
                                        coming_post.getString("user_id"),
                                        coming_post.getString("0"),
                                        "50",
                                        "",
                                        coming_post.getString("description"),
                                        coming_post.getString("color"),
                                        user_info.getString("username"),
                                        user_info.getString("image"),
                                        coming_post.getString("post_type"),
                                        coming_post.getString("image_urls"),
                                        coming_post.toString()
                                ));
                                mAdapter_v19.notifyDataSetChanged();
                            }
                            mAdapter_v19.notifyDataSetChanged();
                            refreshLayout.setRefreshing(false);
                        }else{
                            mView.findViewById(R.id.default_item).setVisibility(View.VISIBLE);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        mView.findViewById(R.id.error_view).setVisibility(View.VISIBLE);
                    }

                    mAdapter_v19.notifyDataSetChanged();
                },
                error -> {
                    refreshLayout.setRefreshing(false);
                    mView.findViewById(R.id.default_item).setVisibility(View.GONE);
                    mView.findViewById(R.id.error_view).setVisibility(View.VISIBLE);
                    TextView error_textV = mView.findViewById(R.id.error_view).findViewById(R.id.error_text);
                    error_textV.setText(Objects.requireNonNull(getActivity()).getString(R.string.server_connection_error));
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> postMap = new HashMap<>();
                postMap.put("user_id", "1");
//                postMap.put("load", String.valueOf(loadCount));
                return postMap;
            }
        };


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            Volley.newRequestQueue(Objects.requireNonNull(getContext())).add(stringRequest);
        else
            Volley.newRequestQueue(Objects.requireNonNull(getActivity())).add(stringRequest);

    }

    @Override
    public void onAttachFragment(@NonNull Fragment childFragment) {
        super.onAttachFragment(childFragment);
        getAllPosts();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPostsRecyclerView.getHandingVideoHolder() != null) mPostsRecyclerView.getHandingVideoHolder().playVideo();
        mAdapter_v19.notifyDataSetChanged();
    }
    @Override
    public void onPause() {
        super.onPause();
        if (mPostsRecyclerView.getHandingVideoHolder() != null) mPostsRecyclerView.getHandingVideoHolder().stopVideo();
    }
}
