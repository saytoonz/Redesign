package com.nsromapa.say.frenzapp_redesign.models;


public class StoryStatus {
    String storyId;
    String posterId;
    String posterName;
    String posterImage;
    String stories;
    String postedTime;
    String endingTime;


    public StoryStatus(String storyId, String posterId, String posterName,
                       String posterImage, String stories, String postedTime,
                       String endingTime) {
        this.storyId = storyId;
        this.posterId = posterId;
        this.posterName = posterName;
        this.posterImage = posterImage;
        this.stories = stories;
        this.postedTime = postedTime;
        this.endingTime = endingTime;
    }


    public String getStoryId() {
        return storyId;
    }

    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }

    public String getPosterId() {
        return posterId;
    }

    public void setPosterId(String posterId) {
        this.posterId = posterId;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getStories() {
        return stories;
    }

    public void setStories(String stories) {
        this.stories = stories;
    }

    public String getPostedTime() {
        return postedTime;
    }

    public void setPostedTime(String postedTime) {
        this.postedTime = postedTime;
    }

    public String getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(String endingTime) {
        this.endingTime = endingTime;
    }
}
