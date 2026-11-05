package com.maju.notificationservice.service;

import com.maju.notificationservice.kafka.dto.BalanceUpdateEvent;
import com.maju.notificationservice.kafka.dto.UserRegistrationEvent;

public interface EmailService {

    void sendWelcomeEmail(UserRegistrationEvent event);

    void sendTransactionAlertEmail(BalanceUpdateEvent event);
}
