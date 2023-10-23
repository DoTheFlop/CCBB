import Button1 from '../../buttons';
import * as S from './style';
import GavelIcon from '@mui/icons-material/Gavel';

export default function Headermain(){
    return (
        <S.Header>
            <S.leftmenu>
                <h1>CC.BB</h1>
                <GavelIcon style={{ color: '#AC0000' , paddingLeft: '10px'}} />
                    <S.gamemenu>
                        <p>LoL</p>
                        <p>발로란트</p>
                    </S.gamemenu>
            </S.leftmenu>
            <S.rightmenu>
                <S.webmenu>
                    <p>자유게시판</p>
                    <p>경품추첨</p>
                    <p>기부내역</p>
                </S.webmenu>
                <Button1 text={"로그인"} width={'75px'} height={'30px'}/>
            </S.rightmenu>
            
        </S.Header>
        
    )
}