import * as S from './style';

export default function Button1({text,width,height,onClick}){
    return (
        <S.Button1 onClick={onClick} width={width} height={height}>{text}</S.Button1>
    )
}