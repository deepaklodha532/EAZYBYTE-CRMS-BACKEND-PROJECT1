package com.crms.CustomerRelationalManagement.repositories;

import com.crms.CustomerRelationalManagement.enities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,String> {

    Optional<Role> findByRoleName(String roleName) ;

}
