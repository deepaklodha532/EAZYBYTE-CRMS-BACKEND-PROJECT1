package com.crms.CustomerRelationalManagement.enities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Role {
    @Id
    private String roleId ;
    private String roleName ;

    @ManyToMany(mappedBy = "roles" , fetch = FetchType.LAZY)
    private  List<User> users = new ArrayList<>()  ;
}
