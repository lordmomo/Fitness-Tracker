package com.momo.fitnessTracker.Dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private int age;

    private String email;

    private String username;

    private String password;

    private byte[] profileImage;

    private MultipartFile profileImageFile;

//    @Transient
//    public String activeUser;


}
