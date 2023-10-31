import React from "react";
import { Link } from "react-router-dom";
import * as S from "./style";
import ProcessBtn from "../processBtn";
import HandshakeOutlinedIcon from "@mui/icons-material/HandshakeOutlined";
import HowToVoteOutlinedIcon from "@mui/icons-material/HowToVoteOutlined";
import DirectionsWalkOutlinedIcon from "@mui/icons-material/DirectionsWalkOutlined";
import CurrencyExchangeOutlinedIcon from "@mui/icons-material/CurrencyExchangeOutlined";
import DoubleArrowOutlinedIcon from "@mui/icons-material/DoubleArrowOutlined";
import VoteRate from "../../voteBoard/voteRate";

export default function OngoingVote() {
  const processList = [
    {
      icon: HandshakeOutlinedIcon,
      title: "수락 대기중",
    },
    {
      icon: HowToVoteOutlinedIcon,
      title: "투표 진행중",
    },
    {
      icon: DirectionsWalkOutlinedIcon,
      title: "공약 이행중",
    },
    {
      icon: CurrencyExchangeOutlinedIcon,
      title: "보증금 반환",
    },
  ];

  return (
    <S.main>
      <Link to="/lolvote">
        <h1>이거누구탓임</h1>
      </Link>
      <S.processChart>
        {processList.map((proc, index) => {
          return (
            <React.Fragment key={index}>
              <S.processStep>
                <ProcessBtn
                  Icon={proc.icon}
                  isOngoing={proc.title === "수락 대기중"}
                />
                <p>{proc.title}</p>
              </S.processStep>
              {!(index === 3) && (
                <DoubleArrowOutlinedIcon
                  key={proc.title}
                  className="arrow"
                  style={{ width: "7vw", height: "7vw" }}
                />
              )}
            </React.Fragment>
          );
        })}
      </S.processChart>
      <VoteRate />
    </S.main>
  );
}
