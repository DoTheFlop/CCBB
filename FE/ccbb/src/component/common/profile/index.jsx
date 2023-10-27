import * as S from "./style";

export default function UserProfile({ name, color = "white", direction = "" }) {
  const iconSize = "50px";

  return (
    <S.Profile direction={direction}>
      <S.Img
        src="/resource/squareyoon.png"
        alt="리그 오브 레전드 이미지"
        style={{ width: iconSize, height: iconSize }}
      />
      <S.Name color={color}>{name}</S.Name>
    </S.Profile>
  );
}
