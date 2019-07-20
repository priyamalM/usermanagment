package com.slt.documentmanagment.controllers;

import com.slt.documentmanagment.UserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CreateUserController {

    @RequestMapping(method = RequestMethod.POST,value = "/create/user")
    public String createUser(@ModelAttribute("userob")UserDto userDto, Model model){
        return "search";
    }

}
