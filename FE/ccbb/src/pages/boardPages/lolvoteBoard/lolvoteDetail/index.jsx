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
import React, { useState } from "react";
import { Link, useParams } from "react-router-dom";
import InputComment from "../../../../component/common/inputs/inputcomment";
import CommentBox from "../../../../component/commentBox";
import { ccbbApi } from "../../../../api/ccbbApi";
import { useRecoilValue } from "recoil";
import { userState } from "../../../../recoil/UserAtom";
import TierImg from "../../../../component/tier";

export default function LoLvoteDetailPage() {
  const userInfo = useRecoilValue(userState);
  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  const postId = useParams().postId;
  const [curPost, SetCurPost] = useState({
    title: "",
    content: "",
    vote: {
      promise: "",
      argument: "",
    },
  });
  const [comments, SetComments] = useState([]);
  const [isPromisePageOpen, setIsPromisePageOpen] = useState(false);
  const [isWard, setIsWard] = useState(false);
  const [isThumbUp, setIsThumbup] = useState(false);
  const [isThumbDown, setIsThumbDown] = useState(false);

  React.useEffect(() => {
    fetchPost();
    fetchComments();
  }, []);

  const fetchPost = () => {
    ccbbApi
      .get("/post/vote/detail", {
        params: { postId: postId },
      })
      .then((res) => {
        console.log(res.data.voteList);
        SetCurPost(res.data.voteList);
      })
      .catch((e) => console.log(e));
  };

  const fetchComments = () => {
    ccbbApi
      .get(`/comment/${postId}`)
      .then((res) => {
        SetComments(res.data.commentList);
      })
      .catch((e) => console.log(e));
  };

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

  const [myComment, SetMyComment] = useState("");

  const handleOnKeyPress = (e) => {
    if (e.key === "Enter" && !e.shiftKey && myComment) {
      e.preventDefault();
      console.log("엔터키입력", postId);
      console.log(myComment);
      postComment(); // Enter 입력시 댓글 등록
      e.target.value = "";
    }
  };

  //댓글 전송
  const postComment = () => {
    ccbbApi
      .post(
        "/comment/add",
        JSON.stringify({ content: myComment, postId: postId }),
        { headers }
      )
      .then((res) => {
        SetMyComment("");
        fetchComments();
      })
      .catch((e) => console.log(e));
  };

  //댓글 수정
  const handleModifyComment = (commentId, newContent) => {
    console.log("댓글 수정");
    ccbbApi
      .put(
        "/comment/modify",
        JSON.stringify({
          commentId: commentId,
          content: newContent,
        }),
        { headers }
      )
      .then((res) => {
        fetchComments();
      })
      .catch((e) => console.log(e));
  };
  //댓글 삭제
  const handleDeleteComment = (commentId) => {
    ccbbApi
      .delete("/comment/delete", {
        headers,
        params: {
          commentId: commentId,
        },
      })
      .then((res) => {
        console.log(res);
        fetchComments();
      })
      .catch((e) => console.log(e));
  };

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
              <h1>{curPost.title}</h1>
            </S.Headtop>
            <S.Headbottom></S.Headbottom>
          </S.HeadLeft>
          <S.HeadRight>
            <TierImg tier={curPost.vote.limitTier} size={"100px"} />
          </S.HeadRight>
        </S.Menuhead>
        <S.DetailBody>
        <S.Moviebody>
  {curPost.fileId && curPost.fileId.length > 0 && (
    <ReactPlayer
      url={`https://ccbb.pro/api/file/get/${curPost.fileId[0].fileId}`}
      controls
      width="80%"
      height=""
      style={{
        border: "3px solid #ccc",
        borderRadius: "30px",
        overflow: "hidden",
      }}
    />
  )}
</S.Moviebody>

          <S.Votebody>
            {curPost.content}
            <S.PromiseP>
              <h3 onClick={togglePromisePage}>공약</h3>
              {isPromisePageOpen ? (
                <ArrowDropDownIcon onClick={togglePromisePage} />
              ) : (
                <PlayArrowIcon onClick={togglePromisePage} />
              )}
            </S.PromiseP>

            <S.PromisePageWrapper $opened={isPromisePageOpen}>
              <PromisePage promise={curPost.vote.promise} />
            </S.PromisePageWrapper>

            <S.VoteBodybot>
              <h3>{curPost.vote.argument}</h3>
              <h4>옳다고 생각하는 유저에 투표해주세요</h4>
              <S.Votebutton>
                <S.ProfileBox $bgcolor="#97A7FF">
                  <UserProfile name={curPost.vote.nickname1} color={"black"} />
                  {/* <S.ImgTier
                    src="../resource/silver.png"
                    alt="VS Logo"
                    style={{ height: "50px" }}
                  /> */}
                </S.ProfileBox>
                <S.ImgVS
                  src="../resource/VSlogo.png"
                  alt="VS Logo"
                  style={{ height: "50px" }}
                />
                <S.ProfileBox $bgcolor="#FF9797">
                  <UserProfile name={curPost.vote.nickname2} color={"black"} />
                  {/* <S.ImgTier
                    src="../resource/challenger.png"
                    alt="VS Logo"
                    style={{ height: "50px" }}
                  /> */}
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
            {token && (
              <S.Createcomment>
                <InputComment
                  className="comment-input"
                  label="댓글작성"
                  id="댓글작성"
                  height="60px"
                  value={myComment}
                  onKeyPress={handleOnKeyPress}
                  onChange={(e) => {
                    SetMyComment(e.target.value);
                  }}
                  onClick={postComment}
                />
              </S.Createcomment>
            )}

            <h4>댓글 {curPost.comment.length}개</h4>
            <S.CommentBody>
              <CommentBox
                bgcolor="#97A7FF"
                comment="This is a comment"
                userId="user123"
                date="2023-10-31"
              />
              {comments.map((cmt, index) => {
                return (
                  <CommentBox
                    key={index}
                    isMine={userInfo.userId === cmt.userId}
                    userId={cmt.userId}
                    nickname={cmt.nickname}
                    comment={cmt.content}
                    date={cmt.createDate}
                    position={cmt.position}
                    onClickModify={(newContent) =>
                      handleModifyComment(cmt.commentId, newContent)
                    }
                    onClickDelete={() => handleDeleteComment(cmt.commentId)}
                  />
                );
              })}
            </S.CommentBody>
          </S.BodyBottom>
        </S.DetailBody>
      </S.Votebodycover>
    </S.Main>
  );
}
