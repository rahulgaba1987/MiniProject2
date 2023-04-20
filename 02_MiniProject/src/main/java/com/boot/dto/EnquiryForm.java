package com.boot.dto;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class EnquiryForm
{
    private int enquiryId;
	
	private String studentName;
	
	private String phoneNumber;
	
	private String classMode;
	
	private String courseName;
	
	private String enquiryStatus;
	
	
}
