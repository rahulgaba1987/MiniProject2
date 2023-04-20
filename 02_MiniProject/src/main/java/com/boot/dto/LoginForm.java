package com.boot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class LoginForm 
{
	 private String userEmail;
	 
	 private String password;

}
