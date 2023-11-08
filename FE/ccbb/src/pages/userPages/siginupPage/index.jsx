import React, { useState } from "react";
import * as S from "./style";
import Input1 from "../../../component/common/inputs/input1";
import Button1 from "../../../component/common/buttons";
import { ccbbApi } from "../../../api/ccbbApi";

export default function SignupPage() {
  const [user, setUser] = useState({
    email: "",
    nickname: "",
    name: "",
    password: "",
    gender: true,
  });

  const createDate = new Date().toISOString();
  
  const handleButtonSignup = () => {
    const { email, nickname ,name, password } = user;

    // 필수 필드가 비어있는지 확인
    if (!email) {
      alert("이메일을 입력해 주세요");
      return;
    }
    if (!password) {
      alert("비밀번호를 입력해 주세요");
      return;
    }
    if (!name) {
      alert("이름을 입력해주세요");
      return;
    }
    if (!nickname) {
      alert("닉네임을 입력해 주세요");
      return;
    }

    const body = {
      "createDate" : createDate,
      "email" : user.email,
      "name" : user.name,
      "nickname" : user.nickname,
      "password" : user.password,
      "point" : 0,
      "sex" : user.gender,
      "state" : "1",
      "voteCount" : 0,
      "voteVictory":0
    }

    ccbbApi
      .post("/user/esign-up",JSON.stringify(body))
      .then((res) => {
        console.log(res)
      })
      .catch((e) => console.log(e))
  }


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
      <S.GenderBox>
        <label>
          남자
          <S.GenderRadio 
          type="radio" 
          name="gender" 
          value={true} 
          onChange={(e) => {
            setUser((prev) => ({
              ...prev,
              gender:e.target.value
              }))}}
          />
        </label>
        <label>
          여자
          <S.GenderRadio 
          type="radio" 
          name="gender" 
          value={false} 
          onChange={(e) => {
            setUser((prev) => ({
              ...prev,
              gender:e.target.value
              }))}}
          />
        </label>
      </S.GenderBox>
      <Button1
        text={"가입"}
        width={"300px"}
        height={"50px"}
        onClick={handleButtonSignup}
      />
    </>
  );
}
