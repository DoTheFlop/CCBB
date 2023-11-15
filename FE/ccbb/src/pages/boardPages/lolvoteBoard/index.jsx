import * as S from "./style";
import Headermenu from "../../../component/common/headers/headermenu";
import Button1 from "../../../component/common/buttons";
import VoteCard from "../../../component/voteBoard/Cardsection";
import React, { useState, useEffect } from "react";
import Select from "react-select";
import { useNavigate } from "react-router-dom";
import { ccbbApi } from "../../../api/ccbbApi";
import { Pagination } from "@mui/material";
import Loading from "../../../component/common/Loading";
import { ToastContainer, toast } from "react-toastify";

export default function LoLvoteboardPage() {
  const options = [
    { value: "Latest", label: "최신순" },
    { value: "Popular", label: "인기순" },
  ];

  const [activeTab, setActiveTab] = useState("ongoing"); // 'ongoing' 또는 'completed'
  const [selectedOption, setSelectedOption] = useState(options[0]);
  const navigate = useNavigate();
  const [modalOpen, setIsModalOpen] = useState(false);
  const [modalMessage, setModalMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const toggleTab = (tab) => {
    setActiveTab(tab);
  };

  // const openModal = (message) => {
  //   setModalMessage(message);
  //   setModalOpen(true);
  // };

  // const closeModal = () => {
  //   setIsModalOpen(false);
  // };

  const handleOptionChange = (selected) => {
    setSelectedOption(selected);
  };

  const handleCreateButtonClick = () => {
    let token = localStorage.getItem("token");
    if (!token) {
      toast.error("로그인 하세요.");
      return;
    }
    setIsModalOpen(true);
  };

  const handleAgreeClick = () => {
    setIsModalOpen(false);
    navigate("/lolvote/create");
  };

  const handleCancelClick = () => {
    setIsModalOpen(false);
  };

  const customStyles = {
    control: (provided) => ({
      ...provided,
      background: "transparent",
      borderColor: "transparent",
      boxShadow: "none",
      "&:hover": { borderColor: "transparent" },
    }),
    option: (provided, state) => ({
      ...provided,
      backgroundColor: state.isFocused ? "#154C61" : "transparent",
      color: state.isFocused ? "white" : "black",
      border: "none",
    }),
    indicatorSeparator: () => null,
  };

  const customControlStyles = {
    singleValue: (provided) => ({
      ...provided,
      color: "white",
      fontSize: "18px",
      fontWeight: "bold",
    }),
  };

  const [pages, setPages] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [total, setTotal] = useState(null);
  const [voteData, setVoteData] = useState([]);

  const fetchData = async () => {
    try {
      let endpoint = "";
      if (activeTab === "ongoing") {
        endpoint =
          selectedOption.value === "Latest"
            ? `/post/vote/list?page=${currentPage}`
            : `/post/vote/popularList?page=${currentPage}`;
      } else if (activeTab === "completed") {
        endpoint =
          selectedOption.value === "Latest"
            ? `/post/vote/pastList?page=${currentPage}`
            : `/post/vote/popularPastList?page=${currentPage}`;
      }

      const response = await ccbbApi.get(endpoint);

      setPages(response.data.voteList.totalPages);
      setVoteData(response.data.voteList.content);
      setTotal(response.data.voteList.totalElements);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    setIsLoading(true);
    fetchData().then(() => setIsLoading(false));
  }, [currentPage, selectedOption, activeTab]);

  const handlePageChange = (event, newPage) => {
    setCurrentPage(newPage);
  };

  return (
    <S.Main>
      <S.Head>
        <S.Headtop>
          <Headermenu title={"리그 오브 레전드"} />
        </S.Headtop>
        <S.Headbottom>
          <S.Menuhead>
            <h3
              onClick={() => toggleTab("ongoing")}
              className={activeTab === "ongoing" ? "active" : ""}
            >
              진행중인 투표
            </h3>
            <h3
              onClick={() => toggleTab("completed")}
              className={activeTab === "completed" ? "active" : ""}
            >
              종료된 투표
            </h3>
          </S.Menuhead>
          <ToastContainer
            position="top-right"
            limit={1}
            closeButton={false}
            autoClose={2200}
            closeOnClick
            hideProgressBar
          />
          <S.Actions>
            <S.Dropdown>
              <Select
                options={options}
                value={selectedOption}
                onChange={handleOptionChange}
                styles={{ ...customStyles, ...customControlStyles }}
              />
            </S.Dropdown>
            <S.A_1>
              <p style={{ paddingRight: "10px" }}>{total}건</p>
              <Button1
                text={"글 작성"}
                width={"75px"}
                height={"30px"}
                onClick={handleCreateButtonClick}
              />
            </S.A_1>
          </S.Actions>
        </S.Headbottom>
      </S.Head>
      <S.Votebodycover>
        <S.VotebodyC>
          <S.Votebody>
            {isLoading ? (
              <>
                <S.EmptyDiv></S.EmptyDiv>
                <Loading />
              </>
            ) : null}
            {voteData.length > 0 &&
              voteData.map((item, idx) => (
                <VoteCard
                  key={idx}
                  title={item.title}
                  postId={item.postId}
                  voteCount={item.voteCount}
                  deadline={item.deadline}
                  fileId={item.fileId[0].fileId}
                  tier={item.tier.toUpperCase()} //대문자로 통일
                />
              ))}
          </S.Votebody>
        </S.VotebodyC>
        {isLoading ? null : (
          <S.PaginationBox>
            <Pagination
              count={pages}
              page={currentPage}
              onChange={handlePageChange}
            />
          </S.PaginationBox>
        )}
      </S.Votebodycover>
      {modalOpen && (
        <S.DepositModal
          isOpen={modalOpen}
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
