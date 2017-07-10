package com.iotta.challenge.model.pojo;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Objects;

/**
 * Created by Galya on 08/07/2017.
 */

public class Language {

    private String mName;
    private Long mId;

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


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Language)) {
            return false;
        }
        return new EqualsBuilder().append(mId, ((Language) (obj)).mId).append(mName, ((Language) (obj)).mName).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(mId)
                .append(mName).toHashCode();
    }
}
