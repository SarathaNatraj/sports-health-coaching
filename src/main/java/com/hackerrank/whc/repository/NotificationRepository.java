package com.hackerrank.whc.repository;


import org.springframework.stereotype.Repository;

import com.hackerrank.whc.model.Notification;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificationRepository {
    private final List<Notification> notifications = new ArrayList<>();

    public void save(Notification notification) {
        notifications.add(notification);
    }

    public List<Notification> findByCustomerId(String customerId) {
        List<Notification> result = new ArrayList<>();
        for (Notification n : notifications) {
            if (n.getCustomerId().equals(customerId)) {
                result.add(n);
            }
        }
        return result;
    }
}
