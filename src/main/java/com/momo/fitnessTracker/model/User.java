package com.momo.fitnessTracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usertbl")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,name="first_name")
    private String firstName;

    @Column(nullable = false,name="last_name")
    private String lastName;

    @Column(nullable = false,name="age")
    private int age;

    @Column(nullable = false,name="email",unique = true)
    private String email;

    @Column(nullable = false,name="username",unique = true)
    @Size(min=4, max = 12, message = "Field must be between 3 and 50 characters")
    private String username;

    @Column(nullable = false)
    private String password;

    @Lob
    @Column(name = "profile_image",columnDefinition = "LONGBLOB")
    private byte[] profileImage;

    @Transient
    private MultipartFile profileImageFile;

    public MultipartFile getProfileImageFile() {
        return profileImageFile;
    }

    public void setProfileImageFile(MultipartFile profileImageFile){
        this.profileImageFile = profileImageFile;
    }


    @Transient
    public String activeUser;

    public String getActiveUser(){
        return activeUser;
    }
    public void setActiveUser(String activeUser){
        this.activeUser=activeUser;
    }

    public String fullName(){
        return firstName+" "+ lastName;
    }

    public User (Long id, String firstName,String lastName,int age,String email, String username,String password){
        this.id = id;
        this.firstName = firstName;
        this.lastName=lastName;
        this.age = age;
        this.email = email;
        this.username=username;
        this.password= password;
    }
}
