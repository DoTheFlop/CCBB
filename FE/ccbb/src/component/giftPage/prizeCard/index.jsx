import * as S from "./style";
import Button1 from "../../common/buttons";

export default function PrizeCard({ stock = 1, title, point }) {
  return (
    <S.card>
      {stock > 0 && <S.stockBadge>{stock}개</S.stockBadge>}
      <S.Img
        src="https://images.unsplash.com/photo-1580643842201-d1de38d71987?auto=format&fit=crop&q=80&w=1780&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
        alt="profile-img"
      />
      <h3>{title}</h3>
      <p>{point}P</p>
      <Button1
        width={"90px"}
        height={"30px"}
        text={stock ? "응모하기" : "Sold Out"}
        color={stock ? null : "#c30f0f"}
      />
    </S.card>
  );
}
