package com.D104.ccbb.notification.dto;

import com.D104.ccbb.notification.domain.Notification;
import com.D104.ccbb.notification.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDto {

    private Integer notificationId;
    private String content;
    private String url;
    private Boolean isRead;
    private NotificationType notificationType;
    private LocalDateTime createDate;
    private String senderUserNickname;

    public static NotificationResponseDto fromEntity(Notification notification) {
        return NotificationResponseDto.builder()
                .notificationId(notification.getNotificationId())
                .content(notification.getContent())
                .url(notification.getUrl())
                .isRead(notification.getIsRead())
                .notificationType(notification.getNotificationType())
                .createDate(notification.getCreateDate())
                .senderUserNickname(notification.getSender().getNickname())
                .build();
    }

}
