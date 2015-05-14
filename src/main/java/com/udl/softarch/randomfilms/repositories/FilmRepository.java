package com.udl.softarch.randomfilms.repositories;

import com.udl.softarch.randomfilms.models.Film;
import com.udl.softarch.randomfilms.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Allu on 21/04/2015.
 */
public interface FilmRepository extends JpaRepository<Film,Long> {

    Film findFilmByTitle(@Param("title") String title);

}
