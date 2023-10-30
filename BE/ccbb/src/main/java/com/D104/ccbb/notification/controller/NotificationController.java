package com.D104.ccbb.notification.controller;

import com.D104.ccbb.jwt.service.JwtTokenService;
import com.D104.ccbb.notification.service.NotifiactionService;
import com.D104.ccbb.user.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final NotifiactionService notifiactionService;


    @ApiOperation(value = "알림 구독", notes = "알림을 구독한다.")
    @GetMapping(value = "/subscribe", produces = "text/event-stream")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public SseEmitter subscribe(@RequestHeader String Authorization,
                                @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        Integer userId = userRepository.findByEmail(jwtTokenService.getUserEmail((jwtTokenService.extractToken(Authorization))))
                .get()
                .getUserId();

        return notifiactionService.subscribe(userId, lastEventId);
    }
}
