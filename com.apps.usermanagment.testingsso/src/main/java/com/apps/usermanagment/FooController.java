package com.apps.usermanagment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FooController {

    @GetMapping("/foos")
    public String getFooResource() {
        return "foo";
    }

}
