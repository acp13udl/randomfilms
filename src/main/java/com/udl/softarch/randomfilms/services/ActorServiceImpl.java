package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adrian on 17/5/15.
 */
@Service
public class ActorServiceImpl implements ActorService{

    @Autowired
    ActorRepository actorRepository;

    @Transactional
    @Override
    public List<Actor> saveActors(Long filmId, List<Actor> actors) {
        Actor actorWithId;
        List<Actor> resultActorsWithId = new ArrayList<>();
        for (Actor actor:actors){
            actorWithId= actorRepository.findActorByactorId(actor.getActorId());
            if (notExistActor(actorWithId)){
                actorWithId = actorRepository.save(actor);
            }
            resultActorsWithId.add(actorWithId);
        }

        return resultActorsWithId;
    }

    private boolean notExistActor(Actor actor){

        return actor == null;
    }
}
