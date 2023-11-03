import React, { useState } from "react";
import * as S from "./style";
import Input1 from "../../../../component/common/inputs/input1";
import Button1 from "../../../../component/common/buttons";

export default function InfoModifyPage() {
  const [user, setUser] = useState({
    email: "",
    nickname: "",
    name: "",
    password: "",
    gender: "",
    birthdate: "",
  });
  const [oldPW, setOldPW] = useState("");

  return (
    <S.main>
      <Input1
        label="* 현재 패스워드"
        id="passwordInput"
        width="300px"
        height="40px"
        type="password"
        value={oldPW}
        onChange={(e) => {
          setOldPW(e.target.value);
        }}
      />
      <Input1
        label="새 패스워드"
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
        label="새 패스워드 확인"
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
        text={"수정"}
        width={"300px"}
        height={"50px"}
        // onClick={doLogin}
      />
    </S.main>
  );
}
