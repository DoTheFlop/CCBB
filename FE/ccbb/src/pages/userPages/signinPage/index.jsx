import * as S from "./style";
import Input1 from "../../../component/common/inputs/input1";
import Button1 from "../../../component/common/buttons";

export default function SigninPage() {
  return (
    <S.bg>
      <h1>시시비비</h1>
      <Input1 label="아이디" id="idInput" width="300px" height="40px" />
      <Input1
        label="비밀번호"
        id="passwordInput"
        width="300px"
        height="40px"
        type="password"
      />
      <Button1 text={"로그인"} width={"75px"} height={"30px"} />
    </S.bg>
  );
}
