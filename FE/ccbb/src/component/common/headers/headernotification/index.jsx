import * as S from "./style";
import { useEffect } from "react";
import NotificationItem from "../../../notificationItem";
import { useCallback } from "react";

export default function Headernotification({
  props: { state, setState, notifications },
}) {
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
      $isVisible={state}
      onClick={(e) => {
        e.stopPropagation();
      }}
    >
      <S.Top>알림</S.Top>
      {notifications.length > 0 ? (
        notifications.map((notification, index) => (
          <NotificationItem key={index} notification={notification} />
        ))
      ) : (
        <S.Empty>알림이 없습니다.</S.Empty>
      )}
    </S.Container>
  );
}
