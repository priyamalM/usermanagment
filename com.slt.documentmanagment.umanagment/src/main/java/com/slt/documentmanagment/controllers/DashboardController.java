package com.slt.documentmanagment.controllers;

import com.slt.documentmanagment.PageableUserDto;
import com.slt.documentmanagment.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Controller
public class DashboardController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String createUserController(@ModelAttribute("userob") UserDto userDto
            , Model model
            ,@RequestParam("page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size){
        ResponseEntity<PageableUserDto> getEntity = restTemplate.exchange("http://localhost:8082/spring-security-oauth-resource/users", HttpMethod.GET,null, PageableUserDto.class);
        PageableUserDto body = getEntity.getBody();
        model.addAttribute("paginatedUser",body.getUserDtoList());
        model.addAttribute("pageNumbers", body.getTotalPageSize());
        return "search";
    }

    @ModelAttribute("userob")
    private UserDto userDto(){
        UserDto userDto = new UserDto();
        String []roles = {"admin","user","super admin"};
        userDto.setRoles(roles);
        return userDto;
    }

}