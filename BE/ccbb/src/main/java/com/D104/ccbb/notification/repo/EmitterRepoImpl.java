package com.D104.ccbb.notification.repo;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@NoArgsConstructor
public class EmitterRepoImpl implements EmitterRepo {

    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>(); // 동시성 문제로 ConcurrentHashMap 사용
    private final Map<String, Object> eventCache = new ConcurrentHashMap<>();

    /**
     * Emiiter를 저장한다.
     */
    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        emitters.put(emitterId, sseEmitter);
        return sseEmitter;
    }

    /**
     * 이벤트를 저장한다.
     */
    @Override
    public void saveEventCache(String eventCacheId, Object event) {
        eventCache.put(eventCacheId, event);
    }

    /**
     * 해당 회원과 관련된 모든 Emitter를 찾는다.
     */
    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithByUserId(String userId) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(userId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * 해당 회원과 관련된 모든 이벤트를 찾는다.
     */
    @Override
    public Map<String, Object> findAllEventCacheStartWithByUserId(String userId) {
        return eventCache.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(userId))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Emitter를 지운다.
     */
    @Override
    public void deleteById(String id) {
        emitters.remove(id);
    }

    /**
     * 해당 회원과 관련된 모든 Emitter를 지운다.
     */
    @Override
    public void deleteAllEmitterStartWithId(String userId) {
        emitters.forEach(
                (key, emitter) -> {
                    if (key.startsWith(userId)) {
                        emitters.remove(key);
                    }
                }
        );
    }

    /**
     * 해당 회원과 관련된 모든 이벤트를 지운다.
     */
    @Override
    public void deleteAllEventCacheStartWithId(String userId) {
        eventCache.forEach(
                (key, emitter) -> {
                    if (key.startsWith(userId)) {
                        eventCache.remove(key);
                    }
                }
        );
    }
}
