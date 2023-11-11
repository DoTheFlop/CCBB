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
import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import InputComment from "../../../../component/common/inputs/inputcomment";
import CommentBox from "../../../../component/commentBox";
import { ccbbApi } from "../../../../api/ccbbApi";
import { useRecoilValue } from "recoil";
import { userState } from "../../../../recoil/UserAtom";
import VotePaymentModal from "../lolvoteCreate/votePaymentpage";
import Button1 from "../../../../component/common/buttons";
import TierImg from "../../../../component/tier";
import VoteProcess from "../../../../component/voteBoard/voteProcess";
import { isBefore } from "date-fns";

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
  const [isApproved, setIsApproved] = useState(false);
  const [isOpen, setIsOpen] = useState(false);
  const [payment, setPayment] = useState(null);
  const token1 = localStorage.getItem("token");
  const [userPick, setUserPick] = useState(null);

  useEffect(() => {
    fetchPost();
    fetchComments();
    console.log(userPick);
  }, [userPick]);

  const isMyVote = () => {
    //(로그인한) 유저가 투표 당사자인지 체크
    return (
      userInfo?.userId === curPost?.vote.user1 ||
      userInfo?.userId === curPost?.vote.user2
    );
  };

  const voteStep = () => {
    //투표 진행단계를 0~3 중 하나의 숫자로 리턴함 (수락대기/투표진행/공약이행/보증금반환)
    if (!isApproved) return 0;
    const now = new Date();
    const endDate = new Date(curPost?.vote.deadline);
    if (isBefore(now, endDate)) return 1;
    if (!curPost?.vote.doPromise) return 2;
    return 3;
  };

  const fetchPost = () => {
    const headers = {
      Authorization: `Bearer ${token1}`,
    };

    ccbbApi
      .get("/post/vote/detail", {
        params: { postId: postId },
      })
      .then((res) => {
        // console.log(res.data);
        setIsApproved(res.data.voteList.vote.accept2);
        ccbbApi
          .get(`/vote/userPick?voteId=${res.data.voteList.vote.voteId}`, {
            headers,
          })
          .then((res) => {
            console.log(res.data.voteResult.userPick);
            if (res.data.voteResult.userPick === 1) {
              setUserPick(true);
            }
          })
          .catch((e) => console.log(e));
        SetCurPost(res.data.voteList);
      })
      .catch((e) => console.log(e));
  };

  const fetchComments = () => {
    ccbbApi
      .get(`/comment/${postId}`)
      .then((res) => {
        SetComments(res.data.commentList);
        // console.log(res.data);
      })
      .catch((e) => console.log(e));
  };

  const payresponse = () => {
    ccbbApi
      .post(
        `/payment/add?postId=${curPost.postId}&price=${curPost.vote.deposit}`,
        {},
        { headers }
      )
      .then((res) => {
        setPayment(res.data);
        // console.log(payment);
        console.log(res.data);
        openModalHandler();
      });
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
      console.log(myComment);
      postComment(); // Enter 입력시 댓글 등록
      e.target.value = "";
    }
  };

  const openModalHandler = () => {
    setIsOpen(!isOpen);
  };
  const handlevoteUser1 = (e) => {
    console.log(curPost.vote.voteId);
    const headers = {
      Authorization: `Bearer ${token1}`,
    };
    const body = {
      ballotBoxId: 0,
      pick: 1,
      userId: 0,
      voteId: curPost.vote.voteId,
    };

    if (userPick) {
      alert("이미 투표를 하였습니다.");
      return;
    } else {
      ccbbApi.post("/vote/ballet/add", body, { headers }).then((e) => {
        console.log(e);
        setUserPick(true);
      });
    }
  };

  const handlevoteUser2 = (e) => {
    const headers = {
      Authorization: `Bearer ${token1}`,
    };
    const body = {
      ballotBoxId: 0,
      pick: 2,
      userId: 0,
      voteId: curPost.vote.voteId,
    };
    if (userPick) {
      alert("이미 투표를 하였습니다.");
      return;
    } else {
      ccbbApi.post("/vote/ballet/add", body, { headers }).then((e) => {
        console.log(e);
        setUserPick(true);
      });
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
          {/* 투표 당사자일때만 진행상황이 보임 */}
          {isMyVote() && <VoteProcess step={voteStep()} />}
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

            {isApproved ? (
              <S.VoteBodybot>
                <h3>{curPost.vote.argument}</h3>
                <h4>옳다고 생각하는 유저에 투표해주세요</h4>
                <S.Votebutton>
                  <S.ProfileBox
                    onClick={(e) => {
                      handlevoteUser1();
                    }}
                    $bgcolor="#97A7FF"
                  >
                    <UserProfile
                      name={curPost.vote.nickname1}
                      color={"black"}
                    />
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
                  <S.ProfileBox
                    onClick={(e) => {
                      handlevoteUser2();
                    }}
                    $bgcolor="#FF9797"
                  >
                    <UserProfile
                      name={curPost.vote.nickname2}
                      color={"black"}
                    />
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
            ) : userInfo.userId === curPost?.vote.user2 ? (
              <S.VoteBodybot>
                <h4>해당 투표를 진행하시겠습니까?</h4>
                <S.VoteBodyButtonBox>
                  <Button1
                    onClick={payresponse}
                    text="수락"
                    width="150px"
                    height="50px"
                  ></Button1>
                  <Button1
                    text="거절"
                    width="150px"
                    height="50px"
                    color="#8B0000"
                  ></Button1>
                </S.VoteBodyButtonBox>
                <VotePaymentModal
                  isOpen={isOpen}
                  onClose={openModalHandler}
                  payment={payment}
                />
              </S.VoteBodybot>
            ) : (
              <></>
            )}
          </S.Votebody>

          {isApproved && (
            <S.BodyBottom>
              {token && (
                <S.Createcomment>
                  <InputComment
                    className="comment-input"
                    label="댓글작성"
                    id="댓글작성"
                    height="60px"
                    value={myComment}
                    btn={true}
                    onKeyPress={handleOnKeyPress}
                    onChange={(e) => {
                      SetMyComment(e.target.value);
                    }}
                    onClick={postComment}
                  />
                </S.Createcomment>
              )}

              <h4>
                댓글 {curPost && curPost.comment && curPost.comment.length}개
              </h4>
              <S.CommentBody>
                <CommentBox
                  bgcolor="#97A7FF"
                  comment="This is a hard-coded sample comment"
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
          )}
        </S.DetailBody>
      </S.Votebodycover>
    </S.Main>
  );
}
