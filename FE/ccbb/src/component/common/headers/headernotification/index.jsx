import * as S from "./style";
import { useEffect } from "react";
import NotificationItem from "../../../notificationItem";
import { useCallback } from "react";
import { useState } from "react";

export default function Headernotification({ props: { state, setState } }) {
  const [notifications, setNotifications] = useState([
    {
      content: '님이 회원님이 작성하신 "이거누구탓임"에 댓글을 달았습니다.',
      url: "http://testurl1.com",
      isRead: false,
      receiverUsername: "최강삼성",
      createDate: "2시간 전",
    },
    {
      content: '님이 회원님이 요청하신 "억울합니다" 투표를 수락하였습니다.',
      url: "http://testurl1.com",
      isRead: false,
      receiverUsername: "싸피인",
      createDate: "1일 전",
    },
  ]);

  const offModal = useCallback(() => {
    setState(false);
  }, [setState]);

  useEffect(() => {
    window.addEventListener("click", offModal);

    return () => {
      window.removeEventListener("click", offModal);
    };
  }, [offModal]);

  return (
    <S.Container
      onClick={(e) => {
        e.stopPropagation();
      }}
    >
      <S.Top>알림</S.Top>
      {notifications.map((notification, index) => (
        <NotificationItem key={index} notification={notification} />
      ))}
    </S.Container>
  );
}
