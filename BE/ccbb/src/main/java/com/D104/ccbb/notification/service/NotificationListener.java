package com.D104.ccbb.notification.service;

import com.D104.ccbb.notification.dto.NotificationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final NotificationService notificationService;

    public void handleNotification(NotificationRequestDto requestDto) {
//        notificationService.send
    }
}
