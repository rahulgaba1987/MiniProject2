package com.boot.serviceimplementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.dto.LoginForm;
import com.boot.dto.SignUpForm;
import com.boot.dto.UnlockForm;
import com.boot.entity.UserEntity;
import com.boot.repository.UserRepository;
import com.boot.service.UserService;
import com.boot.util.EmailUtils;
import com.boot.util.PwdUtils;

@Service
public class UserServiceImpl  implements UserService
{
   
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailUtils emailUtils;
	
	@Override
	public boolean signUp(SignUpForm form)
	{
		
		UserEntity userEmail = userRepository.findByUserEmail(form.getUserEmail());
		
		if(userEmail!=null)
		{
			return false;
		}
		
		// TODO: Copy data binding obj to entity object
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(form, user);
		// generate password
		String tempPassword = PwdUtils.generateRandomPassword();
		user.setPassword(tempPassword);
		
		// set account status locked
		user.setUserStatus("Locked");
		
		// insert record
		this.userRepository.save(user);
		// send email to unlock the account
		
		String to = form.getUserEmail();
		
		String subject= "Unlock your account | RKG";
		
		StringBuffer body = new StringBuffer("");
		body.append("<h1> Use below temporary password to unlock your account </h1>");
		
		body.append("<br>");
		
		body.append("Temporray Password : "+tempPassword);
		
		body.append("<br>");
		
		
		body.append("<a href=\"http://localhost:8080/unlock?email="+to+"\">Click Here To Unlock Your Account</a>");
		
		
		emailUtils.sendEmail(to,subject, body.toString());
	
		return true;
	}

	@Override
	public boolean unlockUser(UnlockForm form) 
	{
		UserEntity entity = this.userRepository.findByUserEmail(form.getEmail());
		
		if(entity.getPassword().equals(form.getTemporaryPassword()))
		{     
			entity.setPassword(form.getNewPassword());
			entity.setUserStatus("UnLocked");
			this.userRepository.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public String checkLogin(LoginForm login)
	{
		 UserEntity userEntity = this.userRepository.findByUserEmailAndPassword(login.getUserEmail(),login.getPassword());
		
		 if(userEntity==null)
		 {
			 return "invalid Credentials";
		 }
		 if(userEntity.getUserStatus().equals("Locked"))
		 {
			 return "Your Account Locked";
		 }
		 return "success";
	}

	@Override
	public String forgotPassword(String email) 
	{
		
		if(email==null)
		{
			return "Invalid Email ID";
		}
				
         UserEntity userEntity = this.userRepository.findByUserEmail(email);
  
         if(userEntity!=null)
         {
        	 String to = userEntity.getUserEmail();
     		
     		String subject= "Recoved your Password |";
     		
     		StringBuffer body = new StringBuffer("");
     		body.append("<h1> Use below  password to unlock your account </h1>");
     		
     		body.append("<br>");
     		
     		body.append("Recovered Password : "+userEntity.getPassword());
     		
     		body.append("<br>");
     		
     		
     		body.append("<a href=\"http://localhost:8080/login\">Click Here To Unlock Your Account</a>");
     		
     		
     		boolean status= emailUtils.sendEmail(to,subject, body.toString());
            if(status)
            {
            	return "Password sent to your E-email";
            }
            else
            {
            	 return "Email does not exist.";
            }
     		
         }
         else
         {
        	 return "Email Id does not exist";
         }
		
		
		
	
	}

	@Override
	public String emailCheck(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
