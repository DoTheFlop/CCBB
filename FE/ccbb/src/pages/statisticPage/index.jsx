import * as S from "./style";
import React, { useState } from "react";
import Headermenu from "../../component/common/headers/headermenu";
import { ccbbApi } from "../../api/ccbbApi";
import { Pie } from "jetty-chart";

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
            setTierDist(makeData(res.data.statistics));
          })
          .catch((e) => console.log(e));
        ccbbApi
          .get("/statistics/count/position")
          .then((res) => {
            setPositionDist(makeData(res.data.statistics));
          })
          .catch((e) => console.log(e));
        ccbbApi
          .get("/statistics/vote/tier")
          .then((res) => {
            setWinRateByTier(makeData(res.data.statistics));
          })
          .catch((e) => console.log(e));
        ccbbApi
          .get("/statistics/vote/position")
          .then((res) => {
            setWinRateByPosition(makeData(res.data.statistics));
          })
          .catch((e) => console.log(e));
      })
      .catch((e) => {
        setStatisticsErr(true);
        console.log(e);
      });
  }, []);

  function numToPosition(num) {
    switch (num) {
      case 0:
        return "탑";
      case 1:
        return "정글";
      case 2:
        return "미드";
      case 3:
        return "원딜";
      case 4:
        return "서폿";
      default:
        return "";
    }
  }
  const makeData = (arr) => {
    return arr
      .map((item) => {
        let label = item.lolTier || numToPosition(item.lolPosition);
        let value = item.userCount || item.voteVictory / item.voteCount;
        if (label && value) {
          return {
            label: label,
            value: value,
          };
        }
      })
      .filter((item) => item !== undefined);
  };

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
        <Pie
          data={tierDist}
          generalSettings={{
            backgroundColor: "white",
          }}
          pieSettings={{ innerRadius: 0.5, cornerRadius: 0.04, padAngle: 2 }}
        />
        <h2>유저 포지션 분포</h2>
        <Pie
          data={positionDist}
          generalSettings={{
            backgroundColor: "white",
          }}
          pieSettings={{ innerRadius: 0.5, cornerRadius: 0.04, padAngle: 2 }}
        />
        <h2>티어별 투표 승률</h2>
        <Pie
          data={winRateByTier}
          generalSettings={{
            backgroundColor: "white",
          }}
          pieSettings={{ innerRadius: 0.5, cornerRadius: 0.04, padAngle: 2 }}
        />
        <h2>포지션별 투표 승률</h2>
        <Pie
          data={winRateByPosition}
          generalSettings={{
            backgroundColor: "white",
          }}
          pieSettings={{ innerRadius: 0.5, cornerRadius: 0.04, padAngle: 2 }}
        />
      </S.statisticSection>
    </S.main>
  );
}
