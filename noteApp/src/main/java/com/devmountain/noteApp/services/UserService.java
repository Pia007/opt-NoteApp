package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.UserDto;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    //methods
    //Register a user
    @Transactional
    List<String> addUser(UserDto userDto);

    // User login
    List<String> userLogin(UserDto userDto);
}
