package com.udl.softarch.springexample.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
@Entity
public class Actor extends PersonInvolved{

    @ManyToMany(mappedBy = "actors")
    private List<Film> films = new ArrayList<>();

    @Override
    public boolean isDirector() {
        return false;
    }

    @Override
    public boolean isActor() {
        return true;
    }
}
