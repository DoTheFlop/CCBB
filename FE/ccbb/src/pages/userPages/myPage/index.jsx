import * as S from "./style";
import AccountCard from "../../../component/accountCard";

export default function MyPage() {
  return (
    <>
      <S.profileInfo>
        <S.imgSection>
          <S.Img
            src="https://images.unsplash.com/photo-1580643842201-d1de38d71987?auto=format&fit=crop&q=80&w=1780&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            alt="profile-img"
          />
        </S.imgSection>
        <S.textSection>
          <div>닉네임</div>
          <div>1,000P</div>
        </S.textSection>
        <AccountCard />
      </S.profileInfo>
    </>
  );
}
