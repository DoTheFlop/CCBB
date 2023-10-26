import * as S from './style';
import GavelIcon from '@mui/icons-material/Gavel';

export default function Headermenu({ title }) {
    const iconSize = '50px';

    return (
        <S.Header>
            {title === "자유게시판" ? (
                <GavelIcon style={{ color: '#AC0000', fontSize: iconSize, paddingLeft: '10px' }} />
            ) : title === "리그 오브 레전드" ? (
                <img src="/resource/LoL.png" alt="리그 오브 레전드 이미지" style={{ width: iconSize, height: iconSize }} />
            ) : null}
            <h1>{title}</h1>
        </S.Header>
        //  서버 배포시 이미지가 보이지 않는다면 <img src={process.env.PUBLIC_URL + '/resource/LoL.png'} alt='logo image'/>
    )
}
