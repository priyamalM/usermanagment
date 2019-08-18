package com.apps.usermanagment.controllers;

import com.apps.usermanagment.PageWrapper;
import com.apps.usermanagment.PageableUserDto;
import com.apps.usermanagment.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);
        ResponseEntity<PageableUserDto> getEntity = restTemplate.exchange("http://localhost:8082/spring-security-oauth-resource/users?page="+currentPage+"&size="+pageSize
                , HttpMethod.GET
                , null
                , PageableUserDto.class);
        PageableUserDto body = getEntity.getBody();
        PageImpl<UserDto> userDtos = new PageImpl<>(body.getUserDtoList(), PageRequest.of(currentPage,pageSize), body.getTotalPages().size());
        model.addAttribute("paginatedUser",userDtos);
        model.addAttribute("pageNumbers", body.getTotalPages());
        model.addAttribute("pageSize",body.getTotalPages().size());
        return "search";
    }

    @RequestMapping(value = "/showUsers")
    public String showUsers(@ModelAttribute("userob") UserDto userDto
            , Model model
            ,@RequestParam("page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);
        ResponseEntity<Page<UserDto>> getEntity = restTemplate.exchange("http://localhost:8082/spring-security-oauth-resource/userlist?page=" + currentPage + "&size=" + pageSize
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<Page<UserDto>>() {});
        List<UserDto> pageBody = getEntity.getBody().getContent();
        PageImpl pages = new PageImpl(pageBody);
        PageWrapper<UserDto> userDtoPageWrapper = new PageWrapper<>(pages,"/userlist");
        model.addAttribute("page",userDtoPageWrapper);
        return "pageview";
    }


    @ModelAttribute("userob")
    private UserDto userDto(){
        UserDto userDto = new UserDto();
        String []roles = {"admin","user","super admin"};
        userDto.setRoles(roles);
        return userDto;
    }

}