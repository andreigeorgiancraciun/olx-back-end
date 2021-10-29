package com.example.olxpostman;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class WebController {

	@RequestMapping("/securedPage")
	public String securedPage(Model model, Principal principal) {
		return "securedPage";
	}

	@RequestMapping("/")
	public String index(Model model, Principal principal) {
		return "index";
	}
}