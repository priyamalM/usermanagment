package com.slt.documentmanagment.controllers;

import com.slt.documentmanagment.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DashboardController {

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String createUserController(@ModelAttribute("userob") UserDto userDto, Model model){
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