package com.iotta.challenge.model.pojo;

import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Galya on 08/07/2017.
 */

public class Owner {

    private boolean isUpdated = false;

    @SerializedName("avatar_url")
    private String mAvatarUrl;

    @SerializedName("name")
    private String mName;

    @SerializedName("url")
    private String mBlogUrl;

    //In order to get email we need to execute get to mBlogUrl
    @SerializedName("email")
    private String mEmail;

    public Owner() {
    }

    public Owner(String mAvatarUrl, String mName, String mBlogUrl, String strEmail) {
        this.mAvatarUrl = mAvatarUrl;
        this.mName = mName;
        this.mBlogUrl = mBlogUrl;
        this.mEmail = strEmail;
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

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getmBlogUrl() {
        return mBlogUrl;
    }

    public String getEmail() {
        return mEmail;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Owner)) {
            return false;
        }

        Owner objOwner = (Owner) obj;

        return new EqualsBuilder().
                append(mAvatarUrl, objOwner.mAvatarUrl).
                append(mName, objOwner.mName).
                append(mBlogUrl, objOwner.mBlogUrl).
                append(mEmail, objOwner.mEmail).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
                append(mAvatarUrl).
                append(mName).
                append(mBlogUrl).
                append(mEmail).toHashCode();
    }
}
