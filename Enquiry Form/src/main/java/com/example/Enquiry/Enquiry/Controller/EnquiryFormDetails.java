package com.example.Enquiry.Enquiry.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Enquiry.Enquiry.Model.EnquiryForm;
import com.example.Enquiry.Enquiry.Service.EnquiryService;
@CrossOrigin("*")
@RestController
@RequestMapping("/enquiry")
public class EnquiryFormDetails {

	@Autowired
	EnquiryService enquiryservice;
	
	@GetMapping("/all")
	public ResponseEntity<List<EnquiryForm>> getFormDetails() {
		return new ResponseEntity<>(enquiryservice.getFormDetails(), HttpStatus.OK);
	}
	
	@GetMapping("/find/{id}")
	public Optional<EnquiryForm> getFormDetailsById(@PathVariable int id) {
		return enquiryservice.getFormDetailsById(id);
	}
	
	@PostMapping("/adddetails")
	public ResponseEntity<EnquiryForm> addDetails(@RequestBody EnquiryForm ef) {
		EnquiryForm enquiryForm = enquiryservice.saveOrUpdate(ef);
		return new ResponseEntity<EnquiryForm>(enquiryForm,HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteByID(@PathVariable("id") int id) {
		enquiryservice.deleteByID(id);
	}
	
	@GetMapping("/find/{name}")
	public List<EnquiryForm> getByName(@PathVariable String name) {
		return enquiryservice.findByName(name);
		
	}
}
