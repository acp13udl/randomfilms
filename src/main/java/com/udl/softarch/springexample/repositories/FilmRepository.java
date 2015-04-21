package com.udl.softarch.springexample.repositories;

import com.udl.softarch.springexample.models.Film;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Allu on 21/04/2015.
 */
public interface FilmRepository extends PagingAndSortingRepository<Film, Long> {
    List<Film> findByContentContaining(@Param("content") String content);
}
