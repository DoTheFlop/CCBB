import * as S from "./style";
import { Link } from "react-router-dom";
import PeopleIcon from "@mui/icons-material/People";
import SmsIcon from "@mui/icons-material/Sms";
import { formatDistanceToNow } from "date-fns";
import { ko } from "date-fns/locale";

export default function VotePreview({
  title,
  postId,
  createTime,
  commentCount,
  userCount,
  createDate,
}) {
  let createDate1; // Declare it here so it's accessible outside the if block

  if (createTime) {
    createDate1 = formatDistanceToNow(new Date(createTime.split("T")[0]), { addSuffix: true, locale: ko });
  } else {
    createDate1 = formatDistanceToNow(new Date(createDate.split("T")[0]), { addSuffix: true, locale: ko });
  }
  return (
    <S.PreviewItem>
      <Link to={`/lolvote/${postId}`}>
        <S.TitleAndDate>
          <span className="vote-title">{title}</span>
          <span className="vote-createdate">{createDate1}</span>
        </S.TitleAndDate>
      </Link>
      <S.IconAndCnt>
        <PeopleIcon />
        <span>{userCount}</span>
      </S.IconAndCnt>
      <S.IconAndCnt>
        <SmsIcon />
        <span>{commentCount}</span>
      </S.IconAndCnt>
    </S.PreviewItem>
  );
}