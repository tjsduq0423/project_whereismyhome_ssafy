package com.whereismyhome.main;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ssafy/**")
public class VueController {

    @GetMapping
    public String goIndex() {
        return "foward:/index.html";
    }
}
