package com.maju.notificationservice.kafka.service;

import com.maju.notificationservice.kafka.dto.BalanceUpdateEvent;
import com.maju.notificationservice.kafka.dto.UserRegistrationEvent;
import com.maju.notificationservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumerListener {

    private final EmailService emailService;

    @KafkaListener(topics = "user_registered_events", groupId = "notification-group")
    public void consumerUserRegisteredEvent(UserRegistrationEvent event) {
        log.info("Received user registered event {}", event);

        try {
            emailService.sendWelcomeEmail(event);

        } catch (Exception e) {

            log.error("Error while sending Welcome email to user registered event: {}", e.getMessage());

        }
    }

    @KafkaListener(topics = "balance-update-notification-events", groupId = "notification-group")
    public void consumerBalanceUpdateEvent(BalanceUpdateEvent event) {
        log.info("Received balance update event {}", event);

        try {
            emailService.sendTransactionAlertEmail(event);

        } catch (Exception e) {

            log.error("Error while sending Welcome email to user registered event: {}", e.getMessage());

        }
    }
}
