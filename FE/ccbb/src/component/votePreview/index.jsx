import * as S from "./style";
import { Link } from "react-router-dom";
import PeopleIcon from "@mui/icons-material/People";
import SmsIcon from "@mui/icons-material/Sms";

export default function VotePreview({
  title,
  postId,
  createDate,
  commentCount,
  userCount,
}) {
  return (
    <S.PreviewItem>
      <Link to={`/lolvote/${postId}`}>
        <S.TitleAndDate>
          <span className="vote-title">{title}</span>
          <span className="vote-createdate">{createDate}</span>
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
