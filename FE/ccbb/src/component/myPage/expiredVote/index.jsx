import React, { useState } from "react";
import { Link } from "react-router-dom";
import * as S from "./style";
// import VoteRate from "../../voteBoard/voteRate";
import VotePreview from "../../votePreview";
import Button1 from "../../common/buttons";
import { ccbbApi } from "../../../api/ccbbApi";
import Loading from "../../common/Loading";

export default function ExpiredVote() {
  const [myVoteList, SetMyVoteList] = useState([]);
  const token = localStorage.getItem("token");
  const [isLoading, setIsLoading] = useState(false);

  const headers = {
    Authorization: `Bearer ${token}`,
  };
  React.useEffect(() => {
    setIsLoading(true);
    ccbbApi
      .get("/post/vote/participationPastList", { headers })
      .then((res) => {
        console.log(res.data);
        SetMyVoteList(res.data.participationList);
        setIsLoading(false);
      })
      .catch((e) => {
        console.log(e);
        setIsLoading(false);
      });
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
        <span className="total-count">{myVoteList.length}건</span>
      </S.CountAndBtn>
      {isLoading ? <Loading></Loading> : null}
      {myVoteList.map((vote, index) => {
        return <VotePreview key={index} {...vote} />;
      })}
    </S.main>
  );
}
