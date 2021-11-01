package com.example.olxpostman;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class WebController {

	@RequestMapping("/login")
	public String authConnect(Model model, Principal principal) {
		return "auth";
	}
}
