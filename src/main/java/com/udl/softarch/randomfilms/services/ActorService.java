package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Film;

/**
 * Created by Allu on 17/05/2015.
 */
public interface ActorService {
    Actor saveActor(Actor actor);
    void removeActor(Actor actor);
}
