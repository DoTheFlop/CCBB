import * as S from "./style";

export default function Input2({
  width,
  height,
  label,
  id,
  color,
  placeholder,
  disabled,
  onChange,
}) {
  return (
    <S.InputBox>
      <S.Inputlabel htmlFor={id} color={color}>
        {label}
      </S.Inputlabel>
      <S.StyledInput
        id={id}
        width={width}
        height={height}
        placeholder={placeholder}
        disabled={disabled} // disabled 속성에 따라 input 요소를 비활성화
        onChange={onChange}
      />
    </S.InputBox>
  );
}
