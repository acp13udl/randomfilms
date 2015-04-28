package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Director;
import com.udl.softarch.randomfilms.models.PersonInvolved;
import com.udl.softarch.randomfilms.repositories.ActorRepository;
import com.udl.softarch.randomfilms.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by adrian on 28/4/15.
 */
@Service
public class PersonInvolvedServiceImpl implements PersonInvolvedService{

    @Autowired
    ActorRepository actorRepository;
    @Autowired
    DirectorRepository directorRepository;


    @Override
    public PersonInvolved savedPersonInvoved(PersonInvolved personInvolved) {

        if (personInvolved.isActor()) {
            return actorRepository.save((Actor) personInvolved);
        }
        if (personInvolved.isDirector()){
            return directorRepository.save((Director)personInvolved);
        }

        return null;
    }

    @Override
    public void removePersonInvoved(PersonInvolved personInvolved) {

        if (personInvolved.isActor()) {
            actorRepository.delete((Actor) personInvolved);
        }
        if (personInvolved.isDirector()){
            directorRepository.delete((Director)personInvolved);
        }
    }
}

