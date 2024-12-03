package com.example.usersmanagementsoftware.Controller;

import com.example.usersmanagementsoftware.Api.ApiResponse;
import com.example.usersmanagementsoftware.Model.User;
import com.example.usersmanagementsoftware.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-management-system")
public class UserController {

    private final UserService userService;
    Logger logger= LoggerFactory.getLogger(UserController.class);

    @GetMapping("/get")
    public ResponseEntity<?> getAllUsers(){
        logger.info("inside getAllUsers");
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user, Errors errors){
        logger.info("inside addUser");
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id , @RequestBody @Valid User user , Errors errors){
       logger.info("inside updateUser");
       if(errors.hasErrors()){
           return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
       }
       userService.updateUser(id,user);
       return ResponseEntity.status(200).body(new ApiResponse("User updated"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        logger.info("inside deleteUser");
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
    }
    
    @GetMapping("/check/username/{username}/password/{password}")
    public ResponseEntity<?> checkByUsernameAndPassword(@PathVariable String username , @PathVariable String password){
      logger.info("checkByUsernameAndPassword");
      return ResponseEntity.status(200).body(userService.checkByUsernameAndPassword(username,password));
    }

    @GetMapping("/get/by-email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email){
        logger.info("inside getUserByEmail");
        return ResponseEntity.status(200).body(userService.getUserByEmail(email));
    }

    @GetMapping("/get/by-role/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable String role){
        logger.info("getUsersByRole");
        return ResponseEntity.status(200).body(userService.getUsersByRole(role));
    }

    @GetMapping("/get/users-by-age/{age}")
    public ResponseEntity<?> getAllUsersByAge(@PathVariable Integer age){
        logger.info("getAllUsersByAge");
        return ResponseEntity.status(200).body(userService.getAllUsersByAge(age));
    }

}
