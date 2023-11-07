import UserProfile from "../common/profile";
import * as S from "./style";

export default function CommentBox({
  isMine = false,
  bgcolor,
  comment,
  nickname,
  date,
  position,
  onClickModify,
  onClickDelete,
}) {
  return (
    <S.Box bgcolor={bgcolor}>
      <S.CommentHead>
        <UserProfile
          name={nickname}
          color={"black"}
          iconSize={"30px"}
          fontsize={"15px"}
        />
        <p>{date}</p>
      </S.CommentHead>
      <S.CommentBody>
        <p>{comment}</p>
      </S.CommentBody>
      {isMine && (
        <S.CommentBottom>
          <span className="modify-comment" onClick={onClickModify}>
            수정
          </span>
          <span className="divider">|</span>
          <span className="delete-comment" onClick={onClickDelete}>
            삭제
          </span>
        </S.CommentBottom>
      )}
    </S.Box>
  );
}
