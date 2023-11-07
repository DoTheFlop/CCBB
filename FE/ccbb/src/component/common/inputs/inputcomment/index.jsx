import * as S from "./style";
import Button1 from "../../buttons";

export default function InputComment({
  width,
  height,
  label,
  id,
  value = "",
  onKeyPress,
  onChange,
  onClick,
}) {
  return (
    <S.InputBox>
      <S.Inputlabel htmlFor={id}>{label}</S.Inputlabel>
      <S.InputAndButton>
        <S.StyledInput
          id={id}
          width={width}
          height={height}
          onKeyPress={onKeyPress}
          onChange={onChange}
        />
        <Button1
          text={"등록"}
          width={"80px"}
          height={"40px"}
          onClick={onClick}
        />
      </S.InputAndButton>
    </S.InputBox>
  );
}
