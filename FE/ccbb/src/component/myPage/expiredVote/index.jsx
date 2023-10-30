import { Link } from "react-router-dom";
import * as S from "./style";
import VoteRate from "../../voteBoard/voteRate";

export default function ExpiredVote() {
  return (
    <S.main>
      <Link to="/lolvote">
        <h1>누구탓이냐고</h1>
      </Link>
      <VoteRate />
    </S.main>
  );
}
