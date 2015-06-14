package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.models.Director;
import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.repositories.ActorRepository;
import com.udl.softarch.randomfilms.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Allu on 18/05/2015.
 */
@Service
public class DirectorServiceImpl implements DirectorService{
    @Autowired
    DirectorRepository directorRepository;

    @Transactional
    @Override
    public List<Director> saveDirectors(Long filmId, List<Director> directors) {
        Director directorWithId;
        List<Director> resultDirectorsWithId = new ArrayList<>();
        for (Director director:directors){
            directorWithId= directorRepository.findDirectorBydirectorId(director.getDirectorId());
            if (notExistDirector(directorWithId)){
                directorWithId = directorRepository.save(director);
            }
            resultDirectorsWithId.add(directorWithId);
        }

        return resultDirectorsWithId;
    }

    private boolean notExistDirector(Director director){

        return director == null;
    }
}
