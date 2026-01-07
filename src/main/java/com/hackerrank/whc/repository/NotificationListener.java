package com.hackerrank.whc.repository;


import com.hackerrank.whc.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Autowired
    private NotificationRepository repository;

    // Listen to the "coach.notifications" queue
  //  @JmsListener(destination = "yoga.notifications")
    public void receiveMessage(String message) {
        System.out.println("Received message -> Notification Listener : " + message);

        // For demo purposes, assume message format: customerId:messageText
        String[] parts = message.split(":", 2);
        if (parts.length == 2) {
            String customerId = parts[0];
            String text = parts[1];

            Notification notification = new Notification();
            notification.setCustomerId(customerId);
            notification.setMessage(text);

            // Save to repository
            repository.save(notification);
        }
    }
}

