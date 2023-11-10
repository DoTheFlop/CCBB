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
        <S.StyledInput
          id={id}
          width={width}
          height={height}
          onKeyPress={onKeyPress}
          onChange={onChange}
        />
    </S.InputBox>
  );
}
