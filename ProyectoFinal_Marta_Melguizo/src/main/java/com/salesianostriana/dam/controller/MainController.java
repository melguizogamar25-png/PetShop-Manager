package com.salesianostriana.dam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping ("/")
	public String index (){
		return "index";
	}
	
	// admin
    @GetMapping("/admin/index")
    public String adminIndex() {
        return "admin/index";
    }
 
    // user
    @GetMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }
}
