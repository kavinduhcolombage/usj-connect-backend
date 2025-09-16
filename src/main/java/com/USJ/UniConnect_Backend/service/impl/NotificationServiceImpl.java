package com.USJ.UniConnect_Backend.service.impl;

import com.USJ.UniConnect_Backend.dao.NotificationDao;
import com.USJ.UniConnect_Backend.dto.NotificationDto;
import com.USJ.UniConnect_Backend.dto.NotificationStatus;
import com.USJ.UniConnect_Backend.entities.NotificationEntity;
import com.USJ.UniConnect_Backend.exception.JobPortalException;
import com.USJ.UniConnect_Backend.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.USJ.UniConnect_Backend.util.Utilities;

import java.time.LocalDateTime;
import java.util.List;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationDao notificationDao;

    @Override
    public void sendNotification(NotificationDto notificationDto) throws JobPortalException {
        notificationDto.setId(Utilities.getNextSequence("notification"));
        notificationDto.setStatus(NotificationStatus.UNREAD);
        notificationDto.setTimestamp(LocalDateTime.now());
        notificationDao.save(notificationDto.toEntity());
    }

    @Override
    public List<NotificationEntity> getUnreadNotifications(Long userId) {
        return notificationDao.findByUserIdAndStatus(userId, NotificationStatus.UNREAD);
    }

    @Override
    public void readNotifications(Long id) throws JobPortalException {
        NotificationEntity notificationEntity = notificationDao.findById(id)
                .orElseThrow(() -> new JobPortalException("No Notification found"));
        notificationEntity.setStatus(NotificationStatus.READ);
        notificationDao.save(notificationEntity);
    }
}
