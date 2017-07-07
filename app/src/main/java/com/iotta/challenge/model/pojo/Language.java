package com.iotta.challenge.model.pojo;

/**
 * Created by Galya on 07/07/2017.
 */

public class Language {

    private String mName;
    private Long   mId;

    public Language(String name, Long id) {
        this.mName = name;
        this.mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long id) {
        this.mId = id;
    }
}
