package com.boot.entity;


import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class UserEntity
{
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
     private int userId;
     
     private String userName;
     
     private String userEmail;
     
     private String userContact;
     
     private String password;
     
     @Column(columnDefinition = "varchar(255) default 'Locked'")
     private String userStatus;
     
     @OneToMany(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.EAGER)
     private List<StudentEnquiryEntity> enquiryList = new ArrayList<StudentEnquiryEntity>();
}
