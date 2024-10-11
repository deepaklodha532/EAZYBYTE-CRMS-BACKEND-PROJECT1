package com.crms.CustomerRelationalManagement.repositories;

import com.crms.CustomerRelationalManagement.dtos.CustomerDto;
import com.crms.CustomerRelationalManagement.enities.Customer;
import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer ,String> {
    Optional<String> findByEmail(String email) ;

    Optional<List<Customer>> findByFirstName(String name )  ;

    Optional<List<Customer>> findByAgeLessThanEqual(int age) ;

    Optional<List<Customer>> findByLastName (String lastName) ;
}
