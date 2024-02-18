package com.momo.fitnessTracker.controller;

import com.momo.fitnessTracker.Dto.UserCredentialsDto;
import com.momo.fitnessTracker.Dto.UserDetailsDto;
import com.momo.fitnessTracker.Dto.UserDto;
import com.momo.fitnessTracker.Dto.UserProfilePictureDto;
import com.momo.fitnessTracker.exception.UserNotFoundException;
import com.momo.fitnessTracker.model.User;
import com.momo.fitnessTracker.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    HttpServletResponse response;

    @GetMapping("/")
    public String homePage(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        System.out.println("inside get register");
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/process-register")
    public String registerUser(UserDto userDto, Model model) {
        System.out.println("inside post register");
        userServiceImpl.saveUser(userDto);
        model.addAttribute("registrationSuccess", true);
        return "register-success";
    }

    @GetMapping("/edit-profile/{username}")
    public String editDetailsProfile(@PathVariable("username") String username, Model model) throws UserNotFoundException {
        System.out.println("inside edit profile.");
        UserDto userDto = userServiceImpl.findByUsername(username);
        model.addAttribute("user",userDto);
        return "edit-profile";
    }

    @GetMapping("/edit-credentials/{username}")
    public String editCredentialsProfile(@PathVariable("username") String username, Model model) throws UserNotFoundException {
        System.out.println("inside edit profile.");
        UserDto userDto = userServiceImpl.findByUsername(username);
        model.addAttribute("user",userDto);
        return "edit-credentials";
    }

    @GetMapping("/change-profile-picture/{username}")
    public String changeProfilePicture(@PathVariable("username") String username, Model model) throws UserNotFoundException {
        System.out.println("inside changeProfilePicture .");
        UserDto userDto = userServiceImpl.findByUsername(username);
        model.addAttribute("user",userDto);
        return "change-profile-picture";
    }

    @PostMapping("/edit-user-details/{username}")
    public String editProfile(@PathVariable String username, @ModelAttribute UserDetailsDto updatedUser) throws UserNotFoundException, IOException {
        System.out.println("Inside patch mapping");
        UserDto existingUser = userServiceImpl.findByUsername(username);

        // Update the existing user with the changes
//        existingUser.setFirstName(updatedUser.getFirstName());
//        existingUser.setLastName(updatedUser.getLastName());
//        existingUser.setEmail(updatedUser.getEmail());
//        existingUser.setAge(updatedUser.getAge());
//
//        existingUser.setUsername(updatedUser.getUsername());
//        existingUser.setPassword(updatedUser.getPassword());
        //existingUser.setProfileImage(updatedUser.getProfileImageFile().getBytes());


        userServiceImpl.updateUserDetails(existingUser,updatedUser);

        // Redirect to the user profile page
//        return "redirect:/user-profile/" + username;
        return "redirect:/login";
    }

    @PostMapping("/edit-user-credentials/{username}")
    public String editProfileCredentials(@PathVariable String username, @ModelAttribute UserCredentialsDto updatedUser) throws UserNotFoundException, IOException {
        System.out.println("Inside credentials mapping");
        UserDto existingUser = userServiceImpl.findByUsername(username);

        userServiceImpl.updateUserCredentials(existingUser,updatedUser);

        return "redirect:/login";
    }

    @PostMapping("/change-user-profile-picture/{username}")
    public String changeUserProfilePicture(@PathVariable String username, @ModelAttribute UserProfilePictureDto userProfilePictureDto) throws UserNotFoundException, IOException {
        System.out.println("Inside credentials mapping");
        UserDto existingUser = userServiceImpl.findByUsername(username);

        userServiceImpl.updateProfilePicture(existingUser,userProfilePictureDto);

        return "redirect:/login";
    }

    @PostMapping("/login-user")
    public String userLogin(@RequestParam String username, @RequestParam String password , Model model, HttpSession session) throws IOException {

        System.out.println("inside user login.");

        User isValidUser = userServiceImpl.findUser(username,password);

        if(isValidUser == null ){
            model.addAttribute("errorMessage","Access Denied-Invalid Credentials");
            return "login";
        }

        String fullName = isValidUser.fullName();
        isValidUser.setActiveUser(fullName);


        String base64Image = Base64.getEncoder().encodeToString(isValidUser.getProfileImage());

        model.addAttribute("fullName",fullName);
        model.addAttribute("age",isValidUser.getAge());
        model.addAttribute("username",isValidUser.getUsername());
        model.addAttribute("userEmail",isValidUser.getEmail());
        model.addAttribute("profileImage",base64Image);
        session.setAttribute("activeUser",fullName);

        return "profile";
    }

    @PostMapping("/user-list")
    public String userList(Model model,HttpSession session){
        System.out.println("inside user list.");

        String fullName = (String) session.getAttribute("activeUser");
        System.out.println(fullName);

        model.addAttribute("fullName",fullName);

        List<User> listUsers = userServiceImpl.showAllUser();
        model.addAttribute("listUsers",listUsers);
        return "user-list";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        System.out.println("inside get login");
        return "login";
    }

    @PostMapping("/logout")
    public String logoutPage(Model model){
        return "redirect:/login";
    }

}
