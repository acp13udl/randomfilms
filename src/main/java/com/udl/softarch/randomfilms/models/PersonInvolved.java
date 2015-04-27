package com.udl.softarch.randomfilms.models;

import javax.persistence.*;

/**
 * Created by Allu on 21/04/2015.
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class PersonInvolved {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    private String idIMDB;

    public abstract boolean isDirector();

    public abstract boolean isActor();

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdIMDB() {
        return idIMDB;
    }

    public void setIdIMDB(String idIMDB) {
        this.idIMDB = idIMDB;
    }
}
