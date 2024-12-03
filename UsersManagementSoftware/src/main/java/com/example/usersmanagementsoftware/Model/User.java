package com.example.usersmanagementsoftware.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@Check(constraints = "role='user' or role='admin'")
@Entity
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // name :
    //Cannot be null
    //Length more than4
    @NotEmpty(message = "Name is empty")
    @Size(min = 5 , message = "Name length must be more than 4")
    @Column(columnDefinition = "varchar(50) not null")
    private String name;

    //username :
    //Cannot be null
    //Length more than4
    //must be unique
    @NotEmpty(message = "Username is empty")
    @Size(min = 5 , message = "username length must be more than 4")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String username;

    //password :
    //Cannot be null
    @NotEmpty(message = "Password is empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String password;

    //email :
    //Cannot be null
    //must be valid email
    //must be unique
    @NotBlank(message = "Email is blank")
    @Email(message = "Enter valid email format")
    @Column(columnDefinition = "varchar(100) not null unique")
    private String email;


    //role :
    //Cannot be null
    //must be user or admin only
    @NotEmpty(message = "role is empty")
    @Pattern(regexp = "^user|admin$" , message = "user role should be only user or admin")
    @Column(columnDefinition = "varchar(5) not null")
    private String role;

    //age :
    //Cannot be null
    //must be integer
    @NotNull(message = "age is null")
    @Positive(message = "Age must be a positive integer")
    @Column(columnDefinition = "int not null")
    private Integer age;

}
