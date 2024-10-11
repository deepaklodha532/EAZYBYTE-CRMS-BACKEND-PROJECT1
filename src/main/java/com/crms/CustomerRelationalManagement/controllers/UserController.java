package com.crms.CustomerRelationalManagement.controllers;

import com.crms.CustomerRelationalManagement.dtos.UserDto;
import com.crms.CustomerRelationalManagement.helper.ApiResponse;
import com.crms.CustomerRelationalManagement.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService  ;

    public UserController(UserService userService){
        this.userService =userService  ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @PostMapping
    public ResponseEntity<UserDto > createUser(@RequestBody UserDto userDto) {
        UserDto userDto1 =  userService.create(userDto) ;
        return new ResponseEntity<>(userDto1 , HttpStatus.CREATED) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getById(@PathVariable String userId){
        UserDto userDto =  userService.getById(userId) ;
        return  new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @GetMapping
    public ResponseEntity<List<UserDto>>  getAll(){
        List<UserDto> userDtos = userService.getAllUser() ;
        return new ResponseEntity<>(userDtos,  HttpStatus.OK) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteuser(@PathVariable String userId){
        userService.deleteUser(userId) ;
        ApiResponse response =  ApiResponse.builder().message("deleted successfully ").success(true).build();
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto>  updated(@PathVariable String userId , @RequestBody UserDto userDto)
    {
        UserDto updated = userService.update( userDto,userId) ;
        return new ResponseEntity<>(updated , HttpStatus.OK) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @GetMapping("/{email}")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<UserDto>  getByEmail(@RequestBody String email){
        UserDto userDto =userService.getUserByEmail(email)  ;
        return new ResponseEntity<>(userDto, HttpStatus.OK)  ;
    }
}