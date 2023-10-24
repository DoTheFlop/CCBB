import * as S from './style';

export default function Input1({ width, height, label, id }) {
    return (
        <S.InputBox>
            <S.Inputlabel htmlFor={id}>{label}</S.Inputlabel>
            <S.StyledInput type="text" id={id} width={width} height={height} />
        </S.InputBox>
    );
}

