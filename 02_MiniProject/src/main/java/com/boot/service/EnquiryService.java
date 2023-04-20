package com.boot.service;

import java.util.List;

import com.boot.dto.DashboardResponse;
import com.boot.dto.EnquiryForm;
import com.boot.dto.EnquirySearchCriteria;

public interface EnquiryService 
{
    public boolean addEnquiry(EnquiryForm enquiry);
    
    public List<EnquiryForm> getAllEnquiries(int enquiryId);
    
    public List<EnquiryForm> getEnquiriesBySearchCriteria(int enquiryId,EnquirySearchCriteria criteria);
    

    
    public DashboardResponse getDashoboardResponse();
    
}
