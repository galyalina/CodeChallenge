package app.challenge.iotta.codechallenge.model.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

import app.challenge.iotta.codechallenge.model.pojo.Owner;

/**
 * Created by Galya on 05/07/2017.
 */

public class Repository {

    public Repository(String mID, String mName, String mDescription, Date mLastUpdate, boolean mHasWiki,
                      boolean mHasFork, ArrayList<String> mLanguages, String avatarUrl, String ownerName, String blogUrl) {
        this.mOwner = new Owner(avatarUrl, ownerName, blogUrl);
        this.mID = mID;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mLastUpdate = mLastUpdate;
        this.mHasWiki = mHasWiki;
        this.mHasFork = mHasFork;
        this.mLanguages = mLanguages;
    }

    public Repository(Owner mOwner, String mID, String mName, String mDescription, Date mLastUpdate, boolean mHasWiki, boolean mHasFork, ArrayList<String> mLanguages) {
        this.mOwner = mOwner;
        this.mID = mID;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mLastUpdate = mLastUpdate;
        this.mHasWiki = mHasWiki;
        this.mHasFork = mHasFork;
        this.mLanguages = mLanguages;
    }

    private Owner mOwner;

    @SerializedName("id")
    private String  mID;

    @SerializedName("name")
    private String  mName;

    @SerializedName("description")
    private String  mDescription;

    @SerializedName("updated_at")
    private Date mLastUpdate;

    @SerializedName("has_wiki")
    private boolean mHasWiki;

    @SerializedName("fork")
    private boolean mHasFork;

    //In order to get languages lig we need to execute get to languages_url
    private ArrayList<String> mLanguages;

    public String getId() {
        return mID;
    }

    public void setId(String mID) {
        this.mID = mID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String strDescription) {
        this.mDescription = mDescription;
    }

    public Date getLastUpdate() {
        return mLastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.mLastUpdate = mLastUpdate;
    }

    public boolean hasWiki() {
        return mHasWiki;
    }

    public void setHasWiki(boolean hasWiki) {
        this.mHasWiki = mHasWiki;
    }

    public boolean hasFork() {
        return mHasFork;
    }

    public void setHasFork(boolean hasFork) {
        this.mHasFork = mHasFork;
    }

    public ArrayList<String> getLanguages() {
        return mLanguages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.mLanguages = mLanguages;
    }

    public void addLanguage(String  strLanguage) {
        this.mLanguages.add(strLanguage);
    }
}
