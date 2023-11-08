import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useRecoilValue, useSetRecoilState } from "recoil";
import { userState } from "../../../../recoil/UserAtom";
import * as S from "./style";
import Input1 from "../../../../component/common/inputs/input1";
import Button1 from "../../../../component/common/buttons";
import { ccbbApi } from "../../../../api/ccbbApi";

export default function InfoModifyPage() {
  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  const navigate = useNavigate();
  const userInfo = useRecoilValue(userState);
  // const setUserInfo = useSetRecoilState(userState);
  const handleInfoUpdate = (infoType) => {
    if (infoType === "password" && !pwSame) return;
    ccbbApi
      .put("/user/modify", JSON.stringify(userModi), {
        headers,
        withCredentials: true,
      })
      .then((res) => {
        console.log(res);
        navigate("/mypage");
      })
      .catch((e) => {
        console.log(e);
      });
  };
  const [userModi, setUserModi] = useState({
    email: "",
    nickname: "",
    password: "",
    gender: "",
  });
  const [pwCheck, SetPwCheck] = useState("");
  const pwSame = userModi.password === pwCheck;
  return (
    <S.main>
      {userInfo.social !== "Kakao" && (
        <S.ModifyBox>
          <h4>패스워드 수정</h4>
          <Input1
            label="새 패스워드"
            id="newPasswordInput"
            width="300px"
            height="40px"
            type="password"
            value={userModi.password}
            onChange={(e) => {
              setUserModi((prev) => ({
                ...prev,
                password: e.target.value,
              }));
            }}
          />
          <Input1
            label="새 패스워드 확인"
            id="newPasswordInputCheck"
            width="300px"
            height="40px"
            type="password"
            value={pwCheck}
            onChange={(e) => {
              SetPwCheck(e.target.value);
            }}
          />
          {pwCheck && !pwSame && (
            <div className="alert-message">패스워드가 일치하지 않습니다.</div>
          )}
          <Button1
            text={"수정"}
            width={"300px"}
            height={"50px"}
            onClick={() => {
              setUserModi((prev) => ({
                ...prev,
                nickname: "",
              }));
              handleInfoUpdate("password");
            }}
          />
        </S.ModifyBox>
      )}
      <S.ModifyBox>
        <h4>닉네임 수정</h4>
        <Input1
          label="닉네임"
          id="nicknameInput"
          width="300px"
          height="40px"
          value={userModi.nickname}
          onChange={(e) => {
            setUserModi((prev) => ({
              ...prev,
              nickname: e.target.value,
            }));
          }}
        />
        <Button1
          text={"수정"}
          width={"300px"}
          height={"50px"}
          onClick={() => {
            setUserModi((prev) => ({
              ...prev,
              password: "",
            }));
            handleInfoUpdate("nickname");
          }}
        />
      </S.ModifyBox>
    </S.main>
  );
}
