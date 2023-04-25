package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class EnquiryController 
{
	@Autowired
	HttpSession session;
	
	@GetMapping("/logout")
	public String logout()
	{
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage()
	{
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiry()
	{
		return "add-enquiry";
	}
}
