package com.example.olxpostman;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {

	@RequestMapping("/")
	public String welcome() {
		return "secured";
	}

}
