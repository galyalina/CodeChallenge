package com.iotta.challenge.model.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Galya on 05/07/2017.
 */

public class Owner {

    private boolean isUpdated = false;

    @SerializedName("avatar_url")
    String mAvatarUrl;

    @SerializedName("name")
    String mName;

    @SerializedName("url")
    String mBlogUrl;

    //In order to get email we need to execute get to mBlogUrl
    @SerializedName("email")
    String mEmail;

    public Owner() {
        this.mAvatarUrl = "";
        this.mName = "";
        this.mBlogUrl = "";
    }


    public Owner(String mAvatarUrl, String mName, String mBlogUrl) {
        this.mAvatarUrl = mAvatarUrl;
        this.mName = mName;
        this.mBlogUrl = mBlogUrl;
    }

    public boolean isUpdated() {
        return isUpdated;
    }

    public void setUpdated(boolean updated) {
        isUpdated = updated;
    }

    public String getmAvatarUrl() {
        return mAvatarUrl;
    }

    public void setmAvatarUrl(String mAvatarUrl) {
        this.mAvatarUrl = mAvatarUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getmBlogUrl() {
        return mBlogUrl;
    }

    public void setmBlogUrl(String blogUrl) {
        this.mBlogUrl = blogUrl;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }


}
