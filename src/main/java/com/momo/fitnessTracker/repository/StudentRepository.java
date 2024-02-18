//package com.momo.fitnessTracker.repository;
//
//import com.momo.fitnessTracker.model.Student;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface StudentRepository extends JpaRepository<Student,Long> {
//
//    @Query("" +
//            "SELECT CASE WHEN COUNT(s) > 0 " +
//            "THEN true ELSE false END " +
//            "FROM STUDENT s " +
//            "WHERE s.email= :email")
//    boolean isStudentEmailExists(@Param("email")String email);
//}
//
