import MyTooltip from "../tooltip";
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
  tooltip = false,
  tooltipDetail = "",
  maxLength,
}) {
  return (
    <S.InputBox>
      <S.Inputlabel htmlFor={id} color={color}>
        {label}
        {"   "}
        {tooltip ? (
          <MyTooltip
            tooltip={tooltip}
            tooltipDetail={tooltipDetail}
          ></MyTooltip>
        ) : null}
      </S.Inputlabel>
      <S.StyledInput
        id={id}
        width={width}
        height={height}
        placeholder={placeholder}
        disabled={disabled} // disabled 속성에 따라 input 요소를 비활성화
        onChange={onChange}
        maxLength={maxLength}
      />
    </S.InputBox>
  );
}
