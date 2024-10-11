package com.crms.CustomerRelationalManagement.controllers;

import com.crms.CustomerRelationalManagement.dtos.CustomerDto;
import com.crms.CustomerRelationalManagement.helper.ApiResponse;
import com.crms.CustomerRelationalManagement.services.CustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    private CustomerService customerService ;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @PostMapping
    public ResponseEntity<CustomerDto> insertCustomer(@RequestBody CustomerDto customerDto  ){
        CustomerDto customerDto1 = customerService.insertCustomer(customerDto) ;
        return  new ResponseEntity<>(customerDto1 , HttpStatus.CREATED)  ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @DeleteMapping("/{customerId}")
    public ResponseEntity<ApiResponse> delete(@PathVariable  String customerId){
        customerService.deleteCustomer(customerId) ;
        ApiResponse response= ApiResponse.builder()
                .message("deleted Successfully")
                .success(true)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getByCustomerId(@PathVariable  String customerId){
        CustomerDto customerDto = customerService.getCustomer(customerId) ;
        return  new ResponseEntity<>(customerDto , HttpStatus.OK) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll(){
        List<CustomerDto> customerDtos = customerService.getCustomers();
        return new ResponseEntity<>(customerDtos , HttpStatus.OK) ;
    }
    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerDto> update(@PathVariable String customerId ,@RequestBody CustomerDto customerDto){
        CustomerDto customerDto1 = customerService.updateCustomer(customerId, customerDto) ;
        return new ResponseEntity<>(customerDto1, HttpStatus.OK) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @PostMapping("/multiple")
    public ResponseEntity<ApiResponse> insertMultiple(@RequestBody List<CustomerDto> customerDtos) {
         customerService.insertMultipleCustomer(customerDtos) ;
         ApiResponse response = ApiResponse.builder().message("Successfully added customer" ).success(true).build() ;
        return new ResponseEntity<>(response, HttpStatus.CREATED) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @GetMapping("/byfirstname/{name}")
    public ResponseEntity<List<CustomerDto>> getByName(@PathVariable String name){
        List<CustomerDto> customerDtos  = customerService.getCustomerByFirstName(name) ;
        return new ResponseEntity<>(customerDtos ,HttpStatus.OK) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @GetMapping("/byage/{age}" )
    public ResponseEntity<List<CustomerDto>> getCustomerByAge(@PathVariable int age){
        List<CustomerDto> customerDtos = customerService.getByAge(age) ;
        return new ResponseEntity<>(customerDtos,HttpStatus.OK) ;
    }

    @PreAuthorize("hasAnyRole('NORMAL','ADMIN')")
    @GetMapping("/bylastname/{name}")
    public ResponseEntity<List<CustomerDto> > getByLastName(@PathVariable String name){
        List<CustomerDto> customerDtos = customerService.getByLastName(name) ;
        return new ResponseEntity<>(customerDtos , HttpStatus.OK) ;
    }

}
