package com.momo.fitnessTracker.repository;

import com.momo.fitnessTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND u.password = ?2")
    User findByUsernameAndPassword(String username, String password);
//
//    @Query(""+
//            "SELECT CASE WHEN COUNT(u) >0 THEN" +
//            "TRUE ELSE FALSE END" +
//            "FROM USER u" +
//            "WHERE u.email= ?1"
//        )
//    Boolean selectExistsEmail(String email);
}

