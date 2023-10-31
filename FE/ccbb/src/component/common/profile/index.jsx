import * as S from './style';

export default function UserProfile({ name, color, iconSize, fontsize }) {
    const size = iconSize || '50px';

    return (
        <S.Profile >
            <S.Img src="/resource/squareyoon.png" alt="리그 오브 레전드 이미지" style={{ width: size, height: size }} />
            <S.Name fontsize={fontsize} color={color}>{name}</S.Name>
        </S.Profile>
    );
}
