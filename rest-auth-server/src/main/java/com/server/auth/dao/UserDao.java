package com.server.auth.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.server.auth.entity.User;

/**
 * Created by Agus Suhardi on 22-Jun-17.
 */
public interface UserDao extends PagingAndSortingRepository<User, String> {

    @Query("select u from User u where u.username= :userName")
    User findByUserName(@Param("userName") String userName);

}