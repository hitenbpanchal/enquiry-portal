package com.api.gateway.payloads;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EnquiryForm {
    @NotEmpty(message = "Name is mandatory")
    private String name;
    @NotEmpty(message = "Email is mandatory")
    private String email;
    @NotEmpty(message = "Date of birth is mandatory")
    private String dob;
    @NotEmpty(message = "Address is mandatory")
    private String address;
    private String collegeName;
    @NotEmpty(message = "Qualification is mandatory")
    private String qualification;
    private String passOutYear;
    @NotEmpty(message = "Contact number is mandatory")
    private String contactNo;
    private String currentJobTitle;
    private Integer experience;
    @NotEmpty(message = "Enrolled course name is mandatory")
    private String courseName;
    private String whenToStart;
    private String batchTime;
    private Boolean isPlacement;
    private Boolean speakEnglish;
}
