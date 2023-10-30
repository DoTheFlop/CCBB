package com.D104.ccbb.notification.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id", nullable = false)
    Integer notificationId;

    @Column(columnDefinition = "varchar(100)")
    private String content;

    @Column(columnDefinition = "varchar(50)")
    private String url;

    @Column(nullable = false)
    private Boolean isRead;

    @Column(nullable = false)
    private NotificationType nt_type;
}
