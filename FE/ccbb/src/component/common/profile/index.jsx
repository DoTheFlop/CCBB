import * as S from "./style";

export default function UserProfile({
  name,
  color = "white",
  direction = "",
  size = 50,
  imgUrl,
}) {
  // const iconSize = "50px";
  return (
    <S.Profile direction={direction}>
      <S.Img
        src={imgUrl || "/resource/squareyoon.png"}
        alt="리그 오브 레전드 이미지"
        style={{ width: size, height: size }}
      />
      <S.Name color={color} size={size}>
        {name}
      </S.Name>
    </S.Profile>
  );
}
