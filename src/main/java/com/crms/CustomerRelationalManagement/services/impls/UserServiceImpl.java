package com.crms.CustomerRelationalManagement.services.impls;

import com.crms.CustomerRelationalManagement.dtos.UserDto;
import com.crms.CustomerRelationalManagement.enities.Role;
import com.crms.CustomerRelationalManagement.enities.User;
import com.crms.CustomerRelationalManagement.exceptions.ResourceNotFoundException;
import com.crms.CustomerRelationalManagement.repositories.RoleRepository;
import com.crms.CustomerRelationalManagement.repositories.UserRepository;
import com.crms.CustomerRelationalManagement.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper ;

    @Autowired
    private PasswordEncoder passwordEncoder ;

    @Autowired
    private RoleRepository roleRepository ;

    @Override
    public UserDto create(UserDto userDto) {

        // dto --> entity
        userDto.setUserId(UUID.randomUUID().toString());
        User user =mapper.map(userDto,User.class ) ;
        // entity --> dto

        Role role = new Role( );
        role.setRoleId(UUID.randomUUID().toString());
        role.setRoleName("ROLE_NORMAL");
        Role roleNormal = roleRepository.findByRoleName("ROLE_NORMAL").orElse(role);
        user.setRoles(List.of(roleNormal));
        user.setPassword(passwordEncoder.encode(user.getPassword()) );
        User saved =  userRepository.save(user) ;

        return mapper.map(saved , UserDto.class) ;
    }

    @Override
    public UserDto update(UserDto userDto, String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("This user is not available"));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setGender(userDto.getGender());
        user.setAbout(userDto.getAbout());
        user.setEmail(userDto.getEmail());
        User saved =userRepository.save(user);
        return mapper.map(saved, UserDto.class);
    }

    @Override
    public UserDto getById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("This user is not available"));
        return mapper.map(user, UserDto.class) ;
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("This user is not available"));
        userRepository.delete(user)  ;

    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user-> mapper.map(user,  UserDto.class)).collect(Collectors.toList());
        return userDtos ;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("This user is not available"));
        return mapper.map(user,  UserDto.class ) ;
    }




}
