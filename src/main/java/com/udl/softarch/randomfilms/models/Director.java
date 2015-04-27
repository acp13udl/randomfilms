package com.udl.softarch.randomfilms.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
@Entity
public class Director extends PersonInvolved{

    @ManyToMany(mappedBy = "directors")
    private List<Film> films = new ArrayList<>();

    @Override
    public boolean isDirector() {
        return true;
    }

    @Override
    public boolean isActor() {
        return false;
    }
}
