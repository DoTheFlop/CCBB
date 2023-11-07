import React from "react";
import * as S from "./style";
import { formatDistanceToNow } from "date-fns";
import { ko } from "date-fns/locale";
import { ccbbApi } from "../../api/ccbbApi";

function NotificationItem({ notification }) {
  const date = new Date(notification.createDate);
  const timeAgo = formatDistanceToNow(date, { addSuffix: true, locale: ko });

  const handleRead = async () => {
    window.location.href = `${notification.url}`;

    const token = localStorage.getItem("token");
    const headers = {
      Authorization: `Bearer ${token}`, // 여기에 사용자 토큰을 넣어주세요
    };

    ccbbApi
      .put(`/notification/read/${notification.notificationId}`, {}, { headers })
      .then(() => {
        console.log("Notification read successfully");
      })
      .catch((e) => console.log(e));
  };

  return (
    <S.Container $isRead={notification.isRead} onClick={handleRead}>
      <S.RightSection>
        <S.TopSection>
          <S.Username>{notification.receiverUsername}</S.Username>
          {timeAgo}
        </S.TopSection>
        <S.Content>{notification.content}</S.Content>
      </S.RightSection>
    </S.Container>
  );
}

export default NotificationItem;
