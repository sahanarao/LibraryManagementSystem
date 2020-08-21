package com.libmgmtsys.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	@GetMapping("/Login")
	public String getRequest() {
		logger.info("Login method invoked");
		return "login";
	}

	@GetMapping("/Register")
	public String registerUser() {
		System.out.println("returning call from register user....");
		return "register";
	}

	@GetMapping("/search")
	public String searchDetails() {
		logger.info("Search method invoked");
		return "search";
	}

	@GetMapping("/")
	public String others() {
		return searchDetails();
	}
}
