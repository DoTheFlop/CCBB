import * as S from "./style";

export default function ProcessBtn({ Icon, isOngoing, title = "" }) {
  return (
    <S.roundBtn $isOngoing={isOngoing}>
      <Icon />
    </S.roundBtn>
  );
}
