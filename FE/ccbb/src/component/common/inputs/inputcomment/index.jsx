import * as S from './style';

export default function InputComment({ width, height, label, id }) {
    return (
      <S.InputBox>
        <S.Inputlabel htmlFor={id}>{label}</S.Inputlabel>
        <S.StyledInput id={id} width={width} height={height} />
      </S.InputBox>
    );
  }
