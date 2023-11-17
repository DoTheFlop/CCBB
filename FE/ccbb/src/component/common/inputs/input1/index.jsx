import * as S from "./style";

export default function Input1({
  width,
  height,
  label,
  id,
  type = "text",
  value = "",
  onKeyPress,
  onChange,
  maxLength,
}) {
  return (
    <S.InputBox>
      <S.Inputlabel htmlFor={id}>{label}</S.Inputlabel>
      <S.StyledInput
        type={type}
        id={id}
        width={width}
        height={height}
        onKeyPress={onKeyPress}
        onChange={onChange}
        maxLength={maxLength}
      />
    </S.InputBox>
  );
}
