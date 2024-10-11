package com.crms.CustomerRelationalManagement.services;

import com.crms.CustomerRelationalManagement.dtos.CustomerDto;
import com.crms.CustomerRelationalManagement.enities.Customer;


import java.util.List;

public interface CustomerService {

    public CustomerDto insertCustomer(CustomerDto customerDto) ;

    public void deleteCustomer(String customerId) ;

    public CustomerDto getCustomer(String customerId) ;

    public List<CustomerDto> getCustomers();

    public CustomerDto updateCustomer(String customerId , CustomerDto customerDto ) ;

    public void insertMultipleCustomer(List<CustomerDto> customerDtos) ;

    public List<CustomerDto> getCustomerByFirstName(String name) ;

    public List<CustomerDto> getByAge(int age) ;

    public List<CustomerDto> getByLastName(String lastName ) ;

}
