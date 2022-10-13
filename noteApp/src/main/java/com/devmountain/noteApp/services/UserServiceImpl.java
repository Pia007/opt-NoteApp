package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.UserDto;
import com.devmountain.noteApp.entities.User;
import com.devmountain.noteApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

// Part 3c
@Service
public class UserServiceImpl implements UserService {

    //fields
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //methods
    //Register a user
    @Override
    @Transactional
    public List<String> addUser(UserDto userDto) {
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        /* Frontend: redirecting user to login after registering*/
        response.add("http://localhost:8080/login.html");
        return response;
    }

    // User login
    @Override
    public List<String> userLogin(UserDto userDto) {
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());

        // conditional logic
        if (userOptional.isPresent()) {
            if (passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())) {
                /* Frontend: redirecting user to home page after login*/
                response.add("http://localhost:8080/home.html");
                response.add(String.valueOf(userOptional.get().getId()));
            } else {
                response.add("Username or password does not match");
            }
        } else {
            response.add("Username or password does not match");
        }
        return response;
    }
}

/*
Service Layer :  business logic
    -- receives info from the repository layer
    -- performs transformation
    -- then passes them to the controller to handle the request

3c: Create UserServiceImp
    -- @Service
    -- add a private member field called “userRepository” of type “UserRepository” and annotate it with the @Autowired annotation:
        -- @Repository was added to the Repository, now Spring will find
            the corresponding dependency and inject it where needed when it finds
            the @Autowired annotation
   -- User Login credentials
        -- implement the PasswordEncoder Interface ( part of Spring Security Core Maven dependency)
            -- BCryptPasswordEncoder class - SSC contains this class
                -- used to hash and compare passwords
   -- add the SSC dependency to pom.xml
   -- CUSTOM BEAN CREATED IN CONFIG CLASS
3e: add the custom PasswordEncoder bean as a private field and @Autowired it

3f: REGISTERING A USER
   1. create a public method that returns a “List<String>” called “addUser”
    -- accepts a UserDto called “userDto” as an argument
    -- annotate it with @Transactional annotation
        Why?
        any time you are saving something to the database you should
        include the @Transactional annotation which ensures that the
        transaction that gets opened with your datasource gets resolved
    -- initialize a new member variable of type “List<String>” called “response” and set it equal to a new ArrayList
    -- invoke the “saveAndFlush()” method available in the “userRepository”
        -- flushing - synchronizing the state ti tge underlying DB
        -- save() - saves the data but does not sync it to the DB
        -- saveAndFlush() internally calls the save() and forces a flush of persistence
            context afterward, reads and saves changes at a later point in the transaction
        -- pass in the “user” object you just created
            -- this step is where a user is actually people persisted
    -- now the user object has been persisted, add a String to “response” with
        a success message and return the “response” method variable.
3g: LOGIN
  1. create a public method that returns “List<String>” called “userLogin”
    -- accepts a UserDto called “userDto” as an argument
    -- initialize a new method variable called “response” of type “List<String>” and set it equal to a new ArrayList
    -- create a new method variable called “userOptional” of type “Optional<User>”
        -- set it equal to the “findByUsername” method we are about to create in the “userRepository” and pass in the username field from the “userDto” argument.
            -- STOP AND GO TO UserRepository and create the "findByUsername" method,
        -- pass in userDto.getUsername() as the argument
    NOTE: Optionals help to avoid NullPointerExceptions
    logic: create 2 conditional statements
        if userOptional is present
            if truthy, check if passwords match
                 truthy - response message and add user_id to the response (Frontend may need it)
            otherwise - response message
        otherwise - response message
        return response message
 */
