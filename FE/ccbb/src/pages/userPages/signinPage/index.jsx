import * as S from "./style";
import Input1 from "../../../component/common/inputs/input1";
import Button1 from "../../../component/common/buttons";
import { Link } from "react-router-dom";

export default function SigninPage() {
  const doLogin = () => {
    console.log("로그인합시다");
  };
  return (
    <S.bg>
      <S.Img
        src="/resource/Logo.png"
        alt="CC.BB"
        style={{ width: "200px", height: "60px" }}
      />
      <Input1 label="이메일" id="idInput" width="300px" height="40px" />
      <Input1
        label="패스워드"
        id="passwordInput"
        width="300px"
        height="40px"
        type="password"
      />
      <Button1
        text={"로그인"}
        width={"100%"}
        height={"50px"}
        onClick={doLogin}
      />
      <S.signinMenu>
        <Link to="/signup">
          <p>회원가입</p>
        </Link>
        <Link to="/">
          <p>패스워드 찾기</p>
        </Link>
      </S.signinMenu>
    </S.bg>
  );
}
