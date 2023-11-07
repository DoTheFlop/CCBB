import * as S from "./style";
import Headermenu from "../../component/common/headers/headermenu";
import PrizeCard from "../../component/giftPage/prizeCard";

export default function GiftPage() {
  let dummy = [
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
    {
      title: "상품",
      stock: 1,
      point: 1000,
    },
  ];

  return (
    <S.main>
      <Headermenu title="경품응모"></Headermenu>
      <S.prizeSection>
        {dummy.map((pr, index) => {
          return (
            <PrizeCard
              key={index}
              title={pr.title + index}
              stock={index * 2}
              point={index * 1000}
            />
          );
        })}
      </S.prizeSection>
    </S.main>
  );
}
