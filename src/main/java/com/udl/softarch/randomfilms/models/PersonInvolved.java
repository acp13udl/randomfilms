package com.udl.softarch.randomfilms.models;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Allu on 21/04/2015.
 */
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class PersonInvolved {

    @javax.persistence.Id
    @GeneratedValue
    private Long Id;

    public abstract boolean isDirector();

    public abstract boolean isActor();

    public Long getId() {
        return Id;
    }


}
