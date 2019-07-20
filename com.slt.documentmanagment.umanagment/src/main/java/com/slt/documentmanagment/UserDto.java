package com.slt.documentmanagment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String []roles;

}
