package com.boot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class UnlockForm 
{
	private String email;
	
	private String temporaryPassword;
	
	private String newPassword;
	
	private String confirmPassword;

}
