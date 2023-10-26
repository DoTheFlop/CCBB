package com.D104.ccbb.user.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class KakaoUserDto {
	private long id;
	private LocalDateTime connected_at;
	private Map<String, String> properties;
	private Map<String, Object> kakao_account;
}

/*
{
    "id": 3112042258,
    "connected_at": "2023-10-23T05:03:19Z",
    "properties": {
        "nickname": "최영창"
    },
    "kakao_account": {
        "profile_nickname_needs_agreement": false,
        "profile": {
            "nickname": "최영창"
        },
        "has_email": true,
        "email_needs_agreement": false,
        "is_email_valid": true,
        "is_email_verified": true,
        "email": "casden@naver.com"
    }
}
 */