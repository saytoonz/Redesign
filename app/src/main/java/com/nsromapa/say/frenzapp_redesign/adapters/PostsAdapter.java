package com.nsromapa.say.frenzapp_redesign.adapters;


import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.hoanganhtuan95ptit.autoplayvideorecyclerview.VideoHolder;
import com.nsromapa.say.frenzapp_redesign.R;
import com.nsromapa.say.frenzapp_redesign.models.MultipleVideos;
import com.nsromapa.say.frenzapp_redesign.models.Post;
import com.nsromapa.say.frenzapp_redesign.ui.view.CameraAnimation;
import com.nsromapa.say.frenzapp_redesign.ui.view.FrenzAppImageView;
import com.nsromapa.say.frenzapp_redesign.ui.view.FrenzAppVideoView;
import com.squareup.picasso.Picasso;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.grantland.widget.AutofitTextView;

public class PostsAdapter extends BaseAdapter<Post> {


    private static final int IMAGES_ONLY = 0;
    private static final int TEXT_ONLY = 1;
    private static final int VIDEO_ONLY = 2;
    private static final int VIDEO_IMAGES = 3;

    private static int screenWight = 0;

    public PostsAdapter(Activity activity) {
        super(activity);
        screenWight = getScreenWight();
    }

    @Override
    public int getItemViewType(int position) {
        Post posts = list.get(position);
        switch (posts.getPost_type()) {
            case "images_only":
                return IMAGES_ONLY;
            case "videos_only":
                return VIDEO_ONLY;
            case "videos_image":
                return VIDEO_IMAGES;
            default:
                return TEXT_ONLY;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case VIDEO_ONLY:
                view = activity.getLayoutInflater().inflate(R.layout.item_viewpager_video, parent, false);
                return new Video1Holder(view);
            default:
                view = activity.getLayoutInflater().inflate(R.layout.item_feed_post, parent, false);
                return new ImageTextHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Post post = list.get(position);
        if (holder instanceof Video1Holder) {
            onBindVideoHolder((Video1Holder) holder, post);
        } else if (holder instanceof ImageTextHolder) {
            onBindImageTextHolder((ImageTextHolder) holder, post);
        }
    }

    private void onBindImageTextHolder(ImageTextHolder holder, Post post) {

    }


    public static class ImageTextHolder extends RecyclerView.ViewHolder {
        private View mView;
        private CircleImageView user_image;
        private TextView user_name, timestamp, post_desc;
        private MaterialFavoriteButton sav_button, like_btn, share_btn, comment_btn, stat_btn;
        private FrameLayout mImageholder;
        private FrameLayout pager_layout;
        private RelativeLayout indicator_holder;
        private AutofitTextView post_text;
        private ImageView delete;
        private ViewPager pager;
        private View vBgLike;
        private ImageView ivLike;
        private DotsIndicator indicator2;

        ImageTextHolder(View itemView) {
            super(itemView);
            mView = itemView;
            user_image = mView.findViewById(R.id.post_user_image);
            like_btn = mView.findViewById(R.id.like_button);
            vBgLike = mView.findViewById(R.id.vBgLike);
            ivLike = mView.findViewById(R.id.ivLike);
            stat_btn = mView.findViewById(R.id.stat_button);
            user_name = mView.findViewById(R.id.post_username);
            timestamp = mView.findViewById(R.id.post_timestamp);
            post_desc = mView.findViewById(R.id.post_desc);
            post_text = mView.findViewById(R.id.post_text);
            pager = mView.findViewById(R.id.pager);
            pager_layout = mView.findViewById(R.id.pager_layout);
            comment_btn = mView.findViewById(R.id.comment_button);
            share_btn = mView.findViewById(R.id.share_button);
            delete = mView.findViewById(R.id.delete_button);
            sav_button = mView.findViewById(R.id.save_button);
            mImageholder = mView.findViewById(R.id.image_holder);
            indicator2 = mView.findViewById(R.id.indicator);
            indicator_holder = mView.findViewById(R.id.indicator_holder);
        }

    }




    public static class Video1Holder extends DemoVideoHolder {

        Video1Holder(View itemView) {
            super(itemView);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) vvInfo.getLayoutParams();
            layoutParams.width = screenWight;
            layoutParams.height = screenWight * 9 / 16;
            vvInfo.setLayoutParams(layoutParams);
        }

    }

    public static class DemoVideoHolder extends VideoHolder {
        FrenzAppVideoView vvInfo;
        FrenzAppImageView ivInfo;
        CameraAnimation ivCameraAnimation;

        DemoVideoHolder(View itemView) {
            super(itemView);
            vvInfo  = itemView.findViewById(R.id.video_view);
            ivInfo  = itemView.findViewById(R.id.videoImage_view);
            ivCameraAnimation  = itemView.findViewById(R.id.ivCameraAnimation);
        }

        @Override
        public View getVideoLayout() {
            return vvInfo;
        }

        @Override
        public void playVideo() {
            ivInfo.setVisibility(View.VISIBLE);
            ivCameraAnimation.start();
            vvInfo.play(() -> {
                ivInfo.setVisibility(View.GONE);
                ivCameraAnimation.stop();
            });
        }

        @Override
        public void stopVideo() {
            ivInfo.setVisibility(View.VISIBLE);
            ivCameraAnimation.stop();
            vvInfo.stop();
        }
    }




    private void onBindVideoHolder(final DemoVideoHolder holder, Post post) {
        holder.vvInfo.setVideo(new MultipleVideos(post.getImages_url(),0));

        Glide.with(activity)
                .load(post.getImages_url())
                .apply(new RequestOptions().centerCrop()
                        .override(screenWight,screenWight))
                .into(holder.ivInfo);
    }



    private int getScreenWight() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
}
