package com.boot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class DashboardResponse 
{
	private int totalEnquiries;
	
	private int totalEnrolled;
	
	private int lostEnquiries;
	

}
