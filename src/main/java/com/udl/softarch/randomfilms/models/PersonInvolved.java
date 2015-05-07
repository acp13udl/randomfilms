package com.udl.softarch.randomfilms.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Allu on 21/04/2015.
 */
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class PersonInvolved {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    public abstract boolean isDirector();

    public abstract boolean isActor();

    public Long getId() {
        return Id;
    }


}
