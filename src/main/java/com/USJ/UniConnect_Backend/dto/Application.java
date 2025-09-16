package com.USJ.UniConnect_Backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Application {


    private Long id;
    private Long applicantId;
    private LocalDateTime interviewTime;
    private ApplicationStatus applicationStatus;




}
