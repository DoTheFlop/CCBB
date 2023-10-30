package com.D104.ccbb.notification.service;

import com.D104.ccbb.notification.repo.EmitterRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotifiactionService {

    private final EmitterRepo emitterRepo;
    private Long DEFAULT_TIMEOUT = 60L * 1000L * 60L;

    public SseEmitter subscribe(Integer userId, String lastEventId) {

        String emitterId = makeTimeIncludeId(userId);
        SseEmitter emitter = emitterRepo.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));
        emitter.onCompletion(() -> emitterRepo.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepo.deleteById(emitterId));

        // 503 에러를 방지하기 위한 더미 이벤트 전송
        String eventId = makeTimeIncludeId(userId);
        sendNotification(emitter, eventId, emitterId, "EventStream Created. [userId=" + userId + "]");

        // 클라이언트가 미수신한 Event 목록이 존재할 경우 전송하여 Event 유실을 예방
        if (hasLostData(lastEventId)) {
            sendLostData(lastEventId, userId, emitterId, emitter);
        }

        return emitter;
    }

    /**
     * 유저 아이디에 현재 시간을 붙여 emitterId를 생성한다.
     */
    private String makeTimeIncludeId(Integer userId) {
        return userId + "_" + System.currentTimeMillis();
    }

    private void sendNotification(SseEmitter emitter, String eventId, String userId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .data(data));
        } catch (IOException e) {
            emitterRepo.deleteById(userId);
        }
    }

    private boolean hasLostData(String lastEventId) {
        return !lastEventId.isEmpty();
    }

    /**
     * 받지 못한 데이터가 있다면 Last-Event-ID를 기준으로 그 뒤의 데이터를 추출해 알림을 보내준다.
     */
    private void sendLostData(String lastEventId, Integer userId, String emitterId, SseEmitter emitter) {
        Map<String, Object> eventCaches = emitterRepo.findAllEventCacheStartWithByUserId(String.valueOf(userId));
        eventCaches.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                .forEach(entry -> sendNotification(emitter, entry.getKey(), emitterId, entry.getValue()));
    }
}
