package com.D104.ccbb.notification.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {

    COMMENT("NT_COMMENT"),
    VOTE_REQUEST("NT_VOTE_REQUEST"), VOTE_APPROVE("NT_VOTE_APPROVE"), VOTE_REJECT("NT_VOTE_REJECT");

    private final String key;
}
