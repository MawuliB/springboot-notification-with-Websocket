package com.websoket.notification.entities.request;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequest {
    private String content;
    private String ownerId;
    private String typeOfNotification;
    private boolean seen;
    private Date timestamp;
}
