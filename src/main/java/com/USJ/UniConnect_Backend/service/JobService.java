package com.USJ.UniConnect_Backend.service;

import com.USJ.UniConnect_Backend.dto.ApplicantDto;
import com.USJ.UniConnect_Backend.dto.Application;
import com.USJ.UniConnect_Backend.dto.JobDto;
import com.USJ.UniConnect_Backend.exception.JobPortalException;
import jakarta.validation.Valid;

import java.util.List;

public interface JobService {


   public  JobDto postJob(@Valid JobDto jobDto) throws JobPortalException;

    public List<JobDto> getAllJobs();

    public  JobDto getJob(Long id) throws JobPortalException;

    public  void applyJob(Long id, ApplicantDto applicantDto)throws JobPortalException;

   public  List<JobDto> getJobsPostedBy(Long id);

   public  void changeAppStatus(Application application)throws JobPortalException;
}
