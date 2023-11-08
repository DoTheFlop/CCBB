package com.D104.ccbb.notification.dto;

import com.D104.ccbb.notification.domain.NotificationType;
import com.D104.ccbb.post.domain.Post;
import com.D104.ccbb.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationRequestDto {

    private User receiver;
    private User sender;
    private String content;
    private String url;
    private NotificationType notificationType;

    public static NotificationRequestDto commentOf(Post post, User writer) {

        Integer postId = post.getPostId();
        User receiver = post.getUserId();
        String content = post.getTitle();
        if (content.length() > 12) {
            content = content.substring(0, 12) + "...";
        }
        content = "님이 회원님이 작성하신 '" + content + "'에 답변을 달았습니다.";
        String url = "/lolvote/" + postId;

        return new NotificationRequestDto(receiver, writer, content, url, NotificationType.COMMENT);
    }

    public static NotificationRequestDto voteRequestOf(Post post, User receiver, User writer) {

        Integer postId = post.getPostId();
        String content = post.getTitle();
        if (content.length() > 12) {
            content = content.substring(0, 12) + "...";
        }
        content = "님이 회원님께 '" + content + "'의 투표 진행을 신청하였습니다.";
        String url = "/lolvote/" + postId;

        return new NotificationRequestDto(receiver, writer, content, url, NotificationType.VOTE_REQUEST);
    }

    public static NotificationRequestDto voteApproveOf(Post post, User receiver, User writer) {

        Integer postId = post.getPostId();
        String content = post.getTitle();
        if (content.length() > 12) {
            content = content.substring(0, 12) + "...";
        }
        content = "님이 회원님이 작성한 '" + content + "'에 투표 진행을 승인하였습니다.";
        String url = "/lolvote/" + postId;

        return new NotificationRequestDto(receiver, writer, content, url, NotificationType.VOTE_APPROVE);
    }


    public static NotificationRequestDto voteRejectOf(Post post, User receiver, User writer) {
        String content = post.getTitle();
        if (content.length() > 12) {
            content = content.substring(0, 12) + "...";
        }
        content = "님이 회원님이 작성한 '" + content + "'에 투표 진행을 거절하였고, 해당 게시글은 삭제되었습니다.";
        String url = "/";

        return new NotificationRequestDto(receiver, writer, content, url, NotificationType.VOTE_REJECT);
    }

    public static NotificationRequestDto voteAddOf(Post post, User receiver, User writer) {
        Integer postId = post.getPostId();
        String content = post.getTitle();
        if (content.length() > 12) {
            content = content.substring(0, 12) + "...";
        }
        content = "님이 회원님이 참여중인 '" + content + "'에 기표하였습니다.";
        String url = "/lolvote/" + postId;

        return new NotificationRequestDto(receiver, writer, content, url, NotificationType.VOTE_REJECT);
    }
}
