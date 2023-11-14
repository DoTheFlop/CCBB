import * as S from "./style";

export default function PromisePage({ promise, deposit }) {
  const formattedDeposit = Number(deposit).toLocaleString();

  return (
    <S.Main>
      {promise} <br />
      <p>
        [보증금] <b>{formattedDeposit}</b>원
      </p>
    </S.Main>
  );
}
