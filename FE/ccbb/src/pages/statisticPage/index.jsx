import * as S from "./style";
import React, { useState, useEffect } from "react";
import Headermenu from "../../component/common/headers/headermenu";
import { ccbbApi } from "../../api/ccbbApi";
// import { NormalMap, NormalScatter } from "jetty-chart";

export default function StatisticPage() {
  const [statisticsErr, setStatisticsErr] = useState(false);
  const [tierDist, setTierDist] = useState([]);
  const [positionDist, setPositionDist] = useState([]);
  const [winRateByTier, setWinRateByTier] = useState([]);
  const [winRateByPosition, setWinRateByPosition] = useState([]);
  React.useEffect(() => {
    ccbbApi
      .get("/statistics/update")
      .then((res) => {
        console.log("통계 최신화 성공!");
        setStatisticsErr(false);
        ccbbApi
          .get("/statistics/count/tier")
          .then((res) => {
            setTierDist(res.data.statistics);
          })
          .catch((e) => console.log(e));
        ccbbApi
          .get("/statistics/count/position")
          .then((res) => {
            setPositionDist(res.data.statistics);
          })
          .catch((e) => console.log(e));
        ccbbApi
          .get("/statistics/vote/tier")
          .then((res) => {
            setWinRateByTier(res.data.statistics);
          })
          .catch((e) => console.log(e));
        ccbbApi
          .get("/statistics/vote/position")
          .then((res) => {
            setWinRateByPosition(res.data.statistics);
          })
          .catch((e) => console.log(e));
      })
      .catch((e) => {
        setStatisticsErr(true);
        console.log(e);
      });
  }, []);
  return (
    <S.main>
      <Headermenu title="통계" />
      <S.statisticSection>
        {statisticsErr && (
          <h2 className="err-msg">
            ❗통계 데이터 업데이트에 문제가 생겼습니다.
          </h2>
        )}
        <h2>유저 티어 분포</h2>
        <h2>유저 포지션 분포</h2>
        <h2>티어별 투표 승리율</h2>
        <h2>포지션별 투표 승리율</h2>
      </S.statisticSection>
    </S.main>
  );
}
