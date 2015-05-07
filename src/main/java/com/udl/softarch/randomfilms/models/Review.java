package com.udl.softarch.randomfilms.models;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by adrian on 5/5/15.
 */
@Entity
public class Review {

    @Id
    Long id;
    private String author;
    private String comment;
    private float  filmRating;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
