package com.USJ.UniConnect_Backend.controller;


import com.USJ.UniConnect_Backend.dto.ResponseDto;
import com.USJ.UniConnect_Backend.entities.NotificationEntity;
import com.USJ.UniConnect_Backend.exception.JobPortalException;
import com.USJ.UniConnect_Backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Validated
@RequestMapping("api/v1/notification")
public class NotificationController {

    @Autowired
    public NotificationService notificationService;

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<NotificationEntity>> getNotifications(@PathVariable Long userId){
        return new ResponseEntity<>(notificationService.getUnreadNotifications(userId), HttpStatus.OK);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<ResponseDto> readNotification(@PathVariable Long id) throws JobPortalException {
        notificationService.readNotifications(id);
        return new ResponseEntity<>(new ResponseDto("Success"), HttpStatus.OK);
    }

}
