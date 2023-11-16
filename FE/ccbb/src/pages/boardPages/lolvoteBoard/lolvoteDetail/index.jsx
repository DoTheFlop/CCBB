import ReactPlayer from "react-player";
import Headermenu from "../../../../component/common/headers/headermenu";
import * as S from "./style";
import ArrowBackIosNewIcon from "@mui/icons-material/ArrowBackIosNew";
import UserProfile from "../../../../component/common/profile";
import PlayArrowIcon from "@mui/icons-material/PlayArrow";
import ArrowDropDownIcon from "@mui/icons-material/ArrowDropDown";
import React, { useEffect, useState } from "react";
import { Link, useParams, useNavigate } from "react-router-dom";
import InputComment from "../../../../component/common/inputs/inputcomment";
import CommentBox from "../../../../component/commentBox";
import { ccbbApi } from "../../../../api/ccbbApi";
import { useRecoilValue } from "recoil";
import { userState } from "../../../../recoil/UserAtom";
import VotePaymentModal from "../lolvoteCreate/votePaymentpage";
import Button1 from "../../../../component/common/buttons";
import TierImg from "../../../../component/tier";
import VoteProcess from "../../../../component/voteBoard/voteProcess";
import { intervalToDuration, isBefore } from "date-fns";
import { isAfter } from "date-fns";
import VoteRate from "../../../../component/voteBoard/voteRate";
import FileUploadIcon from "@mui/icons-material/FileUpload";
import RemoveCircleIcon from "@mui/icons-material/RemoveCircle";

import DownloadIcon from "@mui/icons-material/Download";
import Loading from "../../../../component/common/Loading";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import axios from "axios";
export default function LoLvoteDetailPage() {
  const navigate = useNavigate();
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
  const [isApproved, setIsApproved] = useState(false);
  const [isOpen, setIsOpen] = useState(false);
  const [payment, setPayment] = useState(null);
  const [userPick, setUserPick] = useState(0); // 0: 미투표, 투표했으면 1 or 2
  const [modalMessage, setModalMessage] = useState("");
  const [modalOpen, setModalOpen] = useState(false);
  const [isModalOpen, setIsOpenModal] = useState(false);
  const [timeLeft, setTimeLeft] = useState(""); // 종료일 나타내기 위해서
  const [voteResult, setVoteResult] = useState({ pick1: 0, pick2: 0 });
  const [isLoading, setIsLoading] = useState(false);
  const options = [
    { value: "IRON", label: "아이언" },
    { value: "BRONZE", label: "브론즈" },
    { value: "SILVER", label: "실버" },
    { value: "GOLD", label: "골드" },
    { value: "PLATINUM", label: "플래티넘" },
    { value: "EMERALD", label: "에메랄드" },
    { value: "DIAMOND", label: "다이아몬드" },
    { value: "MASTER", label: "마스터" },
    { value: "GRANDMASTER", label: "그랜드마스터" },
    { value: "CHALLENGER", label: "챌린저" },
  ];
  const [selectedTier, setSelectedTier] = useState(options[0].value);
  const [minVotingTier, setMinVotingTier] = useState(options[0].value);
  const userSelf = useRecoilValue(userState);

  const [user1, setUser1] = useState(""); // 누가이겼는지 확인하기위해
  const [user2, setUser2] = useState(""); // 누가이겼는지 확인하기위해
  const [curUser, setCurUser] = useState("");
  const [winner, setWinner] = useState("");
  const [loser, setLoser] = useState("");
  const [selectedFile, setSelectedFile] = useState(null);
  const [dPromise, setDPromise] = useState(null);
  const [promiseFile, setPromiseFile] = useState({
    hasPromise: false,
    fileType: null,
  });
  const fetchPost = () => {
    return ccbbApi
      .get("/post/vote/detail", {
        params: { postId: postId },
      })
      .then((res) => {
        // console.log("res:", res); // res 전체를 출력
        // console.log("res.data:", res.data); // res.data 출력
        // console.log("res.data.voteList:", res.data.voteList); // res.data.voteList 출력
        // console.log("res.data.voteList.vote:", res.data.voteList.vote); // res.data.voteList.vote 출력
        // console.log(
        //   "res.data.voteList.vote.limitTier:",
        //   res.data.voteList.vote.limitTier
        // ); // res.data.voteList.vote.limitTier 출력
        setMinVotingTier(res.data.voteList.vote.limitTier);
        return res;
      })
      .catch((error) => {
        console.log("API 호출 에러:", error);
      });
  };

  useEffect(() => {
    try {
      setIsLoading(true);
      fetchPost().then((res) => {
        console.log(res.data);
        setIsApproved(res.data.voteList.vote.accept2);
        let user1 = res.data.voteList.vote.user1;
        let user2 = res.data.voteList.vote.user2;
        processFileData(res.data.voteList.fileId);
        setIsApproved(res.data.voteList.vote.accept2);
        setDPromise(res.data.voteList.vote.doPromise);
        if (token) {
          ccbbApi
            .get(`/vote/userPick?voteId=${res.data.voteList.vote.voteId}`, {
              headers,
            })
            .then((res) => {
              console.log("기표함결과");
              console.log(res);
              if (res.data.voteResult.userPick) {
                setUserPick(true);
              }
            })
            .catch((e) => {
              console.log(e);
            });

          ccbbApi
            .get(`/wod/check?postId=${res.data.voteList.vote.postId}`, {
              headers,
            })
            .then((res) => {
              setIsWard(res.data.wodCheck);
              console.log(res);
            })
            .catch((e) => {
              console.log(e);
            });

          ccbbApi.get("/user/profile", { headers }).then((res) => {
            // user1이 이겼을때 승자를 user1으로 세팅
            setCurUser(res.data.user.userId);
            setUser1(user1);
            setUser2(user2);
            if (voteResult.pick1 > voteResult.pick2) {
              setWinner(user1);
              setLoser(user2);
            }
            // user2가 이겼을때 승자를 user2로 세팅
            else if (voteResult.pick1 < voteResult.pick2) {
              setWinner(user2);
              setLoser(user1);
            }
            console.log(`현재유저 : ${res.data.user.userId}`);
            console.log(`유저1 : ${user1}`);
            console.log(`유저2 : ${user2}`);
            console.log(`승자 : ${winner}`);
            console.log(`패자 : ${loser}`);
            console.log(voteResult.pick1);
          });
        }

        SetCurPost(res.data.voteList);

        fetchComments();
        setIsLoading(false);

        // 현재 시간이 deadline을 지난지 확인
        const now = new Date();
        const deadline = new Date(res.data.voteList.vote.deadline);
        if (isAfter(now, deadline)) {
          fetchVoteResult(res.data.voteList.vote.voteId); // 투표 결과를 가져오는 함수 호출
        }
      });
    } catch {
      setIsLoading(false);
    }
  }, [userPick]);
  const now = new Date();
  const fetchVoteResult = (voteId) => {
    ccbbApi
      .get(`/vote/result`, {
        params: { voteId: voteId },
      })
      .then((res) => {
        console.log("voteResult response:", res.data); // 추가된 부분
        setVoteResult(res.data.voteResult); // 응답으로 받은 투표 결과를 상태 변수에 저장
      })
      .catch((e) => {
        console.log(e);
      });
  };

  // 두 번째 useEffect: curPost의 변경을 감지하여 타이머 업데이트
  useEffect(() => {
    const updateTimer = () => {
      if (curPost.vote?.deadline) {
        const deadline = new Date(curPost.vote.deadline);
        const now = new Date();

        // 현재 시간이 deadline을 지났는지 확인
        if (isAfter(now, deadline)) {
          const duration = intervalToDuration({ start: deadline, end: now });
          const timePassed = `${duration.days}일 ${duration.hours}시간 ${duration.minutes}분 지남`;
          setTimeLeft(timePassed);
        } else {
          const duration = intervalToDuration({ start: now, end: deadline });
          const timeLeft = `${duration.days}일 ${duration.hours}시간 ${duration.minutes}분 남음`;
          setTimeLeft(timeLeft);
        }
      }
    };

    updateTimer();
    const intervalId = setInterval(updateTimer, 60000);

    return () => {
      clearInterval(intervalId);
    };
  }, [curPost]);

  const processFileData = (fileArray) => {
    for (const file of fileArray) {
      if (file.isPromise) {
        setPromiseFile({ hasPromise: true, fileType: file.type });
      }
    }
  };

  const isMyVote = () => {
    //(로그인한) 유저가 투표 당사자인지 체크
    return (
      userInfo?.userId === curPost.vote?.user1 ||
      userInfo?.userId === curPost.vote?.user2
    );
  };

  const voteStep = () => {
    //투표 진행단계를 0~3 중 하나의 숫자로 리턴함 (수락대기/투표진행/공약이행/보증금반환)
    if (!isApproved) return 0;
    const now = new Date();
    const endDate = new Date(curPost.vote?.deadline);
    if (isBefore(now, endDate)) return 1;
    if (!curPost.vote?.doPromise) return 2;
    return 3;
  };

  const fetchComments = () => {
    ccbbApi
      .get(`/comment/${postId}`)
      .then((res) => {
        console.log(res.data.commentList);
        SetComments(res.data.commentList);
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
        openModalHandler();
      });
  };

  const voteReject = () => {
    if (window.confirm("정말 거절하시겠습니까? 게시글은 삭제됩니다.")) {
      ccbbApi
        .delete(`/post/reject/${curPost.postId}`, { headers }, {})
        .then((res) => {
          toast.error("거절하였습니다.");
          navigate("/lolvote");
        });
    }
  };

  const toggleWard = () => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    // 와드를 했을경우
    if (isWard) {
      ccbbApi
        .delete(`/wod/delete/${postId}`, { headers })
        .then((res) => {
          console.log(res);
          setIsWard(!isWard);
        })
        .catch((e) => console.log(e));
    }
    // 와드를 안했을 경우
    else if (!isWard) {
      ccbbApi
        .post(`/wod/add?postId=${postId}`, {}, { headers })
        .then((res) => {
          console.log(res);
          setIsWard(!isWard);
        })
        .catch((e) => console.log(e));
    }
  };
  const togglePromisePage = () => {
    setIsPromisePageOpen(!isPromisePageOpen);
  };

  const [myComment, SetMyComment] = useState("");

  const handleOnKeyPress = (e) => {
    if (e.key === "Enter" && !e.shiftKey && myComment) {
      e.preventDefault();
      postComment(); // Enter 입력시 댓글 등록
      e.target.value = "";
    }
  };

  const openModalHandler = () => {
    setIsOpen(!isOpen);
  };

  const handleMinVotingTierChange = (selected) => {
    const selectedValue = selected.value;
    setMinVotingTier(selectedValue);
  };
  // 투표 가능 여부 판단 함수
  const isEligibleToVote = (userTier, minimumTier) => {
    const userTierIndex = options.findIndex(
      (option) => option.value === userTier
    );
    const minimumTierIndex = options.findIndex(
      (option) => option.value === minimumTier
    );

    console.log(userTier, "는", userTierIndex);
    console.log(minimumTier, "는", minimumTierIndex);
    return userTierIndex >= minimumTierIndex;
  };

  // 투표 함수
  const handlevoteUser = (pickSide) => {
    if (!token) {
      //비로그인 유저는 투표못함
      toast.error("로그인 후 투표하세요");
      // navigate("/signin");
      return;
    }

    const minimumTier = minVotingTier; // 최소 투표 가능 티어. 선택된 티어를 최소 투표 가능 티어로 설정합니다.
    console.log(userSelf.lol);
    console.log(minVotingTier);
    console.log(minimumTier);

    if (!isEligibleToVote(userSelf.lol, minimumTier)) {
      toast.error("당신의 티어는 투표를 할 수 없습니다.");
      return;
    }

    const headers = {
      Authorization: `Bearer ${token}`,
    };
    const body = {
      ballotBoxId: 0,
      pick: pickSide,
      userId: userSelf.id, // 로그인한 유저의 ID를 사용합니다.
      voteId: curPost.vote.voteId,
    };

    if (userPick > 0) {
      toast.error("이미 투표를 하였습니다.");
      return;
    } else {
      ccbbApi
        .post("/vote/ballet/add", body, { headers })
        .then((res) => {
          setUserPick(pickSide);
          const votedUserNickname =
            pickSide === 1 ? curPost.vote.nickname1 : curPost.vote.nickname2;
          toast.success(
            `성공적으로 ${votedUserNickname}님에게 투표하였습니다.`
          );
        })
        .catch((e) => {
          console.log(e);
          toast.error("투표 중 오류가 발생하였습니다.");
        });
    }
  };

  const openModal = (message) => {
    setModalMessage(message);
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  const voteSuccess = () => {
    // 기타 코드...
    openModal("투표가 성공적으로 이루어졌습니다.");
  };

  //댓글 전송
  const postComment = () => {
    if (myComment.length === 0) return;
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
        // console.log(res);
        fetchComments();
      })
      .catch((e) => console.log(e));
  };

  const handleCreateButtonClick = () => {
    setIsOpenModal(true);
  };

  const handleCancelClick = () => {
    setIsOpenModal(false);
  };

  const handleAgreeClick = () => {
    setIsOpenModal(false);
    payresponse();
  };
  const handleFileChange = (event) => {
    const files = event.target.files;
    setSelectedFile(files[0]);
  };

  const handleAcceptButton = () => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    console.log(token);
    ccbbApi
      .post(
        `/post/promise/accept/${postId}`,
        {},
        {
          headers,
        }
      )
      .then((res) => {
        console.log(res);
        if (res.data === "공약 이행 완료") {
          setDPromise(true);
        }
      })
      .catch((e) => console.log(e));
  };
  const handleRefuseButton = () => {
    const headers = {
      Authorization: `Bearer ${token}`,
    };
    console.log("거절");
    ccbbApi
      .post(
        `/file/remove/promise/${postId}`,
        {},
        {
          headers,
        }
      )
      .then((res) => {
        console.log(res);
      })
      .catch((e) => console.log(e));
  };
  const handleUploadButton = async () => {
    const headers = {
      Authorization: `Bearer ${token}`,
      "content-type": "multipart/form-data",
    };
    const formData = new FormData();
    formData.append("file", selectedFile);
    console.log("Uploaded files:", selectedFile);

    setIsLoading(true);
    try {
      const response = await axios.post(
        `https://ccbb.pro/api/file/add/promise/${postId}`,
        formData,
        {
          headers,
        }
      );

      // Handle the response
      console.log("Upload success:", response);
      setIsLoading(false);
    } catch (error) {
      // Handle errors
      console.error("Upload error:", error);
      setIsLoading(false);
    }
  };

  return (
    <S.Main>
      <ToastContainer
        position="top-right"
        limit={1}
        closeButton={false}
        autoClose={2200}
        closeOnClick
        hideProgressBar
      />
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
            {isLoading ? (
              <div style={{ height: "100px" }}></div>
            ) : (
              <TierImg tier={curPost.vote.limitTier} size={"100px"} />
            )}
          </S.HeadRight>
        </S.Menuhead>
        {isLoading ? <Loading /> : null}
        {isLoading ? null : <S.TimeLeft>종료일 : {timeLeft}</S.TimeLeft>}
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
            {curPost.fileId && curPost.fileId.length > 1 && (
              <S.replaylinkBox>
                {curPost.fileId[1].extension === "bat" && (
                  <S.replaylink
                    href={`https://ccbb.pro/api/file/get/${curPost.fileId[1].fileId}`}
                    download
                  >
                    <DownloadIcon />
                    <p>리플레이파일</p>
                  </S.replaylink>
                )}
              </S.replaylinkBox>
            )}
            {curPost.content}
            {isLoading ? null : (
              <S.PromiseP>
                <h3 onClick={togglePromisePage}>공약</h3>
                {isPromisePageOpen ? (
                  <ArrowDropDownIcon onClick={togglePromisePage} />
                ) : (
                  <PlayArrowIcon onClick={togglePromisePage} />
                )}
              </S.PromiseP>
            )}

            <S.PromisePageWrapper $opened={isPromisePageOpen}>
              {curPost.vote.promise}

              {/* curPost.fileId 배열이 존재하고 최소 3개의 요소를 가지고 있을 때 파일 미리보기 부분을 렌더링하지 않음 */}
              {voteStep() === 2 && (
                <>
                  {dPromise && <h3>공약 이행</h3>}
                  {!promiseFile["hasPromise"] && (
                    <>
                      <S.FilePreview>
                        {selectedFile && (
                          <>
                            {selectedFile.type.startsWith("image/") ? (
                              <img
                                src={URL.createObjectURL(selectedFile)}
                                alt="Preview"
                                style={{ maxWidth: "100%", maxHeight: "200px" }}
                              />
                            ) : (
                              <video
                                key={URL.createObjectURL(selectedFile)} // Change the key here
                                controls
                                style={{ maxWidth: "100%", maxHeight: "200px" }}
                              >
                                <source
                                  src={URL.createObjectURL(selectedFile)}
                                  type={selectedFile.type}
                                />
                              </video>
                            )}
                          </>
                        )}
                      </S.FilePreview>
                      {/* 패자만 볼수있음 */}
                      {curUser === loser && (
                        <>
                          <input
                            type="file"
                            accept="image/*, video/*"
                            id="input-file"
                            onChange={handleFileChange}
                          />
                          <Button1
                            text={"등록"}
                            width={"55px"}
                            height={"30px"}
                            onClick={handleUploadButton}
                          ></Button1>
                        </>
                      )}
                    </>
                  )}

                  {/* curPost.fileId 배열이 존재하고 최소 3개의 요소를 가지고 있을 때 동영상 부분을 렌더링함 */}
                  {promiseFile["hasPromise"] && (
                    <S.MPbody>
                      {promiseFile["fileType"].startsWith("image/") ? (
                        <img
                          src={`https://ccbb.pro/api/file/get/promise/${postId}`}
                          alt="Preview"
                          style={{ width: "80%", height: "40%" }}
                        />
                      ) : (
                        <ReactPlayer
                          url={`https://ccbb.pro/api/file/get/promise/${postId}`}
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
                      {/* 승자만 볼수있음 */}
                      {!dPromise && curUser === winner && (
                        <S.ARWrapper>
                          <h1>공약을 이행했다고 생각하시나요?</h1>
                          <S.AWrapper>
                            <Button1
                              text={"O"}
                              width={"100px"}
                              height={"60px"}
                              onClick={handleAcceptButton}
                              size={"40px"}
                            />
                          </S.AWrapper>

                          <S.RWrapper>
                            <Button1
                              text={"X"}
                              width={"100px"}
                              height={"60px"}
                              onClick={handleRefuseButton}
                              color={"red"}
                              size={"40px"}
                            />
                          </S.RWrapper>
                        </S.ARWrapper>
                      )}
                    </S.MPbody>
                  )}
                </>
              )}
            </S.PromisePageWrapper>

            {/* 투표 진행중일 때 */}
            {voteStep() === 1 && (
              <S.VoteBodybot>
                <h3>{curPost.vote.argument}</h3>
                <h4>옳다고 생각하는 유저에 투표해주세요</h4>
                <S.Votebutton>
                  <S.ProfileBox
                    onClick={(e) => {
                      handlevoteUser(1);
                    }}
                    $bgcolor="#97A7FF"
                  >
                    <UserProfile
                      imgUrl={`${process.env.REACT_APP_BASE_SERVER}profileimg/${curPost.vote.user1}`}
                      name={curPost.vote.nickname1}
                      color={"black"}
                    />
                  </S.ProfileBox>
                  <S.ImgVS
                    src="../resource/VSlogo.png"
                    alt="VS Logo"
                    style={{ height: "50px" }}
                  />
                  <S.ProfileBox
                    onClick={(e) => {
                      handlevoteUser(2);
                    }}
                    $bgcolor="#FF9797"
                  >
                    <UserProfile
                      imgUrl={`${process.env.REACT_APP_BASE_SERVER}profileimg/${curPost.vote.user2}`}
                      name={curPost.vote.nickname2}
                      color={"black"}
                    />
                  </S.ProfileBox>
                </S.Votebutton>
              </S.VoteBodybot>
            )}
            {/* <p>{alert(curPost.voteList?.vote)}</p> */}
            {/* 미수락 투표글일때 - user2에게만 수락거절 버튼이 보임 */}
            {!isLoading && voteStep() === 0 ? (
              userInfo.userId !== curPost.vote?.user2 ? (
                <S.VoteBodybot>
                  <h3>{curPost.vote.argument}</h3>
                  <h2>⛔️ 상대방의 수락을 기다리고 있는 게시글입니다. ⛔️</h2>
                </S.VoteBodybot>
              ) : (
                <S.VoteBodybot>
                  <h3>{curPost.vote.argument}</h3>
                  <h4>해당 투표를 진행하시겠습니까?</h4>
                  <S.VoteBodyButtonBox>
                    <Button1
                      text="수락"
                      width="150px"
                      height="50px"
                      onClick={handleCreateButtonClick} // 변경된 부분: onClick 이벤트 핸들러로 payresponse 함수가 호출됩니다.
                    ></Button1>
                    <Button1
                      onClick={voteReject}
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
              )
            ) : (
              <></>
            )}

            {/* 종료된 투표일때 */}
            {voteStep() > 1 && (
              <S.VoteResultDisplay>
                <h3>투표 결과</h3>
                <VoteRate
                  cnt1={voteResult.pick1}
                  cnt2={voteResult.pick2}
                  nickname1={curPost.vote.nickname1}
                  nickname2={curPost.vote.nickname2}
                  userId1={curPost.vote.user1}
                  userId2={curPost.vote.user2}
                />
              </S.VoteResultDisplay>
            )}

            {/* 와드영역 - 구현완료한 다음에 비로그인일때 렌더링 막을것 */}
            {token && (
              <>
                {isLoading || voteStep() == 0 ? (
                  <S.EmptyDiv></S.EmptyDiv>
                ) : isWard ? (
                  <S.Imgward
                    onClick={toggleWard}
                    src="../resource/wardafter.png"
                    alt="VS Logo"
                    style={{
                      width: "120px",
                      height: "80px",
                      cursor: "pointer",
                    }}
                  />
                ) : (
                  <S.Imgward
                    onClick={toggleWard}
                    src="../resource/wardbefore.png"
                    alt="VS Logo"
                    style={{
                      width: "120px",
                      height: "80px",
                      cursor: "pointer",
                    }}
                  />
                )}
              </>
            )}
          </S.Votebody>

          {/* 댓글영역 */}
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

              <h4>댓글 {comments.length}개</h4>
              <S.CommentBody>
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
                      tier={cmt.tier}
                      pick={cmt.pick}
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
      {isModalOpen && (
        <S.DepositModal
          isOpen={isModalOpen}
          onClose={handleCancelClick}
          onAgree={handleAgreeClick}
        >
          공약을 이행하지 않으면 결제하신 보증금은 기부된다는 내용에
          동의하십니까?
        </S.DepositModal>
      )}
    </S.Main>
  );
}
