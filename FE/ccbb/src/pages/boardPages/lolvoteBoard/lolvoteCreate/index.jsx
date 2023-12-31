import Button1 from "../../../../component/common/buttons";
import Headermenu from "../../../../component/common/headers/headermenu";
import Input2 from "../../../../component/common/inputs/input2";
import InputComment from "../../../../component/common/inputs/inputcomment";
import * as S from "./style";
import { useEffect, useState } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import { MenuItem, Select } from "@mui/material";
import RemoveCircleIcon from "@mui/icons-material/RemoveCircle";
import PersonSearchIcon from "@mui/icons-material/PersonSearch";
import { ccbbApi } from "../../../../api/ccbbApi";
import { userState } from "../../../../recoil/UserAtom";
import { useRecoilValue } from "recoil";
import VotePaymentModal from "./votePaymentpage";
import axios from "axios";
import FileUploadIcon from "@mui/icons-material/FileUpload";
import Loading from "../../../../component/common/Loading";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import MyTooltip from "../../../../component/common/inputs/tooltip";
import moment from "moment";

export default function LoLvoteCreatePage() {
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

  const [selectedDate, setSelectedDate] = useState(null);
  const [isDatePickerOpen, setDatePickerOpen] = useState(false);
  const [selectedTier, setSelectedTier] = useState(options[0].value);
  const [userSearch, setUserSearch] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [minVotingTier, setMinVotingTier] = useState(options[0].value);

  const handleDateChange = (date) => {
    date.setHours(23);
    date.setMinutes(59);
    date.setSeconds(59);

    setSelectedDate(date);
    setDatePickerOpen(false);
    setVoteArticle((prev) => ({
      ...prev,
      deadline: moment(date).format("YYYY-MM-DDTHH:mm:ss"),
    }));

    console.log(voteArticle.deadline);
  };

  const handleTierChange = (selected) => {
    const selectedValue = selected.value;
    setSelectedTier(selectedValue);
    setMinVotingTier(selectedValue);
    setVoteArticle((prev) => ({
      ...prev,
      tier: selectedValue,
    }));
  };

  const createPreview = (file) => {
    if (file.type.startsWith("image/")) {
      return URL.createObjectURL(file);
    } else if (file.type.startsWith("video/")) {
      return URL.createObjectURL(file);
    }
    return null;
  };

  const [uploadedFiles, setUploadedFiles] = useState([]);
  const [uploadedVideo, setUploadedVideo] = useState(null);
  const [uploadedReplay, setUploadedReplay] = useState(null);

  const createFileEntry = (file) => ({
    file,
    preview: createPreview(file),
  });

  useEffect(() => {
    const updatedFiles = [];

    if (uploadedVideo) {
      updatedFiles.push(createFileEntry(uploadedVideo.file));
    }

    if (uploadedReplay) {
      updatedFiles.push(createFileEntry(uploadedReplay.file));
    }

    setUploadedFiles(updatedFiles);
  }, [uploadedReplay, uploadedVideo]);

  const handleVideoUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      if (file.size >= 100 * 1024 * 1024) {
        toast.error(
          "동영상 파일 크기가 너무 큽니다. 100MB 이하의 파일을 업로드해주세요."
        );
        return;
      }

      if (file.type.startsWith("video/")) {
        setUploadedVideo(createFileEntry(file));
        setUploadedFiles([...uploadedFiles, createFileEntry(file)]);
      }
    }
  };

  const handleReplayUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      setUploadedReplay(createFileEntry(file));
      setUploadedFiles([...uploadedFiles, createFileEntry(file)]);
    }
  };

  const handleDeleteVideo = () => {
    setUploadedVideo(null);
    const updatedFiles = uploadedFiles.filter(
      (fileEntry) => fileEntry.file !== uploadedVideo.file
    );
    setUploadedFiles(updatedFiles);
  };

  const handleDeleteReplay = () => {
    setUploadedReplay(null);
    const updatedFiles = uploadedFiles.filter(
      (fileEntry) => fileEntry.file !== uploadedReplay.file
    );
    setUploadedFiles(updatedFiles);
  };
  const [isOpen, setIsOpen] = useState(false);

  const openModalHandler = () => {
    setIsOpen(!isOpen);
  };
  const token1 = localStorage.getItem("token");

  const [article, setAritcle] = useState({
    title: "",
    content: "",
    type: 1,
    user_id: token1,
    create_date: new Date().toISOString(),
  });

  const [voteArticle, setVoteArticle] = useState({
    accept1: false,
    accept2: false,
    argument: "",
    deadline: "",
    deposit: 0,
    postId: 1,
    doPromise: false,
    promise: "",
    tier: options[0].value,
    selectLine: 0,
    userId1: 1,
    userId2: "",
    voteStart: new Date().toISOString(),
  });

  const roleValues = {
    Top: 10000,
    Jungle: 1000,
    Mid: 100,
    ADCarry: 10,
    Support: 1,
  };
  const [payment, setPayment] = useState(null);

  const handleRoleCheckboxChange = (role) => {
    const isRoleSelected = voteArticle[role]; // Check if the role is selected
    const roleValue = isRoleSelected ? 0 : roleValues[role]; // If selected, set the role value, otherwise set to 0

    setVoteArticle((prev) => {
      const newSelectLine =
        prev.selectLine - (isRoleSelected ? roleValues[role] : 0) + roleValue;

      return {
        ...prev,
        [role]: !isRoleSelected,
        selectLine: newSelectLine,
      };
    });
  };

  const userSelf = useRecoilValue(userState);

  const handleSearchUser = async () => {
    if (voteArticle.userId2 === "") {
      toast.error("이메일을 입력하세요.");
      return;
    }
    try {
      setIsLoading(true);
      if (userSelf.email !== voteArticle.userId2) {
        const response = await ccbbApi
          .get(`/user/find-user?email=${voteArticle.userId2}`)
          .then(setIsLoading(false));
        if (response) {
          setUserSearch(true);
          toast.success("확인되었습니다.");
        }
      } else {
        toast.error("다른사람의 이메일을 입력해주세요");
        return;
      }
    } catch (error) {
      if (error.response.data === false) {
        toast.error("이메일을 확인해주세요.");
      }
      setIsLoading(false);
    }
  };

  const handleSendButton = async () => {
    if (!article.title) {
      toast.error("제목을 작성하세요.");
      return;
    } else if (!voteArticle.argument) {
      toast.error("논점을 작성하세요.");
      return;
    } else if (!article.content) {
      toast.error("내용을 작성하세요.");
      return;
    } else if (!uploadedVideo) {
      toast.error("동영상파일을 첨부하세요.");
      return;
    } else if (!voteArticle.promise) {
      toast.error("공약을 작성하세요.");
      return;
    } else if (!voteArticle.userId2) {
      toast.error("받는사람을 작성하세요.");
      return;
    } else if (voteArticle.userId2 && !userSearch) {
      toast.error("받는사람을 확인해주세요.");
      return;
    } else if (!voteArticle.deadline) {
      toast.error("투표마감일을 선택하세요.");
      return;
    } else if (!voteArticle.deposit) {
      toast.error("보증금을 작성하세요.");
      return;
    } else if (isNaN(voteArticle.deposit)) {
      toast.error("보증금을 숫자로 작성하세요.");
      return;
    }

    let deadline = new Date(voteArticle.deadline);
    let now = new Date();
    now.setHours(0, 0, 0, 0);
    if (deadline <= now) {
      toast.error("마감일이 잘못되었습니다.");
    }

    setIsLoading(true);
    try {
      const jsonData = JSON.stringify(article); // article 객체를 문자열로 변환
      const jsonBlob = new Blob([jsonData], { type: "application/json" }); // 문자열을 Blob으로 변환

      const formData = new FormData();
      formData.append("files", uploadedFiles[0].file);
      if (uploadedFiles.length > 1)
        formData.append("files", uploadedFiles[1].file);
      formData.append("post", jsonBlob);
      const headers = {
        Authorization: `Bearer ${token1}`,
        "content-type": "multipart/form-data",
      };

      const response = await axios
        .post("https://ccbb.pro/api/post/add", formData, {
          headers,
          //cors에러 뜨면 넣어야함
          withCredentials: true,
        })

        .then(async (e) => {
          console.log(e.data.postId);
          voteArticle.postId = e.data.postId;

          const voteArticleWithMinTier = {
            ...voteArticle,
            minVotingTier: minVotingTier,
          };

          const body = JSON.stringify(voteArticle);
          const headers = {
            Authorization: `Bearer ${token1}`,
            withCredentials: true,
          };
          console.log(body);
          const responsevote = await ccbbApi
            .post("/vote/add", body, {
              headers,
              //   withCredentials: true,
            })
            .then(async (responsevote) => {
              if (responsevote.data.message === "success") {
                console.log(voteArticle.deposit);
                console.log(voteArticle.postId);
                const payresponse = await ccbbApi
                  .post(
                    `/payment/add?postId=${voteArticle.postId}&price=${voteArticle.deposit}`,
                    {},
                    { headers }
                  )
                  .then(async (payresponse) => {
                    setIsLoading(false);
                    if (payresponse.data) {
                      setPayment(payresponse.data);
                      console.log(payment);
                      console.log(payresponse.data);
                      openModalHandler();
                    }
                    console.log(payresponse.data);
                  });
              }
            });
        });
    } catch (error) {
      toast.error("네트워크 오류입니다.");
      setIsLoading(false);
    }
  };

  return (
    <S.Main>
      <S.Head>
        <S.Headtop>
          <Headermenu title={"리그 오브 레전드"} />
        </S.Headtop>
      </S.Head>
      <S.CreateBodyCover>
        <VotePaymentModal
          isOpen={isOpen}
          onClose={openModalHandler}
          payment={payment}
        />
        <S.CreateBody>
          <Input2
            label="제목"
            id="title"
            width="100%"
            height="40px"
            onChange={(e) => {
              setAritcle((prev) => ({
                ...prev,
                title: e.target.value,
              }));
            }}
            maxLength={40}
          />
          <InputComment
            value={voteArticle.argument}
            label="논점"
            id="argument"
            width="100%"
            height="80px"
            onChange={(e) => {
              setVoteArticle((prev) => ({
                ...prev,
                argument: e.target.value,
              }));
            }}
            tooltip={true}
            tooltipDetail="논점을 명확하게 작성해주세요."
          />
          <InputComment
            value={article.content}
            label="내용"
            id="content"
            width="100%"
            height="200px"
            onChange={(e) => {
              setAritcle((prev) => ({
                ...prev,
                content: e.target.value,
              }));
            }}
            tooltip={true}
            tooltipDetail={`✔ 반드시 서로 합의 하에 최대한 상세히 상황을 작성해주세요.
            
            ✔ 본인 티어를 감안하여 판결되기를 원하는지 여부를 작성해주시면 좋아요.

            ✔ 글 작성자가 왼쪽 투표대상자, 상대방이 오른쪽 투표대상자로 표시됩니다. 누구에게 투표해야할 지 명확하게 언급해주세요.`}
          />
          <S.InputTitleWrapper>
            <h3 style={{ marginRight: 5 }}>동영상 파일</h3>
            <MyTooltip
              tooltip={true}
              tooltipDetail={`✔ 당시 상황을 녹화해서 올려주세요. 2분 내외가 적당해요.
            
            ✔ 용량 제한은 최대 100MB 입니다. 영상 업로드에 시간이 많이 소모되니 유의하세요.
            `}
            ></MyTooltip>
          </S.InputTitleWrapper>
          <S.Uploadfile>
            {uploadedVideo && (
              <div
                key={uploadedVideo.file.name}
                style={{
                  listStyleType: "none",
                  padding: "0 30px 0 30px",
                  position: "relative",
                  border: "1px solid #ccc",
                }}
              >
                {uploadedVideo.file.type.startsWith("video/") ? (
                  <div>
                    <video width="102.3" height="100">
                      <source
                        src={uploadedVideo.preview}
                        type={uploadedVideo.file.type}
                      />
                    </video>
                  </div>
                ) : (
                  <p>Unsupported File Type</p>
                )}
                <p
                  style={{
                    overflow: "hidden",
                    textOverflow: "ellipsis",
                    whiteSpace: "nowrap",
                    maxWidth: "100px",
                  }}
                >
                  {uploadedVideo.file.name}
                </p>
                <button
                  style={{
                    marginLeft: "110px",
                    marginTop: "-150px",
                  }}
                  className="delete-button"
                  onClick={handleDeleteVideo}
                >
                  <RemoveCircleIcon />
                </button>
              </div>
            )}
            <input
              type="file"
              accept="video/*"
              id="input-file"
              multiple
              onChange={handleVideoUpload}
              style={{ display: "none", width: "100px" }}
            />
          </S.Uploadfile>
          <label className="input-file-button" htmlFor="input-file">
            <FileUploadIcon />
          </label>
          <S.InputTitleWrapper>
            <h3 style={{ marginRight: 5 }}>리플레이</h3>
            <MyTooltip
              tooltip={true}
              tooltipDetail={`✔ op.gg 사이트에서 다운로드 받을 수 있는 bat 파일을 업로드 해주세요.
              
              ✔ 사전에 인게임 정보에서 녹화버튼을 눌러야 합니다.

              ✔ 만약 파일이 없다면, 내용 부분에 당신의 닉네임과 어떤 게임인지 명시해주세요.
            `}
            ></MyTooltip>
          </S.InputTitleWrapper>
          <S.Replayfile>
            <input
              type="file"
              id="replay-file"
              accept=".bat"
              onChange={handleReplayUpload}
              style={{ display: "none" }}
            />

            {uploadedReplay && uploadedReplay.file && (
              <div>
                업로드된 파일: {uploadedReplay.file.name}
                <button onClick={handleDeleteReplay}>삭제</button>
              </div>
            )}
          </S.Replayfile>
          <label className="input-file-button" htmlFor="replay-file">
            <FileUploadIcon />
          </label>
          <Input2
            label="공약"
            id="promise"
            width="100%"
            height="40px"
            tooltip={true}
            tooltipDetail={`적절한 공약을 걸어주세요. 투표가 종료된 후 공약을 수행한다면 보증금은 반환되지만, 이행하지 않으면 승자의 이름으로 기부됩니다.`}
            onChange={(e) => {
              setVoteArticle((prev) => ({
                ...prev,
                promise: e.target.value,
              }));
            }}
            maxLength={80}
          />
          <S.CreateBodybottom>
            <S.LeftBottom>
              <S.UserWrapper>
                <Input2
                  label="받는사람"
                  id="recieveuser"
                  width="80%"
                  height="40px"
                  onChange={(e) => {
                    setVoteArticle((prev) => ({
                      ...prev,
                      userId2: e.target.value,
                    }));
                  }}
                />
                <PersonSearchIcon
                  onClick={(e) => {
                    handleSearchUser();
                  }}
                  style={{
                    position: "absolute",
                    top: "55%",
                    left: "73%",
                    cursor: "pointer",
                  }}
                />
              </S.UserWrapper>
              <label style={{ marginTop: "10px" }}>투표가능 최소티어</label>
              <Select
                value={selectedTier}
                onChange={(event) => {
                  handleTierChange(
                    options.find(
                      (option) => option.value === event.target.value
                    )
                  );
                }}
                style={{
                  width: "80%",
                  height: "40px",
                  marginTop: "10px",
                  marginBottom: "10px",
                  border: "1px solid black",
                }}
              >
                {options.map((option) => (
                  <MenuItem
                    key={option.value}
                    value={option.value}
                    style={{ height: "38px" }}
                  >
                    {option.label}
                  </MenuItem>
                ))}
              </Select>
              <div>
                <label>Roles :</label>
                <input
                  type="checkbox"
                  value={voteArticle.Top}
                  onChange={() => handleRoleCheckboxChange("Top")}
                />
                <label>Top</label>
                <input
                  type="checkbox"
                  value={voteArticle.Jungle}
                  onChange={() => handleRoleCheckboxChange("Jungle")}
                />
                <label>Jungle</label>
                <input
                  type="checkbox"
                  value={voteArticle.Mid}
                  onChange={() => handleRoleCheckboxChange("Mid")}
                />
                <label>Mid</label>
                <input
                  id="AD"
                  type="checkbox"
                  value={voteArticle.ADCarry}
                  onChange={() => handleRoleCheckboxChange("ADCarry")}
                />
                <label id="AD">AD Carry</label>
                <input
                  type="checkbox"
                  value={voteArticle.Support}
                  onChange={() => handleRoleCheckboxChange("Support")}
                />
                <label>Support</label>
              </div>
            </S.LeftBottom>
            <S.RightBottom>
              <S.VoteRange>
                <label style={{ padding: "10px 0" }}>투표마감일</label>
                <DatePicker
                  selected={selectedDate}
                  onChange={handleDateChange}
                  showTimeSelect={false}
                  dateFormat="yyyy-MM-dd"
                  placeholderText="투표 마감일을 선택하세요"
                  id="voterange"
                  open={isDatePickerOpen} // DatePicker 열림 여부를 상태로 관리
                  onFocus={() => setDatePickerOpen(true)}
                  onSelect={() => setDatePickerOpen(false)}
                />
                <CalendarMonthIcon
                  htmlFor="voterange"
                  onClick={() => setDatePickerOpen(true)}
                  style={{
                    position: "absolute",
                    top: "60%",
                    left: "120%",
                    cursor: "pointer",
                  }}
                />
              </S.VoteRange>
              <Input2
                label="보증금"
                id="amount"
                width="100%"
                height="40px"
                placeholder="10,000"
                onChange={(e) => {
                  setVoteArticle((prev) => ({
                    ...prev,
                    deposit: e.target.value.replace(/,/g, ""),
                  }));
                }}
              />
            </S.RightBottom>
          </S.CreateBodybottom>
          <S.buttonBox>
            <Button1
              width={"70px"}
              height={"30px"}
              text={"보내기"}
              size={"15px"}
              onClick={handleSendButton}
            />
          </S.buttonBox>
          {isLoading ? <Loading /> : null}
          <ToastContainer
            position="top-right"
            limit={1}
            closeButton={false}
            autoClose={2200}
            closeOnClick
            hideProgressBar
          />
        </S.CreateBody>
      </S.CreateBodyCover>
    </S.Main>
  );
}
