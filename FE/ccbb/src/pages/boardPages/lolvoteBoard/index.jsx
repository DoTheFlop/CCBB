import * as S from "./style";
import Headermenu from "../../../component/common/headers/headermenu";
import Button1 from "../../../component/common/buttons";
import VoteCard from "../../../component/voteBoard/Cardsection";
import { useEffect, useState } from "react";
import Select from "react-select";
import { useNavigate } from "react-router-dom";
import { ccbbApi } from "../../../api/ccbbApi";
import { Pagination } from "@mui/material";

export default function LoLvoteboardPage() {
  const options = [
    { value: "Latest", label: "최신순" },
    { value: "Popular", label: "인기순" },
  ];

  const [activeTab, setActiveTab] = useState("ongoing"); // 'ongoing' 또는 'completed'
  const [selectedOption, setSelectedOption] = useState(options[0]);
  const navigate = useNavigate();
  const toggleTab = (tab) => {
    setActiveTab(tab);
  };

  const handleOptionChange = (selected) => {
    setSelectedOption(selected);
  };

  const handleCreateButtonClick = () => {
    navigate("/lolvote/create");
  };

  const customStyles = {
    control: (provided) => ({
      ...provided,
      background: "transparent", // 배경을 투명색으로 설정
      borderColor: "transparent", // 테두리를 투명으로 설정 (선택 시 테두리 제거)
      boxShadow: "none", // 테두리 그림자 제거
      "&:hover": { borderColor: "transparent" },
    }),
    option: (provided, state) => ({
      ...provided,
      backgroundColor: state.isFocused ? "#154C61" : "transparent", // 마우스 호버 상태일 때 배경 색상 변경
      color: state.isFocused ? "white" : "black", // 마우스 호버 상태일 때 글자색 변경
      border: "none", // 구분선 제거
    }),
    indicatorSeparator: () => null, // 구분선 숨기기
  };

  const customControlStyles = {
    singleValue: (provided) => ({
      ...provided,
      color: "white", // 텍스트 색상을 흰색으로 설정
      fontSize: "18px", // 텍스트 크기를 18px로 설정
      fontWeight: "bold", // 글자를 굵게 표시
    }),
  };

  const [pages, setPages] = useState(0);
  const [currentPage, setCurrentPage] = useState(1);
  const [total, setTotal] =useState(null)
  const [voteData, setVoteData] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await ccbbApi.get(
          `/post/vote/list?page=${currentPage}`
        );
        setPages(response.data.voteList.totalPages);
        setVoteData(response.data.voteList.content);
        console.log(response.data.voteList.totalElements);
        setTotal(response.data.voteList.totalElements)

      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchData();
  }, [currentPage,selectedOption]);

  const handlePageChange = (event, newPage) => {
    console.log(newPage);
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
        <S.Votebody>
          {voteData.length > 0 &&
            voteData.map((item, idx) => (
              <VoteCard key={idx} title={item.title} postId={item.postId} voteCount = {item.voteCount} deadline={item.deadline} fileId={item.fileId[0].fileId} tier={item.tier}/>
            ))}
        </S.Votebody>
        <S.PaginationBox>
          <Pagination
            count={pages}
            page={currentPage}
            onChange={handlePageChange}
          />
        </S.PaginationBox>
      </S.Votebodycover>
    </S.Main>
  );
}
