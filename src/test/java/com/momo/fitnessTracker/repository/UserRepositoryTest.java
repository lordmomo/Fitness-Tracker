package com.momo.fitnessTracker.repository;

import com.momo.fitnessTracker.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

//@DataJpaTest
class UserRepositoryTest {

//    @Autowired
//    private UserRepository underTest;
//
//    @Test
//    void itShouldCheckIfUserEmailExists() {
//
//        //given
//        String email="ramizz123";
//        User user = new User("123","rami","raj",45,email,"ramizz123","ramiraj");
//        underTest.save(user);
//        //when
//
//        boolean expected = underTest.selectExistsEmail(email);
//        //then
//
//        assertThat(expected).isTrue();
//
//    }
}