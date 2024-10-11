package com.crms.CustomerRelationalManagement.services.impls;


import com.crms.CustomerRelationalManagement.dtos.CustomerDto;
import com.crms.CustomerRelationalManagement.enities.Customer;
import com.crms.CustomerRelationalManagement.exceptions.ResourceNotFoundException;
import com.crms.CustomerRelationalManagement.repositories.CustomerRepository;
import com.crms.CustomerRelationalManagement.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository ;
    private ModelMapper mapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper mapper){
        this.customerRepository=customerRepository ;
        this.mapper = mapper ;
    }
    @Override
    public CustomerDto insertCustomer(CustomerDto customerDto) {
        String randomId = UUID.randomUUID().toString();
        customerDto.setId(randomId);
        Customer  saved = customerRepository.save(mapper.map(customerDto, Customer.class)) ;
        return mapper.map(saved , CustomerDto.class) ;
    }

    @Override
    public void deleteCustomer(String customerId) {
        Customer customer =  customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("customer not found give customer id"));
        customerRepository.delete(customer) ;

    }

    @Override
    public CustomerDto getCustomer(String customerId) {
        Customer customer =  customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("customer not found give customer id"));
        return mapper.map(customer, CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos =  customers.stream().map((customer -> mapper.map(customer,CustomerDto.class))).collect(Collectors.toList());
        return customerDtos ;
    }

    @Override
    public CustomerDto updateCustomer(String customerId, CustomerDto customerDto) {
        Customer customer =  customerRepository.findById(customerId).orElseThrow(()->new ResourceNotFoundException("customer not found give customer id"));
        customer.setFirstName(customerDto.getFirstName()) ;
        customer.setLastName(customer.getLastName());
//        customer.setEmail(customerDto.getEmail());
        customer.setMobileNumber(customerDto.getMobileName());
        customer.setAge(customer.getAge()) ;
        Customer updated = customerRepository.save(customer);
        return mapper.map(updated , CustomerDto.class) ;
    }

    @Override
    public void insertMultipleCustomer(List<CustomerDto> customerDtos) {
          List<Customer> customers = customerDtos.stream().map(customerDto -> mapper.map(customerDto, Customer.class) ).collect(Collectors.toList());
          for(int i = 0  ;  i< customers.size() ; i++){
              customers.get(i).setId(UUID.randomUUID().toString()) ;
              customerRepository.save(customers.get(i)) ;
          }

    }
    @Override
    public List<CustomerDto> getCustomerByFirstName(String name) {
        List<Customer> customers = customerRepository.findByFirstName(name).orElseThrow(()->new ResourceNotFoundException("customers not available this name"));
        List<CustomerDto> customerDtos = customers.stream().map(customer -> mapper.map(customer, CustomerDto.class)).collect(Collectors.toList());
        return customerDtos;
    }

    @Override
    public List<CustomerDto> getByAge(int age) {
        List<Customer> customers =   customerRepository.findByAgeLessThanEqual(age).orElseThrow(()->new ResourceNotFoundException("less than age not available customers")) ;
        return customers.stream().map(customer -> mapper.map(customer, CustomerDto.class) ).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDto> getByLastName(String lastName) {
        List<Customer> customers =  customerRepository.findByLastName(lastName).orElseThrow(()->new ResourceNotFoundException("This lastname customer not available "));
        List<CustomerDto> customerDtos =customers.stream().map(customer -> mapper.map(customer,CustomerDto.class) ).collect(Collectors.toList());
        return customerDtos;
    }
}
