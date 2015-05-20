package com.udl.softarch.randomfilms.repositories;

import com.udl.softarch.randomfilms.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by adrian on 23/3/15.
 */
public interface UserRepository extends JpaRepository<User,Long> {


    User findUserByUsername(@Param("username") String username);

}
