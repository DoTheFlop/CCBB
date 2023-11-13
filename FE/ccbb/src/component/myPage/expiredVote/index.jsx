import React, { useState } from "react";
import { Link } from "react-router-dom";
import * as S from "./style";
// import VoteRate from "../../voteBoard/voteRate";
import VotePreview from "../../votePreview";
import Button1 from "../../common/buttons";
import { ccbbApi } from "../../../api/ccbbApi";

export default function ExpiredVote() {
  const [myVoteList, SetMyVoteList] = useState([]);
  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  React.useEffect(() => {
    ccbbApi
      .get("/post/vote/participationPastList", { headers })
      .then((res) => {
        SetMyVoteList(res.data.participationList);
      })
      .catch((e) => console.log(e));
  }, []);

  const dummyVotes = [
    {
      title: "지난 투표",
      postId: 1,
      createDate: "2023-11-09",
      commentCount: 4,
      userCount: 100,
    },
    {
      title: "목록입니다",
      postId: 1,
      createDate: "2023-11-09",
      commentCount: 10,
      userCount: 1,
    },
  ];

  return (
    <S.main>
      <S.CountAndBtn>
        <span className="total-count">{dummyVotes.length}건</span>
      </S.CountAndBtn>
      {dummyVotes.map((vote, index) => {
        return <VotePreview key={index} {...vote} />;
      })}
    </S.main>
  );
}
