import * as S from "./style";
import React, { useState } from "react";
import Headermenu from "../../component/common/headers/headermenu";
import { ccbbApi } from "../../api/ccbbApi";
import { Pie } from "jetty-chart";
import PuffLoader from "react-spinners/PuffLoader";

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
        return { kor: "플래티넘", color: "#aedee6" };
      case "EMERALD":
        return { kor: "에메랄드", color: "#99ecca" };
      case "DIAMOND":
        return { kor: "다이아몬드", color: "#9adff0" };
      case "MASTER":
        return { kor: "마스터", color: "#e7bef6" };
      case "GRANDMASTER":
        return { kor: "그랜드마스터", color: "#f5be99" };
      case "CHALLENGER":
        return { kor: "챌린저", color: "#b3bdff" };
      default:
        return { kor: "", color: "#cccccc" };
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
          };
        }
      })
      .filter((item) => item !== undefined);
  };
  const makeColor = (arr) => {
    return arr.map((item) => {
      return tierToKorAndColor(item.lolTier).color;
    });
  };
  const pieGeneralSettings = {
    backgroundColor: "transparent",
    paddingTop: 0,
    paddingRight: 0,
    paddingBottom: 0,
    paddingLeft: 0,
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
          <S.PieWithDescription>
            <h2>유저 티어 분포</h2>
            {tierDist.length > 0 ? (
              <Pie
                data={makeData(tierDist)}
                generalSettings={pieGeneralSettings}
                pieSettings={{
                  color: makeColor(tierDist),
                  innerRadius: 0.4,
                  cornerRadius: 0.04,
                  padAngle: 1,
                  startAngle: 270,
                  sortByValue: true,
                }}
                labelSettings={{
                  labelText: "ratio",
                  labelOpacity: 0.5,
                }}
                arcLinkLabelSettings={{
                  arcLinkLabelTextColor: makeColor(tierDist),
                  arcLinkLabelLineColor: makeColor(tierDist),
                }}
                legendSettings={{ useLegend: false }}
              />
            ) : (
              <S.SpinnerBox>
                <PuffLoader color="#5c78d5" />
              </S.SpinnerBox>
            )}
          </S.PieWithDescription>
          <S.PieWithDescription>
            <h2>유저 포지션 분포</h2>
            {positionDist.length > 0 ? (
              <Pie
                data={makeData(positionDist)}
                generalSettings={pieGeneralSettings}
                pieSettings={{
                  innerRadius: 0.5,
                  cornerRadius: 0.04,
                  padAngle: 2,
                  startAngle: 270,
                  sortByValue: true,
                }}
                labelSettings={{
                  labelText: "ratio",
                  labelOpacity: 0.5,
                }}
                legendSettings={{ useLegend: false }}
              />
            ) : (
              <S.SpinnerBox>
                <PuffLoader color="#5c78d5" />
              </S.SpinnerBox>
            )}
          </S.PieWithDescription>
        </S.DualPie>
        <S.DualPie>
          <S.PieWithDescription>
            <h2>티어별 투표 승률</h2>
            {winRateByTier.length > 0 ? (
              <Pie
                data={makeData(winRateByTier)}
                generalSettings={pieGeneralSettings}
                pieSettings={{
                  color: makeColor(winRateByTier),
                  innerRadius: 0.4,
                  cornerRadius: 0.04,
                  padAngle: 1,
                  startAngle: 270,
                  // sortByValue: true,
                }}
                labelSettings={{
                  labelText: "ratio",
                  labelOpacity: 0.5,
                }}
                arcLinkLabelSettings={{
                  arcLinkLabelText: "label",
                  arcLinkLabelTextColor: makeColor(winRateByTier),
                  arcLinkLabelLineColor: makeColor(winRateByTier),
                }}
                legendSettings={{ useLegend: false }}
              />
            ) : (
              <S.SpinnerBox>
                <PuffLoader color="#5c78d5" />
              </S.SpinnerBox>
            )}
          </S.PieWithDescription>
          <S.PieWithDescription>
            <h2>포지션별 투표 승률</h2>
            {winRateByPosition.length > 0 ? (
              <Pie
                data={makeData(winRateByPosition)}
                generalSettings={pieGeneralSettings}
                pieSettings={{
                  innerRadius: 0.5,
                  cornerRadius: 0.04,
                  padAngle: 2,
                  startAngle: 270,
                  sortByValue: true,
                }}
                labelSettings={{
                  labelText: "ratio",
                  labelOpacity: 0.5,
                }}
                legendSettings={{ useLegend: false }}
              />
            ) : (
              <S.SpinnerBox>
                <PuffLoader color="#5c78d5" />
              </S.SpinnerBox>
            )}
          </S.PieWithDescription>
        </S.DualPie>
      </S.StatisticSection>
    </S.main>
  );
}
