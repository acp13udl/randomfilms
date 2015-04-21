package com.udl.softarch.springexample.models;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Allu on 20/04/2015.
 */
@Entity
public class RecommendedList {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Film> recommendedList = new ArrayList<>();

    public RecommendedList() {
    }

    public RecommendedList(Date date, List<Film> recommendedList) {
        this.date = date;
        this.recommendedList = recommendedList;
    }

    public Long getId() {
        return Id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Film> getRecommendedList() {
        return recommendedList;
    }

    public void setRecommendedList(List<Film> recommendedList) {
        this.recommendedList = recommendedList;
    }
}