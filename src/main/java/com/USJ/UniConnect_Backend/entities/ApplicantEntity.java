package com.USJ.UniConnect_Backend.entities;

import com.USJ.UniConnect_Backend.dto.ApplicantDto;
import com.USJ.UniConnect_Backend.dto.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Base64;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "application")
public class ApplicantEntity{

    private Long applicantId;
    private String name;
    private String email;
    private Long phone;
    private String website;
    private byte[] resume;
    private String coverLetter;
    private LocalDateTime timeStamp;
    private ApplicationStatus applicationStatus;
    private LocalDateTime interviewTime;

    public ApplicantDto toDto() {
        return new ApplicantDto(this.applicantId,this.name,this.email,this.phone,this.website,this.resume!=null? Base64.getEncoder().encodeToString(this.resume):null,this.coverLetter,this.timeStamp,this.applicationStatus,this.interviewTime);
    }
}



