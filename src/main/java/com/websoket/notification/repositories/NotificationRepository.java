package com.websoket.notification.repositories;

import com.websoket.notification.entities.Notification;
import com.websoket.notification.entities.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, String> {

    @Query("SELECT n FROM Notification n WHERE n.ownerId = :ownerId ORDER BY n.timestamp DESC")
    List<Notification> findByOwnerId(Person ownerId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Notification n WHERE n.id = :id")
    void deleteByIdString(@Param("id") String id);
}