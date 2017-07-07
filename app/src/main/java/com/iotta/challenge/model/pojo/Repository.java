package com.iotta.challenge.model.pojo;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.iotta.challenge.model.pojo.Language;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.iotta.challenge.model.pojo.Owner;

/**
 * Created by Galya on 05/07/2017.
 */

public class Repository{

    @SerializedName("owner")
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

    @SerializedName("languages_url")
    private String mLanguagesListUrl;

    //In order to get languages lig we need to execute get to languages_url
    private ArrayList<Language> mLanguages;


    public Repository(String mID, String mName, String mDescription, Date mLastUpdate, boolean mHasWiki,
                      boolean mHasFork, HashMap<String, Long> mLanguages, String avatarUrl, String ownerName, String blogUrl) {
        this.mOwner = new Owner(avatarUrl, ownerName, blogUrl);
        this.mID = mID;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mLastUpdate = mLastUpdate;
        this.mHasWiki = mHasWiki;
        this.mHasFork = mHasFork;
        setLanguages(mLanguages);
    }

    public Repository(Owner mOwner, String mID, String mName, String mDescription, Date mLastUpdate, boolean mHasWiki, boolean mHasFork, HashMap<String, Long> mLanguages) {
        this.mOwner = mOwner;
        this.mID = mID;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mLastUpdate = mLastUpdate;
        this.mHasWiki = mHasWiki;
        this.mHasFork = mHasFork;
        setLanguages(mLanguages);
    }

    public String getLanguagesListUrl() {
        return mLanguagesListUrl;
    }

    public void setLanguagesListUrl(String languagesListUrl) {
        this.mLanguagesListUrl = languagesListUrl;
    }

    public Owner getOwner() {
        if(mOwner == null){
            mOwner = new Owner();
        }
        return mOwner;
    }

    public void setOwner(Owner owner) {
        owner.setUpdated(true);
        this.mOwner = owner;
    }
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

    public ArrayList<Language> getLanguages() {
        if(mLanguages == null){
            mLanguages = new ArrayList<>();
        }
        return mLanguages;
    }

    public void setLanguages(HashMap<String, Long> languages) {
        this.mLanguages.clear();

        for (String languageKey: languages.keySet()) {
            addLanguage(languageKey, languages.get(languageKey));
        }
    }

    public void addLanguage(String  strLanguage, Long strId) {
        Language language = new Language(strLanguage, strId);
        if(this.mLanguages == null){
            mLanguages = new ArrayList<>();
        }
        this.mLanguages.add(language);
    }
}
