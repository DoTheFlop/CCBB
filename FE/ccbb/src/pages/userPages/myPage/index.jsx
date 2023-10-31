import React, { useState } from "react";
import * as S from "./style";
import Button1 from "../../../component/common/buttons";
import AccountCard from "../../../component/accountCard";
import OngoingVote from "../../../component/myPage/ongoingVote";
import ExpiredVote from "../../../component/myPage/expiredVote";
import MyPosts from "../../../component/myPage/myPosts";
import MyWards from "../../../component/myPage/myWards";
import TollIcon from "@mui/icons-material/Toll";
import { useRecoilValue } from "recoil";
import { userState } from "../../../recoil/UserAtom";

export default function MyPage() {
  const user = useRecoilValue(userState);

  const tabs = [
    { name: "진행중인 투표", component: <OngoingVote key={0} /> },
    { name: "종료된 투표", component: <ExpiredVote key={1} /> },
    { name: "작성한 글", component: <MyPosts key={2} /> },
    { name: "와드한 글", component: <MyWards key={3} /> },
  ];

  const [currentTab, setCurrentTab] = useState(0);
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
          <S.textAndBtn>
            <h1>{user.nickname}</h1>
            <Button1 text="정보수정" height={"30px"}></Button1>
          </S.textAndBtn>
          <S.textAndBtn>
            <TollIcon />
            <div>1,000P</div>
            <Button1 text="내역보기" height={"30px"}></Button1>
          </S.textAndBtn>
        </S.textSection>
        <AccountCard />
      </S.profileInfo>
      <S.tabGroup>
        {tabs.map((tab, index) => (
          <h3
            key={index}
            onClick={() => setCurrentTab(index)}
            className={currentTab === index ? "active" : ""}
          >
            {tab.name}
          </h3>
        ))}
      </S.tabGroup>
      <S.bottomSection>{tabs[currentTab].component}</S.bottomSection>
    </>
  );
}
