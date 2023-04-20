package com.boot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class EnquirySearchCriteria 
{
    private String classMode;
	
	private String courseName;
	
	private String enquiryStatus;

}
