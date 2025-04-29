package com.nguyenhan.hdv.service;

import com.nguyenhan.hdv.model.Notification;
import com.nguyenhan.hdv.model.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public Notification sendNotification(NotificationRequest notificationRequest) {
        // Logic to send notification (via email, SMS, etc.)
        Notification notification = new Notification();
        notification.setMessage(notificationRequest.getMessage());
        notification.setUserId(notificationRequest.getUserId());
        // Send the notification
        return notification;
    }
}

