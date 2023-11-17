import React, { useState } from "react";
import { Link } from "react-router-dom";
import * as S from "./style";
import ProcessBtn from "../processBtn";
import VotePreview from "../../votePreview";
import Button1 from "../../common/buttons";
import { ccbbApi } from "../../../api/ccbbApi";
import Loading from "../../common/Loading";
// import VoteRate from "../../voteBoard/voteRate";

export default function OngoingVote() {
  const [myVoteList, SetMyVoteList] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  React.useEffect(() => {
    setIsLoading(true);
    ccbbApi
      .get("/post/vote/participationList", { headers })
      .then((res) => {
        console.log(res);
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
      title: "시시비비",
      postId: 1,
      createDate: "2023-11-09",
      commentCount: 4,
      userCount: 100,
    },
    {
      title: "를",
      postId: 1,
      createDate: "2023-11-09",
      commentCount: 10,
      userCount: 1,
    },
    {
      title: "가려주세요",
      postId: 1,
      createDate: "2023-11-09",
      commentCount: 10,
      userCount: 100000,
    },
    {
      title: "ㅎㅎ",
      postId: 1,
      createDate: "2023-11-09",
      commentCount: 600,
      userCount: 100,
    },
  ];

  return (
    <S.main>
      <S.CountAndBtn>
        <span className="total-count">{myVoteList.length}건</span>
        <Link to="/lolvote/create">
          <Button1 text={"투표 만들기"} width={"120px"} height={"30px"} />
        </Link>
      </S.CountAndBtn>
      {isLoading ? <Loading></Loading> : null}
      {myVoteList.map((vote, index) => {
        return <VotePreview key={index} {...vote} />;
      })}
    </S.main>
  );
}
