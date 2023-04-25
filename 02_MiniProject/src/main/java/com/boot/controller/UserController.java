package com.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.boot.dto.ForgotFormDTO;
import com.boot.dto.LoginForm;
import com.boot.dto.SignUpForm;
import com.boot.dto.UnlockForm;
import com.boot.service.UserService;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpSession;



@Controller
public class UserController 
{
	@Autowired
	private UserService userService;
	
	@Autowired
	HttpSession session;
	
	@GetMapping("/login")
	public String home(Model model) 
	{
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPage(@ModelAttribute LoginForm loginPage,Model model) 
	{
		model.addAttribute("loginForm", new LoginForm());
		 String status = this.userService.checkLogin(loginPage);
		
		 if(status.contains("success"))
		 {
			 return "redirect:/dashboard";
		 }
		 else
		 {
			 model.addAttribute("errorMsg", status);
		 }
		session.setAttribute("userId", "loginPage.getUserEmail()");
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
	@PostMapping("/forgotPsw")
	public String forgotPswd(@RequestParam("userEmail") String userEmail,Model model)
    {
		
		String emailMsg=this.userService.forgotPassword(userEmail);
		model.addAttribute("msg",emailMsg);
		
		return "forgot";
	}
	
	
	@PostMapping("/unlock")
	public String unlockPage(@ModelAttribute("unlock") UnlockForm form,Model model) {
		
		if(form.getNewPassword().equals(form.getConfirmPassword()))
		{
			boolean status = this.userService.unlockUser(form);
			
			if(status)
			{
				model.addAttribute("successMsg","Your account unlocked successfully");
			}
			else
			{
				model.addAttribute("errorMsg","Given temporary password is incorrect");
			}
		}
		else
		{
			model.addAttribute("errorMsg","New Password and confirm password should be same");
		}
		
		
		
		return "unlock";
	}
	
	@GetMapping("/unlock")
	public String unlockPage(@RequestParam("email") String email,Model model) 
	{
		UnlockForm form = new UnlockForm();
		form.setEmail(email);
		
		model.addAttribute("unlock",form);
		
		
		return "unlock";
	}

}
