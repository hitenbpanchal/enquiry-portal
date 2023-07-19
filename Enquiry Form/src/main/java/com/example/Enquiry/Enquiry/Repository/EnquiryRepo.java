package com.example.Enquiry.Enquiry.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.Enquiry.Enquiry.Model.EnquiryForm;

@Repository
public interface EnquiryRepo extends CrudRepository<EnquiryForm, Integer>{

	List<EnquiryForm> findByName(String name);
}
