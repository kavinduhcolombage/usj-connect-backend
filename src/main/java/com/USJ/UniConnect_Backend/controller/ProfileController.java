package com.USJ.UniConnect_Backend.controller;


import com.USJ.UniConnect_Backend.dto.ProfileDto;
import com.USJ.UniConnect_Backend.exception.JobPortalException;
import com.USJ.UniConnect_Backend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("api/v1/profile")
public class  ProfileController {

    @Autowired
    private ProfileService profileService;


    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long  id)throws JobPortalException{

        return new ResponseEntity<>(profileService.getProfile(id), HttpStatus.OK);

    }
    @GetMapping("/getAll")
    public ResponseEntity<List<ProfileDto>> getAllProfiles()throws JobPortalException{

        return new ResponseEntity<>(profileService.getAllProfiles(), HttpStatus.OK);

    }
    @PutMapping("/update")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody ProfileDto  profileDto)throws JobPortalException{

        return new ResponseEntity<>(profileService.updateProfile(profileDto), HttpStatus.OK);

    }
    
}
