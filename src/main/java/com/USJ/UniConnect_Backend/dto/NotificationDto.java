package com.USJ.UniConnect_Backend.dto;

import com.USJ.UniConnect_Backend.entities.NotificationEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    private  Long id;
    private  Long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus status;
    private LocalDateTime timestamp;

    public NotificationEntity toEntity(){
        return new NotificationEntity(this.id,this.userId,this.message,this.action,this.route,this.status,this.timestamp);
    }
}
