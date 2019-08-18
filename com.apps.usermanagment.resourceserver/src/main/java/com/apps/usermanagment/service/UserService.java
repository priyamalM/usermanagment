package com.apps.usermanagment.service;

import com.apps.usermanagment.model.Role;
import com.apps.usermanagment.repository.RoleRepository;
import com.apps.usermanagment.repository.UserDetailRepository;
import com.apps.usermanagment.UserDto;
import com.apps.usermanagment.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        User user = getUserFromUserDto(userDto);
        User savedUser = userDetailRepository.save(user);
        return getUserDtoFromUser(savedUser);
    }

    public Page<User> findAllUsers(Pageable pageable){
        Page<User> all = userDetailRepository.findAll(pageable);
        return all;
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

    public User getUserFromUserDto(UserDto userDto){
        User user = new User();
        user.setAccountNonExpired(userDto.isAccountNonExpired());
        user.setAccountNonLocked(userDto.isAccountNonLocked());
        user.setCredentialsNonExpired(userDto.isCredentialsNonExpired());
        user.setEmail(userDto.getEmail());
        user.setEnabled(userDto.isEnabled());
        List<String> rolesArr = Arrays.asList(userDto.getRoles());
        List<Role> roleList = rolesArr.stream().map(role -> {
            Role byName = roleRepository.findByName(role);
            return byName;
        }).collect(Collectors.toList());
        user.setRoles(roleList);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setUsername(userDto.getUserName());
        return user;
    }

}
