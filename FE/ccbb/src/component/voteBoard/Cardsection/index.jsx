import React, { useEffect, useState } from "react";
import * as S from "./style";
import { useNavigate } from "react-router";
import PeopleIcon from "@mui/icons-material/People";
import { ko } from "date-fns/locale";
import { formatDistanceToNow } from "date-fns";
import TierImg from "../../tier";


const VoteCard = ({ title, postId, voteCount, deadline, fileId, tier }) => {
  const navigate = useNavigate();
  const date = new Date(deadline);
  const timeAgo = formatDistanceToNow(date, { addSuffix: true, locale: ko });

  const handleCardClick = () => {
    navigate(`/lolvote/${postId}`);
  };

  return (
    <S.Card onClick={handleCardClick}>
      
      {fileId && (
        <video
          width={"100%"}
          src={`https://ccbb.pro/api/file/get/${fileId}`}
          alt="hi"
          style={{ borderTopLeftRadius: "10px", borderTopRightRadius: "10px" }}
        ></video>
      )}
      <h2
        style={{
          marginLeft: "20px",
          whiteSpace: "nowrap",
          overflow: "hidden",
          textOverflow: "ellipsis",
        }}
      >
        {title}
      </h2>
      <S.BottomWrapper>
        <S.CountWrapper>
          <PeopleIcon />
          <p style={{ marginLeft: "10px" }}>{voteCount}</p>
        </S.CountWrapper>
        <p style={{ marginRight: "20px" }}>종료일 : {timeAgo}</p>
      </S.BottomWrapper>
      <S.ImgBox>
        <TierImg tier={tier} size={"50px"} />
      </S.ImgBox>
    </S.Card>
  );
};

export default VoteCard;
