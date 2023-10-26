import Button1 from "../../buttons";
import * as S from "./style";
import GavelIcon from "@mui/icons-material/Gavel";
import { Link, useNavigate } from "react-router-dom";
import { useSetRecoilState } from "recoil";
import { UrlAtom } from "../../../../recoil/UrlAtom";

export default function Headermain() {
  const goSignIn = useNavigate();
  const setToUrl = useSetRecoilState(UrlAtom);
  const handleSigninClick = () => {
    //로그인페이지로 가기 전 url을 기억하자
    const toUrl = window.location.pathname;
    setToUrl(toUrl);
    goSignIn("/signin");
  };

  return (
    <S.Header>
      <S.leftmenu>
        <Link to="/">
          <h1>CC.BB</h1>
        </Link>
        <GavelIcon style={{ color: "#AC0000", paddingLeft: "10px" }} />
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
        <Button1
          onClick={handleSigninClick}
          text={"로그인"}
          width={"75px"}
          height={"30px"}
        />
      </S.rightmenu>
    </S.Header>
  );
}
