package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.boot.dto.SignUpForm;
import com.boot.dto.UnlockForm;
import com.boot.service.UserService;



@Controller
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String home() 
	{
		return "login";
	}

	@PostMapping("/signup")
	public String signupPage(@ModelAttribute("user") SignUpForm form,Model model) 
	{
		boolean status = this.userService.signUp(form);
		
		if(status)
		{
			model.addAttribute("successMsg","Account is created, Check your email");
		}
		else
		{
			model.addAttribute("errorMsg","Choose unique Email ");
		}
		return "signup";
	}
	
	@GetMapping("/signup")
	public String signupPage(Model model) 
	{
		model.addAttribute("user",new SignUpForm());
		return "signup";
	}
	
	@GetMapping("/forgot")
	public String forgotPassword()
    {
		return "forgot";
	}
	
	@PostMapping("/unlock")
	public String unlockPage(@ModelAttribute("unlock") UnlockForm form) {
		return "unlock";
	}
	
	@GetMapping("/unlock")
	public String unlockPage() {
		return "unlock";
	}

}