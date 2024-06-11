package com.websoket.notification.services;

import com.websoket.notification.entities.Notification;
import com.websoket.notification.entities.Person;
import com.websoket.notification.entities.request.NotificationRequest;
import com.websoket.notification.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledEvents {

    private final PersonRepository personRepository;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationService notificationService;

    @Scheduled(cron = "0 0 0 * * *") // every day at midnight
    public void sendHelloMessage(){ // send a hello message to all users
        List<Person> people = (List<Person>) personRepository.findAll();
        for (Person person : people) {
            NotificationRequest notification = new NotificationRequest();
            notification.setOwnerId(String.valueOf(person.getId()));
            notification.setContent("Hello " + person.getName() + "!");
            notification.setTimestamp(new Date());
            Notification savedNotification = notificationService.saveNotification(notification);
            simpMessagingTemplate.convertAndSendToUser(
                    String.valueOf(person.getId()),
                    "/topic/notification",
                    savedNotification
            );
        }
    }
}
