package com.slt.documentmanagment.controllers;

import com.slt.documentmanagment.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class CreateUserController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.POST, value = "/create/user")
    public String createUser(@ModelAttribute("userob") UserDto userDto, Model model) {
        HttpEntity<UserDto> userDtoHttpEntity = new HttpEntity<>(userDto);
        ResponseEntity<UserDto> exchange = restTemplate.exchange("http://localhost:8082/spring-security-oauth-resource/user"
                , HttpMethod.POST
                , userDtoHttpEntity
                , UserDto.class);
        UserDto savedUser = exchange.getBody();
        return "search";
    }

}
