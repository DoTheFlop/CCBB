import * as S from "./style";

export default function Button1({ text, width, height, onClick, color, size }) {
    return (
        <S.Button1 size={size} color={color} onClick={onClick} width={width} height={height}>
            {text}
        </S.Button1>
    )
}
