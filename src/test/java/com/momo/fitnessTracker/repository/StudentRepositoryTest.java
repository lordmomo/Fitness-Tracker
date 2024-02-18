package com.momo.fitnessTracker.repository;

import com.momo.fitnessTracker.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository underTest;

    @Test
    void isStudentEmailExists() {

        //given
        String email = "abc@gmail.com";
        Student student = new Student("abc",11,"abc@gmail.com","abc123");
        underTest.save(student);

        //when

        boolean expected = underTest.isStudentEmailExists(email);

        //then
        assertThat(expected).isTrue();

    }
}