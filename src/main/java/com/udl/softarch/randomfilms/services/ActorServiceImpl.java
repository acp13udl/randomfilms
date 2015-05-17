package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.ActorRepository;
import com.udl.softarch.randomfilms.repositories.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Allu on 17/05/2015.
 */
@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    ActorRepository actorRepository;


    @Transactional
    @Override
    public Actor saveActor(Actor actor) {

        return actorRepository.save(actor);
    }

    @Transactional
    @Override
    public void removeActor(Actor actor) {
        actorRepository.delete(actor);
    }
}
