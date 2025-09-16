package com.USJ.UniConnect_Backend.controller;

import com.USJ.UniConnect_Backend.dto.ApplicantDto;
import com.USJ.UniConnect_Backend.dto.Application;
import com.USJ.UniConnect_Backend.dto.JobDto;
import com.USJ.UniConnect_Backend.dto.ResponseDto;
import com.USJ.UniConnect_Backend.exception.JobPortalException;
import com.USJ.UniConnect_Backend.service.JobService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("api/v1/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    @PostMapping("/post")
    public ResponseEntity<JobDto> postJob(@RequestBody @Valid JobDto jobDto) throws JobPortalException {
        return new ResponseEntity<>(jobService.postJob(jobDto), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<JobDto>> getAllJobs() throws JobPortalException {
        return new ResponseEntity<>(jobService.getAllJobs(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<JobDto> getJob(@PathVariable Long id) throws JobPortalException {
        return new ResponseEntity<>(jobService.getJob(id), HttpStatus.OK);
    }

    @PostMapping("/apply/{id}")
    public ResponseEntity<ResponseDto> applyJob(@PathVariable Long id, @RequestBody ApplicantDto applicantDto)
            throws JobPortalException {
                jobService.applyJob(id, applicantDto);
                return new ResponseEntity<>(new ResponseDto("Applied Successfully"), HttpStatus.OK);
    }

    @GetMapping("/postedBy/{id}")
    public ResponseEntity<List<JobDto>> getJobsPostedBy(@PathVariable Long id) throws JobPortalException {
        return new ResponseEntity<>(jobService.getJobsPostedBy(id), HttpStatus.OK);
    }

    @PostMapping("/changeAppStatus")
    public ResponseEntity<ResponseDto> changeAppStatus(@RequestBody Application application) throws JobPortalException {

        jobService.changeAppStatus(application);
        return new ResponseEntity<>(new ResponseDto("Application Status changed Successfully"), HttpStatus.OK);

    }

}
