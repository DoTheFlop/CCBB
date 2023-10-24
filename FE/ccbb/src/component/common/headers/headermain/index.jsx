import Button1 from '../../buttons';
import * as S from './style';
import GavelIcon from '@mui/icons-material/Gavel';
import { Link, useNavigate } from 'react-router-dom'

export default function Headermain(){
    const goSignIn = useNavigate();

    const handleSigninClick = () => {
        goSignIn("/signin")
    }

    return (
        <S.Header>
            <S.leftmenu>
                <Link to="/">
                    <h1>CC.BB</h1>
                </Link>
                <GavelIcon style={{ color: '#AC0000' , paddingLeft: '10px'}} />
                    <S.gamemenu>
                        <Link to="/lolvote">
                            <p>LoL</p>
                        </Link>
                        <Link to="/1">
                            <p>컴포넌트</p>
                        </Link>
                    </S.gamemenu>
            </S.leftmenu>
            <S.rightmenu>
                <S.webmenu>
                    <Link to="/free">
                        <p>자유게시판</p>
                    </Link>
                    <Link to="/gift">
                        <p>경품추첨</p>
                    </Link>
                    <Link to="/donate">
                        <p>기부내역</p>
                    </Link>
                </S.webmenu>
                <Button1 onClick={handleSigninClick} text={"로그인ll"} width={'75px'} height={'30px'}/>
            </S.rightmenu>
        </S.Header>
        
    )
}