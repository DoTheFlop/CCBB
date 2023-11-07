import React, { useState } from "react";
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
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    console.log(ccbbApi.defaults.baseURL);
    ccbbApi
      .get("/user/profile", { headers })
      .then((res) => {
        console.log(res);
        if (res.data.user) {
          // 사용자 정보가 유효한지 확인하고, 유효할 때만 토큰 저장
          setUserInfo(res.data.user);
          localStorage.setItem("token", token);
          // 추가로 프사 있는지 확인
          ccbbApi
            .get(`/profileimg/${res.data.user.userId}`)
            .then((res) => {
              console.log("프사는?");
              console.log(res);
              setUserInfo((prev) => ({
                ...prev,
                profileImg: res.data,
              }));
            })
            .catch((e) => {
              console.log(e);
              console.log("프사없음");
            });
        }
      })
      .catch((e) => console.log(e));
    // 로그인버튼 누를 때 페이지 기억해뒀다가 로그인 성공 후 복원
    navigate(toUrl);
  }, [token, toUrl, setUserInfo, navigate]);

  return <></>;
}
