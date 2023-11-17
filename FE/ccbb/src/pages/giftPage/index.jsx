import * as S from "./style";
import Headermenu from "../../component/common/headers/headermenu";
import PrizeCard from "../../component/giftPage/prizeCard";
import { ccbbApi } from "../../api/ccbbApi";
import { useEffect, useState } from "react";
import Loading from "../../component/common/Loading";

export default function GiftPage() {
  const [goods, setGoods] = useState([]);
  const [userPoint, setUserPoint] = useState(0); // 사용자의 포인트를 저장할 state를 추가합니다.
  const [isLoading, setIsLoading] = useState(false);

  const token1 = localStorage.getItem("token"); // 사용자의 토큰을 가져옵니다.

  const updateGoods = () => {
    setIsLoading(true);
    ccbbApi
      .get(`/event/goods/list?eventId=${1}`)
      .then((res) => {
        setGoods(res.data.goodsList);
        setIsLoading(false);
      })
      .catch((e) => {
        console.log(e);
        setIsLoading(false);
      });
  };

  const getUserPoint = () => {
    // 사용자의 포인트를 가져오는 함수를 추가합니다.
    const headers = {
      Authorization: `Bearer ${token1}`,
    };
    ccbbApi
      .get(`/user/profile`, { headers }) // 이 URL은 사용자의 포인트 정보를 가져오는 API의 URL로 수정해야 합니다.
      .then((res) => {
        setUserPoint(res.data.user.point); // 응답에서 포인트 정보를 가져와 state에 저장합니다.
      })
      .catch((e) => console.log(e));
  };

  useEffect(() => {
    updateGoods();
    getUserPoint(); // 컴포넌트가 마운트될 때 사용자의 포인트 정보를 가져옵니다.
  }, []);

  return (
    <S.main>
      <Headermenu title="경품응모"></Headermenu>
      {token1 ? (
        <S.userPointSection>
          {" "}
          {/* 사용자의 포인트를 나타내는 섹션을 추가합니다. */}
          <h3>
            내가 보유한 포인트 &nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp; {userPoint}
            point
          </h3>{" "}
          {/* 사용자의 포인트를 출력합니다. */}
        </S.userPointSection>
      ) : null}
      <S.prizeSection>
        {isLoading ? (
          <>
            <Loading></Loading>
            <S.EmptyDiv></S.EmptyDiv>
          </>
        ) : null}
        {goods.map((pr, index) => {
          return (
            <PrizeCard
              key={index}
              fileId={pr.fileId}
              title={pr.name}
              stock={pr.winCount}
              point={pr.price}
              goodsId={pr.goodsId}
              updateGoods={updateGoods}
              getUserPoint={getUserPoint}
              setUserPoint={setUserPoint} // setUserPoint 함수를 전달합니다.
              userPoint={userPoint} // userPoint 상태를 전달합니다.
            />
          );
        })}
      </S.prizeSection>
    </S.main>
  );
}
