package com.USJ.UniConnect_Backend.service.impl;

import com.USJ.UniConnect_Backend.dao.ProfileDao;
import com.USJ.UniConnect_Backend.dao.UserDao;
import com.USJ.UniConnect_Backend.dto.ProfileDto;
import com.USJ.UniConnect_Backend.entities.ProfileEntity;
import com.USJ.UniConnect_Backend.exception.JobPortalException;
import com.USJ.UniConnect_Backend.service.ProfileService;
import com.USJ.UniConnect_Backend.util.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("ProfileService")
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private UserDao userDao;

    @Override
    public Long createProfile(String email) throws JobPortalException {
        ProfileEntity profile = new ProfileEntity();
        profile.setId(Utilities.getNextSequence("profiles"));
        profile.setEmail(email);
        profile.setSkills(new ArrayList<>());
        profile.setExperience(new ArrayList<>());
        profile.setCertifications(new ArrayList<>());
        profileDao.save(profile);
        System.out.println("Profile Created: " + profile.toDto());
        return profile.getId();

    }

    @Override
    public ProfileDto getProfile(Long id) throws JobPortalException {
        return profileDao.findById(id).orElseThrow(() -> new JobPortalException("Profile_Not_Found")).toDto();
    }

    @Override
    public ProfileDto updateProfile(ProfileDto profileDto) throws JobPortalException {
        profileDao.findById(profileDto.getId()).orElseThrow(() -> new JobPortalException("Profile_Not_Found")).toDto();
        System.out.println("profile updated, id is : " + profileDto.getId());
        profileDao.save(profileDto.toEntity());
        return profileDto;
    }

    @Override
    public List<ProfileDto> getAllProfiles() {
        return profileDao.findAll().stream().map((x) -> x.toDto()).toList();
    }

    @Override
    public List<ProfileDto> getAllStudentsProfiles() {
        List<com.USJ.UniConnect_Backend.entities.UserEntity> allUsers = userDao.findAll();

        List<Long> studentProfileIds = allUsers.stream()
                .filter(user -> user.getRole() == com.USJ.UniConnect_Backend.dto.UserRole.STUDENT)
                .map(com.USJ.UniConnect_Backend.entities.UserEntity::getProfileId)
                .toList();

        List<ProfileEntity> studentProfiles = profileDao.findAllById(studentProfileIds);

        return studentProfiles.stream().map(ProfileEntity::toDto).toList();
    }

}
