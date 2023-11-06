import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./style";
import Input1 from "../../../../component/common/inputs/input1";
import Button1 from "../../../../component/common/buttons";
import { ccbbApi } from "../../../../api/ccbbApi";

export default function BeforeModifyPage() {
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
        console.log(res);
        navigate("/mypage/modify");
      })
      .catch((e) => {
        console.log(e);
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
