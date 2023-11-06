package com.D104.ccbb.notification.repo;

import com.D104.ccbb.notification.domain.Notification;
import com.D104.ccbb.notification.dto.NotificationResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Integer> {

    List<Notification> findAllByReceiverUserIdOrderByCreateDateDesc(Integer userId);
}
