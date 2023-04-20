package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entity.EnquiryStatusEntity;

public interface EnquiryStatusRepository extends JpaRepository<EnquiryStatusEntity, Integer>
{

}
