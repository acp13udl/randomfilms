package com.udl.softarch.randomfilms.repositories;

import com.udl.softarch.randomfilms.models.Actor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
public interface ActorRepository extends PagingAndSortingRepository<Actor, Long> {

}
