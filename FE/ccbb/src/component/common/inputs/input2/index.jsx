import * as S from './style';

export default function Input2({ width, height, label, id, type }) {
    return (
      <S.InputBox>
        <S.Inputlabel htmlFor={id}>{label}</S.Inputlabel>
        <S.StyledInput type={type || 'text'} id={id} width={width} height={height} />
      </S.InputBox>
    );
  }
