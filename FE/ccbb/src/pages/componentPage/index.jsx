import CommentBox from '../../component/commentBox';
import Button1 from '../../component/common/buttons';
import Headermenu from '../../component/common/headers/headermenu';
import Input1 from '../../component/common/inputs/input1';
import Input2 from '../../component/common/inputs/input2';
import UserProfile from '../../component/common/profile';
import * as S from './style';

export default function ComponentPage() {
    
    return (
        <S.main>
            <Headermenu title={"리그 오브 레전드"}/>
            <Headermenu title={"자유게시판"}/>
            <Input1 label="닉네임" id="nicknameInput" width="300px" height="40px" />
            <Input2 label="닉네임" id="nickname" width="300px" height="40px" />
            <Button1 text={"로그인"} width={'75px'} height={'30px'}/>
            <UserProfile name={"챌우혁"}/>
            <CommentBox bgcolor="#97A7FF" comment="This is a comment" userId="user123" date="2023-10-31" />

        </S.main>  
    )
}