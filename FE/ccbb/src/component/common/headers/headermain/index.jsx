import Button1 from "../../buttons";
import * as S from "./style";
import GavelIcon from "@mui/icons-material/Gavel";
import UserProfile from "../../profile";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilValue, useSetRecoilState, useResetRecoilState } from "recoil";
import { UrlAtom } from "../../../../recoil/UrlAtom";
import { userState } from "../../../../recoil/UserAtom";
import { ccbbApi } from "../../../../api/ccbbApi";

export default function Headermain() {
  const navigate = useNavigate();
  const setToUrl = useSetRecoilState(UrlAtom);
  const user = useRecoilValue(userState);
  const resetUserState = useResetRecoilState(userState);
  const handleSigninClick = () => {
    //로그인페이지로 가기 전 url을 기억하자
    const toUrl = window.location.pathname;
    setToUrl(toUrl);
    navigate("/signin");
  };
  const handleLogoutClick = () => {
    localStorage.removeItem("token");
    resetUserState();
    navigate("/");
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
        {localStorage.getItem("token") ? (
          <S.usermenu>
            <Link to="/mypage">
              <UserProfile
                name={user.nickname}
                size={42}
                imgUrl={
                  user.profileImg
                    ? `${process.env.REACT_APP_BASE_SERVER}/profileimg/${user.profileImg.name}`
                    : ""
                }
              />
            </Link>
            <Button1
              text={"로그아웃"}
              onClick={handleLogoutClick}
              width={"90px"}
              height={"30px"}
            />
          </S.usermenu>
        ) : (
          <Button1
            onClick={handleSigninClick}
            text={"로그인"}
            width={"75px"}
            height={"30px"}
          />
        )}
      </S.rightmenu>
    </S.Header>
  );
}
