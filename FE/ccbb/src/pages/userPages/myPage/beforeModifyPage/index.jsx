import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilValue } from "recoil";
import { userState } from "../../../../recoil/UserAtom";
import * as S from "./style";
import Input1 from "../../../../component/common/inputs/input1";
import Button1 from "../../../../component/common/buttons";
import { ccbbApi } from "../../../../api/ccbbApi";

export default function BeforeModifyPage() {
  React.useEffect(() => {
    if (userInfo.social === "Kakao") {
      console.log("카카오 회원은 비밀번호 체크하지 않음");
      navigate("/mypage/modify");
    }
  });
  const userInfo = useRecoilValue(userState);
  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  const navigate = useNavigate();
  const handlePwCheck = () => {
    ccbbApi
      .get("/user/checkPw", {
        headers: headers,
        params: { userPw: pwCheck },
      })
      .then((res) => {
        if (res.data.message == "success") {
          if (res.data.check) {
            navigate("/mypage/modify");
          } else {
            alert("비밀번호가 일치하지 않습니다.");
          }
        }
      })
      .catch((e) => {
        console.log(e);
        alert("서버에러");
      });
  };
  const [pwCheck, SetPwCheck] = useState("");
  return (
    <S.main>
      <Input1
        label="패스워드"
        id="PasswordInput"
        width="300px"
        height="40px"
        type="password"
        value={pwCheck}
        onChange={(e) => {
          SetPwCheck(e.target.value);
        }}
      />
      <Button1
        text={"확인"}
        width={"300px"}
        height={"50px"}
        onClick={handlePwCheck}
      />
    </S.main>
  );
}
