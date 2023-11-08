package com.D104.ccbb.notification.domain;

import com.D104.ccbb.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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

    @Column(name = "nt_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User receiver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User sender;

    public void setIsReadTrue() {
        this.isRead = true;
    }
}
