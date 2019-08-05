package com.slt.documentmanagment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {

    private Integer id;
    private String idcol;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled = true;
    private boolean accountNonExpired = true;
    private boolean credentialsNonExpired = true;
    private boolean accountNonLocked = true;
    private String []roles;


}