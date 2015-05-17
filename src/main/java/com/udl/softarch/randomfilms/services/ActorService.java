package com.udl.softarch.randomfilms.services;

import com.udl.softarch.randomfilms.models.Actor;
import com.udl.softarch.randomfilms.repositories.ActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by adrian on 17/5/15.
 */

public interface ActorService {

    List<Actor> saveActors(Long filmId,List<Actor> actors);
}
