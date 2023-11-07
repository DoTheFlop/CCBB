import ReactPlayer from "react-player";
import Headermenu from "../../../../component/common/headers/headermenu";
import * as S from "./style";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import PromisePage from "./promisePage";
import UserProfile from "../../../../component/common/profile";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import ThumbUpOffAltIcon from "@mui/icons-material/ThumbUpOffAlt";
import ThumbDownOffAltIcon from "@mui/icons-material/ThumbDownOffAlt";
import ThumbUpAltIcon from "@mui/icons-material/ThumbUpAlt";
import ThumbDownAltIcon from "@mui/icons-material/ThumbDownAlt";
import { useState } from "react";
import { Link } from "react-router-dom";
import Button1 from "../../../../component/common/buttons";
import InputComment from "../../../../component/common/inputs/inputcomment";
import CommentBox from "../../../../component/commentBox";

export default function LoLvoteDetailPage() {
  const [isPromisePageOpen, setIsPromisePageOpen] = useState(false);
  const [isWard, setIsWard] = useState(false);
  const [isThumbUp, setIsThumbup] = useState(false);
  const [isThumbDown, setIsThumbDown] = useState(false);

  const toggleThumbUp = () => {
    setIsThumbup(!isThumbUp);
  };

  const toggleThumbDown = () => {
    setIsThumbDown(!isThumbDown);
  };

  const toggleWard = () => {
    setIsWard(!isWard);
  };
  const togglePromisePage = () => {
    setIsPromisePageOpen(!isPromisePageOpen);
  };

  const dummyData = [
    {
      title: "이거 내잘못임??",
      content:
        "아니 어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구어쩌구저쩌구 그래서 내가 잘못임?",
    },
  ];

  const dummyData1 = [
    {
      argument: "13분10초에 갈리오가 궁을 쓰는게 맞냐?",
      tier_limit: "브론즈",
      promise: "지는사람이 번지점프를 하겠습니다.",
      deposit: "30000",
    },
  ];

  const dummyData2 = [
    {
      id: 1,
      videoUrl: "../resource/LoLsample.mp4",
      title: "Video 1 Title",
      amount: 100,
    },
  ];

  return (
    <S.Main>
      <S.Head>
        <S.Headtop>
          <Headermenu title={"리그 오브 레전드"} />
        </S.Headtop>
      </S.Head>
      <S.Votebodycover>
        <S.Menuhead>
          <S.HeadLeft>
            <S.Headtop>
              <Link to="/lolvote">
                <ArrowBackIosNewIcon style={{ padding: "5px" }} />
              </Link>
              <h1>{dummyData[0].title}</h1>
            </S.Headtop>
            <S.Headbottom></S.Headbottom>
          </S.HeadLeft>
          <S.HeadRight>
            <img
              src="../resource/silver.png"
              alt=""
              style={{ height: "100px", paddingRight: "50px" }}
            />
          </S.HeadRight>
        </S.Menuhead>
        <S.DetailBody>
          <S.Moviebody>
            <ReactPlayer
              url={dummyData2[0].videoUrl}
              controls
              width="80%"
              height=""
              style={{
                border: "3px solid #ccc",
                borderRadius: "30px",
                overflow: "hidden",
              }}
            />
          </S.Moviebody>

          <S.Votebody>
            {dummyData[0].content}
            <S.PromiseP>
              <h3 onClick={togglePromisePage}>공약</h3>
              {isPromisePageOpen ? (
                <ArrowDropDownIcon onClick={togglePromisePage} />
              ) : (
                <PlayArrowIcon onClick={togglePromisePage} />
              )}
            </S.PromiseP>

            <S.PromisePageWrapper isOpen={isPromisePageOpen}>
              <PromisePage promise={dummyData1[0].promise} />
            </S.PromisePageWrapper>

            <S.VoteBodybot>
              <h3>{dummyData1[0].argument}</h3>
              <h4>옳다고 생각하는 유저에 투표해주세요</h4>
              <S.Votebutton>
                <S.ProfileBox backgroundColor="#97A7FF">
                  <UserProfile name={"챌우혁"} color={"black"} />
                  <S.ImgTier
                    src="../resource/silver.png"
                    alt="VS Logo"
                    style={{ height: "50px" }}
                  />
                </S.ProfileBox>
                <S.ImgVS
                  src="../resource/VSlogo.png"
                  alt="VS Logo"
                  style={{ height: "50px" }}
                />
                <S.ProfileBox backgroundColor="#FF9797">
                  <UserProfile name={"브우혁"} color={"black"} />
                  <S.ImgTier
                    src="../resource/challenger.png"
                    alt="VS Logo"
                    style={{ height: "50px" }}
                  />
                </S.ProfileBox>
              </S.Votebutton>
              <S.ArticleMenu>
                {isThumbUp ? (
                  <ThumbUpAltIcon
                    onClick={toggleThumbUp}
                    style={{ fontSize: "50px", cursor: "pointer" }}
                  />
                ) : (
                  <ThumbUpOffAltIcon
                    onClick={toggleThumbUp}
                    style={{ fontSize: "50px", cursor: "pointer" }}
                  />
                )}

                {isWard ? (
                  <S.Imgward
                    onClick={toggleWard}
                    src="../resource/wardafter.png"
                    alt="VS Logo"
                    style={{ height: "80px", cursor: "pointer" }}
                  />
                ) : (
                  <S.Imgward
                    onClick={toggleWard}
                    src="../resource/wardbefore.png"
                    alt="VS Logo"
                    style={{ height: "80px", cursor: "pointer" }}
                  />
                )}

                {isThumbDown ? (
                  <ThumbDownAltIcon
                    onClick={toggleThumbDown}
                    style={{ fontSize: "50px", cursor: "pointer" }}
                  />
                ) : (
                  <ThumbDownOffAltIcon
                    onClick={toggleThumbDown}
                    style={{ fontSize: "50px", cursor: "pointer" }}
                  />
                )}
              </S.ArticleMenu>
            </S.VoteBodybot>
          </S.Votebody>
          <S.BodyBottom>
            <S.Createcomment>
              <InputComment
                label="댓글작성"
                id="댓글작성"
                width="100%"
                height="50px"
              />
            </S.Createcomment>
            <S.Createcomment1>
              <Button1
                text={"등록"}
                width={"80px"}
                height={"40px"}
                onClick={""}
              />
            </S.Createcomment1>

            <h4>댓글 00개</h4>
            <S.CommentBody>
              <CommentBox
                bgcolor="#97A7FF"
                comment="This is a comment"
                userId="user123"
                date="2023-10-31"
              />
              <CommentBox
                bgcolor="#97A7FF"
                comment="This is a comment"
                userId="user123"
                date="2023-10-31"
              />
              <CommentBox
                bgcolor="#97A7FF"
                comment="This is a comment"
                userId="user123"
                date="2023-10-31"
              />
              <CommentBox
                bgcolor="#97A7FF"
                comment="This is a comment"
                userId="user123"
                date="2023-10-31"
              />
              <CommentBox
                bgcolor="#97A7FF"
                comment="This is a comment"
                userId="user123"
                date="2023-10-31"
              />
              <CommentBox
                bgcolor="#97A7FF"
                comment="This is a comment"
                userId="user123"
                date="2023-10-31"
              />
            </S.CommentBody>
          </S.BodyBottom>
        </S.DetailBody>
      </S.Votebodycover>
    </S.Main>
  );
}
