package com.crms.CustomerRelationalManagement.dtos;

import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
    private String id;
    private String firstName;
    private String lastName;
    private String email ;
    private String mobileName;
    private int age;
}
