import React, { useState } from "react";
import * as S from "./style";
import AccountCard from "../../../component/accountCard";
import OngoingVote from "../../../component/myPage/ongoingVote";
import MyPosts from "../../../component/myPage/myPosts";
import MyWards from "../../../component/myPage/myWards";

export default function MyPage() {
  const tabs = [
    { name: "진행중인 투표", component: <OngoingVote key={0} /> },
    { name: "작성한 글", component: <MyPosts key={1} /> },
    { name: "와드한 글", component: <MyWards key={2} /> },
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
          <div>닉네임</div>
          <div>1,000P</div>
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
