package com.crms.CustomerRelationalManagement.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.processing.Pattern;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class UserDto {
    private String userId;

    private String name ;
//    @Pattern(regxp= "^[a-z0-9][-a-z0-9._]+@([-a-z0-9]+\\.)+[a-z]{2,5}$", message = "Invalid User Email !!")
    @NotBlank(message = "Email is required !!")
    private String email ;
    private String gender ;

    @NotBlank(message = "Password is required !!")
    private String password ;
    @NotBlank(message = "Write something about yourself !!")
    private String about;

    // @pattern
    //custom validation
    //@ImageNameValid

    private List<RoleDto> roles = new ArrayList<>() ;
}
