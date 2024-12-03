package com.example.usersmanagementsoftware.Service;

import com.example.usersmanagementsoftware.Api.ApiException;
import com.example.usersmanagementsoftware.Model.User;
import com.example.usersmanagementsoftware.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id , User user){
      User oldUser = userRepository.findUserById(id);
      if(oldUser == null){
          throw new ApiException("User not found");
      }
      oldUser.setAge(user.getAge());
      oldUser.setRole(user.getRole());
      oldUser.setName(user.getName());
      oldUser.setEmail(user.getEmail());
      oldUser.setUsername(user.getUsername());
      oldUser.setPassword(user.getPassword());
      userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if(user == null){
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }

    //Check if username and password are correct
    public User checkByUsernameAndPassword(String username , String password){
        User user = userRepository.findUserByUsernameAndPassword(username,password);
        if(user == null){
            throw new ApiException("User not found");
        }
        return user;
    }

    //Get user by email
    public User getUserByEmail(String email){
        User user = userRepository.getUserByEmail(email);
        if(user == null){
            throw new ApiException("User with given email not found");
        }
        return user;
    }

    // Get All users with specific role
    public List<User> getUsersByRole(String role){
        List<User> users = userRepository.getUserByRole(role);
        if(users == null){
            throw new ApiException("No users found");
        }
        return users;
    }

    //Get All users with specific age or above
    public List<User> getAllUsersByAge(Integer age){
        List<User> users = userRepository.getAllUsersByAge(age);
        if(users == null){
            throw new ApiException("No users found");
        }
        return users;
    }

}
