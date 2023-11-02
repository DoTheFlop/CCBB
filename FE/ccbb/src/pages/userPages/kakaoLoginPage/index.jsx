import React from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useSetRecoilState, useRecoilValue } from "recoil";
import { UrlAtom } from "../../../recoil/UrlAtom";
import { userState } from "../../../recoil/UserAtom";
import { ccbbApi } from "../../../api/ccbbApi";

export default function KakaoLoginPage() {
  const { token } = useParams();
  const navigate = useNavigate();
  const toUrl = useRecoilValue(UrlAtom);
  const setUserInfo = useSetRecoilState(userState);

  React.useEffect(() => {
    console.log("token: " + token);
    localStorage.setItem("token", token);
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    // console.log(ccbbApi.defaults.baseURL);
    ccbbApi
      .get("/user/profile", { headers })
      .then((res) => {
        console.log(res);
        setUserInfo(res.data.user);
      })
      .catch((e) => console.log(e));
    // 로그인버튼 누를 때 페이지 기억해뒀다가 로그인 성공 후 복원
    navigate(toUrl);
  }, [token, toUrl, setUserInfo, navigate]);

  return <></>;
}
