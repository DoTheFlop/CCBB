import React, { useState } from "react";
import { Link } from "react-router-dom";
import * as S from "./style";
import ProcessBtn from "../processBtn";
import VotePreview from "../../votePreview";
import Button1 from "../../common/buttons";
import { ccbbApi } from "../../../api/ccbbApi";
// import VoteRate from "../../voteBoard/voteRate";

export default function MyWards() {
  const [myWardList, SetMyWardList] = useState([]);

  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };

  React.useEffect(() => {
    ccbbApi
      .get("/wod/list", { headers })
      .then((res) => {
        console.log(res.data.wodList)
        SetMyWardList(res.data.wodList);
      })
      .catch((e) => console.log(e));
  }, []);

  

  return (
    <S.main>
      <S.CountAndBtn>
        <span className="total-count">{myWardList.length}건</span>
      </S.CountAndBtn>
      {myWardList.map((vote, index) => {
        return <VotePreview key={index} {...vote} />;
      })}
    </S.main>
  );
}
