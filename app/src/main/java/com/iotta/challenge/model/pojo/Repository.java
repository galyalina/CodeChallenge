package com.iotta.challenge.model.pojo;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.iotta.challenge.model.pojo.Language;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.iotta.challenge.model.pojo.Owner;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Created by Galya on 08/07/2017.
 */

public class Repository implements Comparable {

    @SerializedName("owner")
    private Owner mOwner;

    @SerializedName("id")
    private String mID;

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("updated_at")
    private Date mLastUpdate;

    @SerializedName("has_wiki")
    private boolean mHasWiki;

    @SerializedName("forks_count")
    private int mForkCount;

    @SerializedName("languages_url")
    private String mLanguagesListUrl;

    //In order to get languages lig we need to execute get to languages_url
    private ArrayList<Language> mLanguages;


    public Repository(String mID, String mName, String mDescription, Date mLastUpdate, boolean mHasWiki,
                      int forkCount, HashMap<String, Long> mLanguages, String avatarUrl, String ownerName, String email, String blogUrl) {
        this.mOwner = new Owner(avatarUrl, ownerName, blogUrl, email);
        this.mID = mID;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mLastUpdate = mLastUpdate;
        this.mHasWiki = mHasWiki;
        this.mForkCount = forkCount;
        setLanguages(mLanguages);
    }

    public Repository(Owner mOwner, String mID, String mName, String mDescription, Date mLastUpdate, boolean mHasWiki, int forkCount, HashMap<String, Long> mLanguages) {
        this.mOwner = mOwner;
        this.mID = mID;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mLastUpdate = mLastUpdate;
        this.mHasWiki = mHasWiki;
        this.mForkCount = forkCount;
        setLanguages(mLanguages);
    }

    public String getLanguagesListUrl() {
        return mLanguagesListUrl;
    }

    public void setLanguagesListUrl(String languagesListUrl) {
        this.mLanguagesListUrl = languagesListUrl;
    }

    public Owner getOwner() {
        if (mOwner == null) {
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

    public boolean hasFork() {
        return mForkCount > 0;
    }

    public ArrayList<Language> getLanguages() {
        if (mLanguages == null) {
            mLanguages = new ArrayList<>();
        }
        return mLanguages;
    }

    public void setLanguages(HashMap<String, Long> languages) {

        if (this.mLanguages != null) {
            this.mLanguages.clear();
        }
        for (String languageKey : languages.keySet()) {
            addLanguage(languageKey, languages.get(languageKey));
        }
    }

    public void addLanguage(String strLanguage, Long strId) {
        Language language = new Language(strLanguage, strId);
        if (this.mLanguages == null) {
            mLanguages = new ArrayList<>();
        }
        this.mLanguages.add(language);
    }


    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof Repository)) {
            return false;
        }

        Repository other = (Repository) obj;
        EqualsBuilder equalsBuilder = new EqualsBuilder().append(mID, other.mID);

        equalsBuilder.append(mOwner, other.mOwner)
                .append(mID, other.mID)
                .append(mName, other.mName).append(mDescription, other.mDescription)
                .append(mLastUpdate, other.mLastUpdate)
                .append(mHasWiki, other.mHasWiki)
                .append(mForkCount, other.mForkCount)
                .append(mLanguagesListUrl, other.mLanguagesListUrl)
                .append(mLanguages, other.mLanguages);

        return equalsBuilder.isEquals();
    }


    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(mOwner)
                .append(mID)
                .append(mName)
                .append(mLastUpdate)
                .append(mHasWiki)
                .append(mForkCount)
                .append(mLanguagesListUrl)
                .append(mLanguages).toHashCode();
    }

    @Override
    public int compareTo(@NonNull Object obj) {
        Repository compareTo = (Repository) obj;

        return compareTo.getLastUpdate().compareTo(mLastUpdate);
    }
}
