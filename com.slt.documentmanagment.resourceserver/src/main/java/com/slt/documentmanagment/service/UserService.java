package com.slt.documentmanagment.service;

import com.slt.documentmanagment.model.Role;
import com.slt.documentmanagment.repository.RoleRepository;
import com.slt.documentmanagment.repository.UserDetailRepository;
import com.slt.documentmanagment.UserDto;
import com.slt.documentmanagment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserDetailRepository userDetailRepository;
    @Autowired
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDto saveUser(UserDto userDto){
        User user = new User();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEmail(userDto.getEmail());
        user.setEnabled(true);
        List<String> rolesArr = Arrays.asList(userDto.getRoles());
        List<Role> roleList = rolesArr.stream().map(role -> {
            Role byName = roleRepository.findByName(role);
            return byName;
        }).collect(Collectors.toList());
        user.setRoles(roleList);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUserName());
        User savedUser = userDetailRepository.save(user);
        return getUserDtoFromUser(savedUser);
    }


    public UserDto getUserDtoFromUser(User user){
        UserDto userDto = new UserDto();
        userDto.setIdcol(user.getId()+"");
        userDto.setUserName(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAccountNonExpired(user.isAccountNonExpired());
        userDto.setAccountNonLocked(user.isAccountNonLocked());
        userDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
        userDto.setEnabled(user.isEnabled());
        return userDto;
    }

}
