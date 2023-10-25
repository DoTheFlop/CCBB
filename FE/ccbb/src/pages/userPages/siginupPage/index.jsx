import React, { useState } from "react";
import * as S from "./style";
import Input1 from "../../../component/common/inputs/input1";
import Button1 from "../../../component/common/buttons";

export default function SignupPage() {
  const [user, setUser] = useState({
    email: "",
    nickname: "",
    name: "",
    password: "",
    gender: "",
    birthdate: "",
  });

  return (
    <>
      <S.Img
        src="/resource/Logo.png"
        alt="CC.BB"
        style={{ width: "200px", height: "60px" }}
      />
      <Input1
        label="이메일"
        id="idInput"
        width="300px"
        height="40px"
        value={user.email}
        onChange={(e) => {
          setUser((prev) => ({
            ...prev,
            email: e.target.value,
          }));
        }}
      />
      <Input1
        label="패스워드"
        id="passwordInput"
        width="300px"
        height="40px"
        type="password"
        value={user.password}
        onChange={(e) => {
          setUser((prev) => ({
            ...prev,
            password: e.target.value,
          }));
        }}
      />
      <Input1
        label="이름"
        id="nameInput"
        width="300px"
        height="40px"
        value={user.name}
        onChange={(e) => {
          setUser((prev) => ({
            ...prev,
            name: e.target.value,
          }));
        }}
      />
      <Input1
        label="닉네임"
        id="nicknameInput"
        width="300px"
        height="40px"
        value={user.nickname}
        onChange={(e) => {
          setUser((prev) => ({
            ...prev,
            nickname: e.target.value,
          }));
        }}
      />
      <Button1
        text={"가입"}
        width={"300px"}
        height={"50px"}
        // onClick={doLogin}
      />
    </>
  );
}
