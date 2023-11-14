import React, { useState } from "react";
import UserProfile from "../common/profile";
import * as S from "./style";
// import { parseDate } from "../../api/dateParse";
import { ko } from 'date-fns/locale'
import {formatDistanceToNow} from "date-fns";

export default function CommentBox({
  isMine = false,
  userId,
  bgcolor,
  comment,
  nickname,
  date,
  position,
  onClickModify,
  onClickDelete,
  tier,
}) {
  const receivedDate = new Date(date);
  const createDate = formatDistanceToNow(receivedDate, { addSuffix: true, locale: ko });
  

  //댓글 수정 상태를 위한 값
  const [isEditing, setIsEditing] = useState(false);
  const [newComment, setNewComment] = useState(comment);

  const handleModifyClick = () => {
    setIsEditing(true);
  };

  const handleSaveClick = () => {
    setIsEditing(false);
    onClickModify(newComment);
  };

  const handleCancelClick = () => {
    setIsEditing(false);
    setNewComment(comment); // 원본 comment로 복원
  };

  return (
    <S.Box bgcolor={bgcolor}>
      <S.CommentHead>
        <UserProfile
          name={nickname}
          imgUrl={`${process.env.REACT_APP_BASE_SERVER}profileimg/${userId}`}
          color={"black"}
          iconSize={"10px"}
          fontsize={"10px"}
          size={40}
          tier={tier}
        />
        <p>
          {createDate}
        </p>
      </S.CommentHead>
      <S.CommentBody>
        {isEditing ? (
          <S.StyledInput
            value={newComment}
            onChange={(e) => setNewComment(e.target.value)}
            onKeyPress={(e) => {
              if (e.key === "Enter" && !e.shiftKey && newComment) {
                e.preventDefault();
                e.target.value = "";
                handleSaveClick();
              }
            }}
          />
        ) : (
          <p>{comment}</p>
        )}
      </S.CommentBody>
      {isMine && (
        <S.CommentBottom>
          {isEditing ? (
            <div>
              <span className="save-comment" onClick={handleSaveClick}>
                저장
              </span>
              <span className="divider">|</span>
              <span className="cancel-comment" onClick={handleCancelClick}>
                취소
              </span>
            </div>
          ) : (
            <div>
              <span className="modify-comment" onClick={handleModifyClick}>
                수정
              </span>
              <span className="divider">|</span>
              <span className="delete-comment" onClick={onClickDelete}>
                삭제
              </span>
            </div>
          )}
        </S.CommentBottom>
      )}
    </S.Box>
  );
}
