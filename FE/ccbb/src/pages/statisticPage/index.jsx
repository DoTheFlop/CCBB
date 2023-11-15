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
            console.log(makeData(res.data.statistics));
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
  function tierToKorAndColor(tier) {
    switch (tier) {
      case "IRON":
        return { kor: "아이언", color: "#af9b97" };
      case "BRONZE":
        return { kor: "브론즈", color: "#cea07e" };
      case "SILVER":
        return { kor: "실버", color: "#a7b3be" };
      case "GOLD":
        return { kor: "골드", color: "#ebcb94" };
      case "PLATINUM":
        return { kor: "플래티넘", color: "#dbf5fc" };
      case "EMERALD":
        return { kor: "에메랄드", color: "#99ecca" };
      case "DIAMOND":
        return { kor: "다이아몬드", color: "#adebfa" };
      case "MASTER":
        return { kor: "마스터", color: "#e7bef6" };
      case "GRANDMASTER":
        return { kor: "그랜드마스터", color: "#f5be99" };
      case "CHALLENGER":
        return { kor: "챌린저", color: "#b3bdff" };
      default:
        return { kor: "", color: "#ffffff" };
    }
  }
  const makeData = (arr) => {
    return arr
      .map((item) => {
        let label =
          tierToKorAndColor(item.lolTier).kor ||
          numToPosition(item.lolPosition);
        let value = item.userCount || item.voteVictory / item.voteCount;
        if (label && value) {
          return {
            label: label,
            value: value,
            origin: item.lolTier || item.lolPosition,
          };
        }
      })
      .filter((item) => item !== undefined);
  };
  const makeColor = (data) => {
    return data.map((item) => {
      return tierToKorAndColor(item.origin).color;
    });
  };

  return (
    <S.main>
      <Headermenu title="통계" />
      <S.StatisticSection>
        {statisticsErr && (
          <h2 className="err-msg">
            ❗통계 데이터 업데이트에 문제가 생겼습니다.
          </h2>
        )}
        <S.DualPie>
          {/* <S.PieWithDescription>
            <h2>유저 티어 분포(소트안함)</h2>
            <Pie
              data={tierDist}
              generalSettings={{
                backgroundColor: "white",
              }}
              pieSettings={{
                color: makeColor(tierDist),
                innerRadius: 0.4,
                cornerRadius: 0.04,
                padAngle: 1,
                startAngle: 270,
                // sortByValue: true,
              }}
            />
          </S.PieWithDescription> */}
          <S.PieWithDescription>
            <h2>유저 티어 분포(소트했더니색밀림)</h2>
            <Pie
              data={tierDist}
              generalSettings={{
                backgroundColor: "white",
              }}
              pieSettings={{
                color: makeColor(tierDist),
                innerRadius: 0.4,
                cornerRadius: 0.04,
                padAngle: 1,
                startAngle: 270,
                sortByValue: true,
              }}
              labelSettings={{
                labelText: "label",
                labelOpacity: 0.5,
              }}
            />
          </S.PieWithDescription>
          <S.PieWithDescription>
            <h2>유저 포지션 분포</h2>
            <Pie
              data={positionDist}
              generalSettings={{
                backgroundColor: "white",
              }}
              pieSettings={{
                innerRadius: 0.5,
                cornerRadius: 0.04,
                padAngle: 2,
                startAngle: 270,
                sortByValue: true,
              }}
              labelSettings={{
                labelText: "label",
                labelOpacity: 0.5,
              }}
            />
          </S.PieWithDescription>
        </S.DualPie>
        <S.DualPie>
          <S.PieWithDescription>
            <h2>티어별 투표 승률</h2>
            <Pie
              data={winRateByTier}
              generalSettings={{
                backgroundColor: "white",
              }}
              pieSettings={{
                color: makeColor(winRateByTier),
                innerRadius: 0.4,
                cornerRadius: 0.04,
                padAngle: 1,
                startAngle: 270,
                sortByValue: true,
              }}
              labelSettings={{
                labelText: "label",
                labelOpacity: 0.5,
              }}
            />
          </S.PieWithDescription>
          <S.PieWithDescription>
            <h2>포지션별 투표 승률</h2>
            <Pie
              data={winRateByPosition}
              generalSettings={{
                backgroundColor: "white",
              }}
              pieSettings={{
                innerRadius: 0.5,
                cornerRadius: 0.04,
                padAngle: 2,
                startAngle: 270,
                sortByValue: true,
              }}
              labelSettings={{
                labelText: "label",
                labelOpacity: 0.5,
              }}
            />
          </S.PieWithDescription>
        </S.DualPie>
      </S.StatisticSection>
    </S.main>
  );
}
