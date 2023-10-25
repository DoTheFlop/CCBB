import * as S from "./style";

export default function Input1({ width, height, label, id, type = "text" }) {
  return (
    <S.InputBox>
      <S.Inputlabel htmlFor={id}>{label}</S.Inputlabel>
      <S.StyledInput type={type} id={id} width={width} height={height} />
    </S.InputBox>
  );
}
