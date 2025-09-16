package com.USJ.UniConnect_Backend.service.impl;

import com.USJ.UniConnect_Backend.dao.JobDao;
import com.USJ.UniConnect_Backend.dto.*;
import com.USJ.UniConnect_Backend.entities.ApplicantEntity;
import com.USJ.UniConnect_Backend.entities.JobEntity;
import com.USJ.UniConnect_Backend.exception.JobPortalException;
import com.USJ.UniConnect_Backend.service.JobService;
import com.USJ.UniConnect_Backend.service.NotificationService;
import com.USJ.UniConnect_Backend.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("jobService")
public class JobServiceImpl implements JobService {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private NotificationService notificationService;

    @Override
    public JobDto postJob(JobDto jobDto) throws JobPortalException {
        if (jobDto.getId() == 0) {
            jobDto.setId(Utilities.getNextSequence("jobs"));
            jobDto.setPostTime(LocalDateTime.now());
            NotificationDto notificationDto = new NotificationDto();
            notificationDto.setAction("Job Posted");
            notificationDto.setMessage("Job Posted Successfully for:" + jobDto.getJobTitle() + "at" + jobDto.getCompany());
            notificationDto.setUserId(jobDto.getPostedBy());
            notificationDto.setRoute("/posted-jobs/" + jobDto.getId());
            notificationService.sendNotification(notificationDto);
        } else {
            JobEntity jobEntity = jobDao.findById(jobDto.getId())
                    .orElseThrow(() -> new JobPortalException("Job_Not_Found"));
            if (jobEntity.getJobStatus().equals(JobStatus.DRAFT) || jobDto.getJobStatus().equals(JobStatus.CLOSED))
                jobDto.setPostTime(LocalDateTime.now());
        }
        return jobDao.save(jobDto.toEntity()).toDto();

        // }

        // else{
        // JobEntity jobEntity = jobDao.findById(jobDto.getId()).orElseThrow(()->new
        // JobPortalException("Job_Not_Found"));
        // if(jobEntity.getJobStatus().equals(JobStatus.DRAFT) ||
        // jobDto.getJobStatus().equals(JobStatus.CLOSED))jobDto.setPostTime(LocalDateTime.now());
        // }

        // return jobDao.save(jobDto.toEntity()).toDto();

    }

    @Override
    public List<JobDto> getAllJobs() {
        return jobDao.findAll().stream().map((x) -> x.toDto()).toList();
    }

    @Override
    public JobDto getJob(Long id) throws JobPortalException {
        return jobDao.findById(id).orElseThrow(() -> new JobPortalException("Job_Not_Found")).toDto();
    }

    @Override
    public void applyJob(Long id, ApplicantDto applicantDto) throws JobPortalException {
        System.out.println("incomig applicant :" + applicantDto);
        JobEntity jobEntity = jobDao.findById(id).orElseThrow(() -> new JobPortalException("Job_Not_Found"));
        List<ApplicantEntity> applicantEntities = jobEntity.getApplicants();
        if (applicantEntities == null)
            applicantEntities = new ArrayList<>();
        if (applicantEntities.stream().filter((x) -> x.getApplicantId() == applicantDto.getApplicantId()).toList()
                .size() > 0)
            throw new JobPortalException("Job Applied Already");
        applicantDto.setApplicationStatus(ApplicationStatus.APPLIED);
        applicantEntities.add(applicantDto.toEntity());
        jobEntity.setApplicants(applicantEntities);
        jobDao.save(jobEntity);

    }

    @Override
    public List<JobDto> getJobsPostedBy(Long id) {
        return jobDao.findByPostedBy(id).stream().map((x) -> x.toDto()).toList();
    }

    @Override
    public void changeAppStatus(Application application) throws JobPortalException {
        System.out.println("incoming application : " + application);
        JobEntity jobEntity = jobDao.findById(application.getId())
                .orElseThrow(() -> new JobPortalException("Job_Not_Found"));
        List<ApplicantEntity> applicantEntities = jobEntity.getApplicants().stream().map((x) -> {
            if (application.getApplicantId() == x.getApplicantId()) {
                x.setApplicationStatus(application.getApplicationStatus());
                if (application.getApplicationStatus().equals(ApplicationStatus.INTERVIEWING)) {
                    x.setInterviewTime(application.getInterviewTime());
                    NotificationDto notificationDto = new NotificationDto();
                    notificationDto.setAction("Interview Scheduled");
                    notificationDto.setMessage("Interview scheduled for job id:" + application.getId());
                    notificationDto.setUserId(application.getApplicantId());
                    notificationDto.setRoute("/job-history");
                    try {
                        notificationService.sendNotification(notificationDto);
                    } catch (JobPortalException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return x;
        }).toList();
        jobEntity.setApplicants(applicantEntities);
        jobDao.save(jobEntity);
    }
}
