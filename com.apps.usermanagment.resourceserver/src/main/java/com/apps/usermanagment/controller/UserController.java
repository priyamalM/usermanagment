package com.apps.usermanagment.controller;

import com.apps.usermanagment.PageableUserDto;
import com.apps.usermanagment.UserDto;
import com.apps.usermanagment.model.User;
import com.apps.usermanagment.repository.UserDetailRepository;
import com.apps.usermanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class UserController {

    @Autowired
    UserDetailRepository userDetailRepository;

    @Autowired
    UserService userService;

    @PreAuthorize("#oauth2.hasScope('read')")
//    @RolesAllowed("ROLE_admin")
    @RequestMapping(method = RequestMethod.GET, value = "/users/extra")
    @ResponseBody
    public Map<String, Object> getExtraInfo(Authentication auth) {
        OAuth2AuthenticationDetails oauthDetails = (OAuth2AuthenticationDetails) auth.getDetails();
        Map<String, Object> details = (Map<String, Object>) oauthDetails.getDecodedDetails();
        System.out.println("User organization is " + details.get("organization"));
        return details;
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET,value = "/users")
    public PageableUserDto getUsers(@RequestParam("page") Optional<Integer> page,
                                    @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);

        PageRequest pageRequest = PageRequest.of(currentPage - 1, pageSize);
        Page<User> paginatedUser = userDetailRepository.findAll(pageRequest);

        PageableUserDto pageableUserDto = new PageableUserDto();
        int totalPages = paginatedUser.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            pageableUserDto.setTotalPages(pageNumbers);
        }

        List<UserDto> userDtoList = paginatedUser.stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setIdcol(user.getId()+"");
            userDto.setUserName(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setAccountNonExpired(user.isAccountNonExpired());
            userDto.setAccountNonLocked(user.isAccountNonLocked());
            userDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
            userDto.setEnabled(user.isEnabled());
            return userDto;
        }).collect(Collectors.toList());
        pageableUserDto.setUserDtoList(userDtoList);
        return pageableUserDto;
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @ResponseBody
    @RequestMapping(value = "/userlist",method = RequestMethod.GET)
    public Page<User> getUsers(Pageable pageable){
        Page<User> allUsers = userService.findAllUsers(pageable);
//        List<UserDto> collect = allUsers.stream().map(user -> {
//            UserDto userDtoFromUser = userService.getUserDtoFromUser(user);
//            return userDtoFromUser;
//        }).collect(Collectors.toList());
        //Page<UserDto> userDtoPage = new PageImpl<UserDto>(collect);
        return allUsers;
    }


    @PostMapping("/user")
    @ResponseBody
    @RolesAllowed("ROLE_admin")
    public UserDto saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }

}