import React, { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";
import * as S from "./style";
import Button1 from "../../../component/common/buttons";
import AccountCard from "../../../component/accountCard";
import OngoingVote from "../../../component/myPage/ongoingVote";
import ExpiredVote from "../../../component/myPage/expiredVote";
// import MyPosts from "../../../component/myPage/myPosts";
import MyWards from "../../../component/myPage/myWards";
import TollIcon from "@mui/icons-material/Toll";
import AddPhotoAlternateIcon from "@mui/icons-material/AddPhotoAlternate";
import { useRecoilValue, useSetRecoilState } from "recoil";
import { userState } from "../../../recoil/UserAtom";
import { ccbbApi } from "../../../api/ccbbApi";

export default function MyPage() {
  const navigate = useNavigate();
  const token = localStorage.getItem("token");
  const headers = {
    "Content-Type": "multipart/form-data",
    Authorization: `Bearer ${token}`,
  };
  const user = useRecoilValue(userState);
  const setRecoilUser = useSetRecoilState(userState);
  React.useEffect(() => {
    if (!token) navigate("/");
    ccbbApi
      .get("/user/profile", { headers })
      .then((res) => {
        console.log(res);
        if (res.data.user) {
          console.log("프로필정보: ", res.data);
          setRecoilUser(res.data.user);
        }
      })
      .catch((e) => console.log(e));
  }, [setRecoilUser]);

  const tabs = [
    { name: "진행중인 투표", component: <OngoingVote key={0} /> },
    { name: "종료된 투표", component: <ExpiredVote key={1} /> },
    // { name: "작성한 글", component: <MyPosts key={2} /> },
    { name: "와드한 글", component: <MyWards key={2} /> },
  ];

  const [currentTab, setCurrentTab] = useState(0);

  const fileInput = useRef();
  const onClickUploadImgBtn = () => {
    if (!fileInput.current) {
      return;
    }
    fileInput.current.click();
  };
  const handleChangeProfileImg = (e) => {
    const file = e.currentTarget.files[0];
    if (file) {
      const formData = new FormData();
      formData.append("file", file);

      ccbbApi
        .post("/profileimg/modify", formData, { headers })
        .then((res) => {
          console.log("프사 업데이트됨");
          console.log(res);
        })
        .catch((e) => {
          console.log(e);
        });
    }
  };

  return (
    <>
      <S.profileInfo>
        <S.imgSection>
          <S.Img
            src={`${process.env.REACT_APP_BASE_SERVER}profileimg/${
              user.userId
            }?${Date.now()}`}
            alt="profile-img"
          />
          <AddPhotoAlternateIcon onClick={onClickUploadImgBtn} />
          {/* input은 display: none이고, 아이콘이 버튼 역할을 함 */}
          <input
            type="file"
            ref={fileInput}
            onChange={handleChangeProfileImg}
            accept="image/jpeg, image/jpg, image/png"
          />
        </S.imgSection>
        <S.textSection>
          <S.textAndBtn>
            <h1>{user.nickname}</h1>
            <Button1
              text="정보수정"
              height={"30px"}
              onClick={() => {
                navigate("/mypage/pwcheck");
              }}
            ></Button1>
          </S.textAndBtn>
          <p>{user.email}</p>
          <S.textAndBtn>
            <TollIcon />
            <div>{user.point}P</div>
            <Button1 text="내역보기" height={"30px"}></Button1>
          </S.textAndBtn>
        </S.textSection>
        <AccountCard loltier={user.lol} />
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
