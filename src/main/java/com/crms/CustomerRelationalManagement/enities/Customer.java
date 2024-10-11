package com.crms.CustomerRelationalManagement.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Customer {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email ;
    @Column(name = "mobile_Number")
    private String mobileNumber;
    private int age;

}
