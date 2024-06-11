package com.websoket.notification.services;

import com.websoket.notification.entities.Notification;
import com.websoket.notification.entities.NotificationType;
import com.websoket.notification.entities.Person;
import com.websoket.notification.entities.request.NotificationRequest;
import com.websoket.notification.repositories.NotificationRepository;
import com.websoket.notification.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final PersonRepository personRepository;

    public Notification saveNotification(NotificationRequest notification) {
        log.error("Notification: {}", notification.getOwnerId());
        Notification newNotification = Notification.builder()
                .ownerId(personRepository.findById(Integer.parseInt(notification.getOwnerId())).get())
                .content(notification.getContent())
                .seen(false)
                .typeOfNotification(NotificationType.PUSH_NOTIFICATION)
                .timestamp(notification.getTimestamp())
                .build();
        return notificationRepository.save(newNotification);
    }

    public List<Notification> getNotificationByOwnerId(String id) {
        Person owner = personRepository.findById((int) Long.parseLong(id)).orElseThrow();
        return notificationRepository.findByOwnerId(owner);
    }

    public void setAllNotificationAsSeenByOwnerId(String id) {
        Person owner = personRepository.findById((int) Long.parseLong(id)).orElseThrow();
        List<Notification> notifications = notificationRepository.findByOwnerId(owner);
        notifications.forEach(notification -> {
            notification.setSeen(true);
            notificationRepository.save(notification);
        });
    }

    public void setNotificationAsSeenById(String id) {
        Notification notification = notificationRepository.findById(id).orElseThrow();
        notification.setSeen(true);
        notificationRepository.save(notification);
    }

    public void deleteNotificationById(String id) {
        notificationRepository.deleteByIdString(id);
    }
}
