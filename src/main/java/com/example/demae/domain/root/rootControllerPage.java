package com.example.demae.domain.root;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class rootControllerPage {

    @GetMapping
    public String indexPage(){ return "root/index";}

    @GetMapping("/error")
    public String errorPage(){ return "root/error"; }
}
