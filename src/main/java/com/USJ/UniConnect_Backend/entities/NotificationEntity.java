package com.USJ.UniConnect_Backend.entities;

import com.USJ.UniConnect_Backend.dto.NotificationDto;
import com.USJ.UniConnect_Backend.dto.NotificationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notification")
public class NotificationEntity  {

    private  Long id;
    private  Long userId;
    private String message;
    private String action;
    private String route;
    private NotificationStatus status;
    private LocalDateTime timestamp;

    public NotificationDto toDto(){
        return new NotificationDto(this.id,this.userId,this.message,this.action,this.route,this.status,this.timestamp);
    }



}
