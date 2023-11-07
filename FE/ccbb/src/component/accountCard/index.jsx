import * as S from "./style";
import AccountLinkBtn from "../accountLinkBtn";

export default function AccountCard() {
  const games = ["LoL", "발로란트", "etc", "etc2"];
  return (
    <S.card>
      <h3>계정 연동</h3>
      <S.gameLinks>
        {games.map((game, index) => {
          return (
            <S.gameLinkItem key={index}>
              <span>{game}</span>
              <AccountLinkBtn />
            </S.gameLinkItem>
          );
        })}
      </S.gameLinks>
    </S.card>
  );
}
