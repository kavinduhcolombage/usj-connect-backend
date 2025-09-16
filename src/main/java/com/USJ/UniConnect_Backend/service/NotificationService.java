package com.USJ.UniConnect_Backend.service;

import com.USJ.UniConnect_Backend.dto.NotificationDto;
import com.USJ.UniConnect_Backend.entities.NotificationEntity;
import com.USJ.UniConnect_Backend.exception.JobPortalException;

import java.util.List;

public interface NotificationService {

    public void sendNotification(NotificationDto notificationDto) throws JobPortalException;

    public List<NotificationEntity> getUnreadNotifications(Long userId);

    public void readNotifications(Long id) throws JobPortalException;
}
