package com.momo.fitnessTracker.service;

import com.momo.fitnessTracker.Dto.UserCredentialsDto;
import com.momo.fitnessTracker.Dto.UserDetailsDto;
import com.momo.fitnessTracker.Dto.UserDto;
import com.momo.fitnessTracker.exception.UserNotFoundException;
import com.momo.fitnessTracker.model.User;
import com.momo.fitnessTracker.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    ModelMapper mapper = new ModelMapper();

    public void saveUser(UserDto userDto){

        User user = mapper.map(userDto,User.class);
        MultipartFile file = user.getProfileImageFile();
        if(file != null && !file.isEmpty()){
            try{
                 user.setProfileImage(file.getBytes());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        userRepository.save(user);
    }


    public void updateUserDetails(UserDto userDto,UserDetailsDto userDetailsDto){
        User updtUser = mapper.map(userDto,User.class);
        System.out.println("--------------");
        System.out.println(updtUser);
        System.out.println(updtUser.getUsername());
        System.out.println("--------------");
        updtUser.setFirstName(userDetailsDto.getFirstName());
        updtUser.setLastName(userDetailsDto.getLastName());
        updtUser.setEmail(userDetailsDto.getEmail());
        updtUser.setAge(userDetailsDto.getAge());
        userRepository.save(updtUser);
    }
    @Override
    public void updateUserCredentials(UserDto userDto, UserCredentialsDto userCredentialsDto) {
        User updtUser = mapper.map(userDto,User.class);
        System.out.println("--------------");
        System.out.println(updtUser);
        System.out.println(updtUser.getUsername());
        System.out.println("--------------");

        updtUser.setUsername(userCredentialsDto.getUsername());
        updtUser.setPassword(userCredentialsDto.getPassword());
        userRepository.save(updtUser);
    }

    @Override
    public UserDto findByUsername(String username) throws UserNotFoundException {
        Optional<User> optUser = userRepository.findByUsername(username);
        if(optUser.isPresent()) {
            User user = optUser.get();
            UserDto userDto = mapper.map(user,UserDto.class);
            return userDto;
        }
        throw new UserNotFoundException("User not found");
    }

    public User findUser(String username, String password){
        Optional<User> optional = Optional.ofNullable(userRepository.findByUsernameAndPassword(username, password));
        User user = null;
        boolean value=true;
        if(optional.isPresent()){
            user = optional.get();
        }
//        else{
//            throw new RuntimeException("User not found");
//        }
        return user;
    }
    @Override
    public List<User> showAllUser() {
       List<User> listUsers =  userRepository.findAll();
       return listUsers;
    }



    public Optional<User> findById(String id) {
        return userRepository.findById(Long.parseLong(id));
    }
}
