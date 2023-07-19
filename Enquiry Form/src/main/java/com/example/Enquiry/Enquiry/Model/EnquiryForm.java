package com.example.Enquiry.Enquiry.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "EnquiryForm")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EnquiryForm {


	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String email;

//	@JsonFormat(pattern = "dd-mm-yyyy" , shape = Shape.STRING)
//	@Column
	private String dob;
	private String address;
	private String collegeName;
	@Column
	private String qualification;
	private String passOutYear;
	private String contactNo;
	private String currentJobTitle;
	private Integer experience;
	private String courseName;
	private String whenToStart;
	private String batchTime;
	private Boolean isPlacement;
	private Boolean speakEnglish;

}
