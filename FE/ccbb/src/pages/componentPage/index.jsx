import Button1 from "../../component/buttons";
import Headermain from "../../component/headers/headermain";
import Headermenu from "../../component/headers/headermenu";
import Input1 from "../../component/inputs/input1"
import Input2 from "../../component/inputs/input2";
import * as S from './style';

export default function ComponentPage() {
    return (
        <S.main>
            <Headermain/>
            <Headermenu title={"리그 오브 레전드"}/>
            <Headermenu title={"자유게시판"}/>
            <Input1 label="닉네임" id="nicknameInput" width="300px" height="40px" />
            <Input2 label="닉네임" id="nicknameInput" width="300px" height="40px" />
            <Button1 text={"로그인"} width={'75px'} height={'30px'}/>
        </S.main>  
    )
}