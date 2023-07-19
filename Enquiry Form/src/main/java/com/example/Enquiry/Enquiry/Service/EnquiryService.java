package com.example.Enquiry.Enquiry.Service;

import com.example.Enquiry.Enquiry.Model.EnquiryForm;
import com.example.Enquiry.Enquiry.Repository.EnquiryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class EnquiryService {

	@Autowired
	EnquiryRepo enquiryrepo;

	public List<EnquiryForm> getFormDetails() {
		List<EnquiryForm> enquiryform = new ArrayList<>();
		enquiryrepo.findAll().forEach(enquiryform::add);
		return enquiryform;
	}

	public Optional<EnquiryForm> getFormDetailsById(int id) {
		Optional<EnquiryForm> enquiryform  = enquiryrepo.findById(id);
			return enquiryform;
	}

	public EnquiryForm saveOrUpdate(EnquiryForm ef) {
		return enquiryrepo.save(ef);
	}

	public void deleteByID(int id) {
		enquiryrepo.deleteById(id);
	}

	public List<EnquiryForm> findByName(String name) {
		return enquiryrepo.findByName(name);
	}

	private ArrayList<String> getTimeslot(boolean isCurrentDay) {
		ArrayList<String> result = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		if(!isCurrentDay) {
			calendar.set(Calendar.HOUR_OF_DAY, 9);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
		}
		for (int i = 0; i < 9; i++) {
			String day1 = sdf.format(calendar.getTime());
			
			// add 30 minute to the current time
			calendar.add(Calendar.MINUTE, 30);
			String day2 = sdf.format(calendar.getTime());
			
			String day = day1 + " - " + day2;
			result.add(i, day);
		}
		return result;
	}
}

