package com.USJ.UniConnect_Backend.service;

import com.USJ.UniConnect_Backend.dto.ProfileDto;
import com.USJ.UniConnect_Backend.exception.JobPortalException;

import java.util.List;

public interface ProfileService {

    public Long createProfile(String email)throws JobPortalException;
    public ProfileDto getProfile(Long id)throws JobPortalException;
    public ProfileDto updateProfile(ProfileDto profileDto)throws JobPortalException;

    public List<ProfileDto> getAllProfiles();
    public List<ProfileDto> getAllStudentsProfiles();
}
