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
      />
    </S.InputBox>
  );
}
