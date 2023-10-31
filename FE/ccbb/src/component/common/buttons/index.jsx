import * as S from './style';

export default function Button1({ text, width, height, onClick, color, fontsize }) {
    return (
        <S.Button1 fontsize={fontsize} color={color} onClick={onClick} width={width} height={height}>
            {text}
        </S.Button1>
    )
}
