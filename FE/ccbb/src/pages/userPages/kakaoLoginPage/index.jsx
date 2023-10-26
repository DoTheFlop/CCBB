import React from "react";
import { useParams, useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { UrlAtom } from "../../../recoil/UrlAtom";

export default function KakaoLoginPage() {
  const { token } = useParams();
  const navigate = useNavigate();
  const toUrl = useRecoilValue(UrlAtom);

  React.useEffect(() => {
    console.log("token: " + token);

    // 로그인버튼 누를 때 페이지 기억해뒀다가 로그인 성공 후 복원
    navigate(toUrl);
  }, [token, navigate]);

  return <></>;
}
