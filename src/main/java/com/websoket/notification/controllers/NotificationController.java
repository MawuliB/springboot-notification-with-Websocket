package com.websoket.notification.controllers;

import com.websoket.notification.entities.Notification;
import com.websoket.notification.entities.request.NotificationRequest;
import com.websoket.notification.services.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/notification")
    public void processMessage(
            @Payload NotificationRequest notification
            ) {
        Notification savedNotification = notificationService.saveNotification(notification);
        messagingTemplate.convertAndSendToUser(
                notification.getOwnerId(),
                "/topic/notification",
                savedNotification);
    }

    @GetMapping("/notification/{id}")
    public List<Notification> getNotificationByOwnerId(
            @PathVariable String id
    ) {
        return notificationService.getNotificationByOwnerId(id);
    }

    @PostMapping("/notification/seen/all/{id}")
    public ResponseEntity<?> setAllNotificationAsSeenByOwnerId(
            @PathVariable String id
    ) {
        notificationService.setAllNotificationAsSeenByOwnerId(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/notification/seen/{id}")
    public ResponseEntity<?> setNotificationAsSeenById(
            @PathVariable String id
    ) {
        notificationService.setNotificationAsSeenById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/notification/{id}")
    public ResponseEntity<?> deleteNotificationById(
            @PathVariable String id
    ) {
        notificationService.deleteNotificationById(id);
        return ResponseEntity.ok().build();
    }


}
