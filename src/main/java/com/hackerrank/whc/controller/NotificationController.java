package com.hackerrank.whc.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import com.hackerrank.whc.model.Notification;
import com.hackerrank.whc.repository.NotificationRepository;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

//    @Autowired
//    private JmsTemplate jmsTemplate;

    @Autowired
    private NotificationRepository repository;

    private static final String QUEUE = "yoga.notifications";

    // Coach sends notification
    @PostMapping("/send")
    public String sendNotification(@RequestBody Notification notification) {
        // Save to repository
        repository.save(notification);

        // Send to JMS queue
//        jmsTemplate.convertAndSend(QUEUE, notification.getMessage());
        return "Notification sent to customer: " + notification.getCustomerId();
    }

    // Customer receives all notifications from repository
    @GetMapping("/customer/{id}")
    public List<Notification> getNotifications(@PathVariable("id") String customerId) {
        return repository.findByCustomerId(customerId);
    }
}

