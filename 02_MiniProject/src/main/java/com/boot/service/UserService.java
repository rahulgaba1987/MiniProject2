package com.boot.service;

import com.boot.dto.LoginForm;
import com.boot.dto.SignUpForm;
import com.boot.dto.UnlockForm;


public interface UserService 
{
	public boolean signUp(SignUpForm form);
	
	public boolean unlockUser(UnlockForm form);
	
	public boolean checkLogin(LoginForm login);
	
	public boolean forgotPassword(String email);
	
	public String emailCheck(String email);
	
	

}
