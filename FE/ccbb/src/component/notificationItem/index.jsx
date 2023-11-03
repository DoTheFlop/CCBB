import React from "react";
import * as S from "./style";

function NotificationItem({ notification }) {
  return (
    <S.Container>
      <div style={{width: '40px', 'paddingTop': '5px'}}>
        <S.Img src="/resource/squareyoon.png" alt="이미지" />
      </div>
      <S.RightSection>
        <S.TopSection>
          <S.Username>{notification.receiverUsername}</S.Username>
          {notification.createDate}
        </S.TopSection>
        <S.Content>{notification.content}</S.Content>
      </S.RightSection>
    </S.Container>
  );
}

export default NotificationItem;
