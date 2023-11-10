import * as S from "./style";
import React from "react";
import ProcessBtn from "../../myPage/processBtn";
import HandshakeOutlinedIcon from "@mui/icons-material/HandshakeOutlined";
import HowToVoteOutlinedIcon from "@mui/icons-material/HowToVoteOutlined";
import DirectionsWalkOutlinedIcon from "@mui/icons-material/DirectionsWalkOutlined";
import CurrencyExchangeOutlinedIcon from "@mui/icons-material/CurrencyExchangeOutlined";
import DoubleArrowOutlinedIcon from "@mui/icons-material/DoubleArrowOutlined";

export default function VoteProcess({ step = 1 }) {
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
    <S.processChart>
      {processList.map((proc, index) => {
        return (
          <React.Fragment key={index}>
            <S.processStep>
              <ProcessBtn Icon={proc.icon} isOngoing={index === step} />
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
  );
}
