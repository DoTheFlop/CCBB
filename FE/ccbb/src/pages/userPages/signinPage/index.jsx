import * as S from "./style";
import Input1 from "../../../component/common/inputs/input1";
import Button1 from "../../../component/common/buttons";
import { Link, useNavigate } from "react-router-dom";

export default function SigninPage() {
  return (
    <S.bg>
      <h1>시시비비</h1>
      <Input1 label="이메일" id="idInput" width="300px" height="40px" />
      <Input1
        label="패스워드"
        id="passwordInput"
        width="300px"
        height="40px"
        type="password"
      />
      <Button1 text={"로그인"} width={"75px"} height={"30px"} />
      <S.signinMenu>
        <Link>
          <p>회원가입</p>
        </Link>
        <Link>
          <p>패스워드 찾기</p>
        </Link>
      </S.signinMenu>
    </S.bg>
  );
}
