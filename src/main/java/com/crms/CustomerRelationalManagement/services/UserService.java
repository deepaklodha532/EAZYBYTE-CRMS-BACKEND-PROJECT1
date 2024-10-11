package com.crms.CustomerRelationalManagement.services;

import com.crms.CustomerRelationalManagement.dtos.UserDto;
import com.crms.CustomerRelationalManagement.enities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserDto create(UserDto userDto );

    UserDto update(UserDto userDto , String userId) ;

    UserDto getById(String userId) ;

    void deleteUser(String userId)  ;

    List<UserDto> getAllUser() ;

    UserDto getUserByEmail(String email )  ;

//    List<UserDto> searchUser(String keyword);

    // other user specific features
//    Optional<User> findUserByEmailOptional(String email) ;
}
