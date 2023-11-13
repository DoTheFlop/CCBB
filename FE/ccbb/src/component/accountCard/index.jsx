import * as S from "./style";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import CloseIcon from "@mui/icons-material/Close";
import AccountLinkBtn from "../accountLinkBtn";
import Input1 from "../common/inputs/input1";
import Button1 from "../common/buttons";
import { ccbbApi } from "../../api/ccbbApi";
import ModalContainer from "../Modal/modalContainer";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

export default function AccountCard({ loltier }) {
  const navigate = useNavigate();
  const [isOpen, setIsOpen] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const [lolNick, setLolNick] = useState("");
  const [err, SetErr] = useState(false);
  const games = ["LoL", "Valorant", "Runeterra", "Overwatch"];
  const token = localStorage.getItem("token");
  const headers = {
    Authorization: `Bearer ${token}`,
  };
  let toastId = null;
  const handleAccountLink = (title) => {
    SetErr(false);
    if (title === "LoL") {
      setIsOpen(true);
    } else {
      // 이미 토스트 메시지가 활성 상태라면 함수를 종료
      if (toast.isActive(toastId)) return;
      toastId = toast(title + " 연동은 준비중입니다!");
    }
  };
  const handleOnKeyPress = (e) => {
    if (e.key === "Enter") {
      if (!lolNick) return;
      handleLoLNicknamePost();
    }
  };

  const handleLoLNicknamePost = () => {
    setIsLoading(true);
    ccbbApi
      .post(`/user/lol/tier?lolName=${lolNick}`, {}, { headers })
      .then((res) => {
        setIsOpen(false);
        // navigate("/mypage");
        window.location.reload();
        setIsLoading(false);
      })
      .catch((e) => {
        console.log(e);
        setIsLoading(false);
        SetErr(true);
      });
  };

  return (
    <S.card>
      <h3>계정 연동</h3>
      <S.gameLinks>
        {games.map((game, index) => {
          return (
            <S.gameLinkItem key={index}>
              <span onClick={() => handleAccountLink(game)}>{game}</span>
              {game === "LoL" && loltier ? (
                <S.LoLTierImg src={`/resource/${loltier}.png`} alt="lol-tier" />
              ) : (
                <AccountLinkBtn
                  title={game}
                  onClick={() => handleAccountLink(game)}
                />
              )}
            </S.gameLinkItem>
          );
        })}
      </S.gameLinks>
      <ToastContainer
        position="top-right"
        limit={1}
        closeButton={false}
        autoClose={2200}
        closeOnClick
        hideProgressBar
      />
      {isOpen && (
        <S.Overlay>
          <S.ModalWrap>
            <ModalContainer>
              <S.LoLNicknameModal>
                <CloseIcon
                  onClick={() => {
                    setIsOpen(false);
                    SetErr(false);
                  }}
                />
                <Input1
                  label={"리그오브레전드 닉네임"}
                  value={lolNick}
                  onKeyPress={handleOnKeyPress}
                  onChange={(e) => {
                    SetErr(false);
                    setLolNick(e.target.value);
                  }}
                ></Input1>
                {lolNick && err && (
                  <span>티어 정보를 가져오지 못했습니다.</span>
                )}
                <Button1
                  text={isLoading ? "갱신중..." : "등록"}
                  height={"30px"}
                  width={"100px"}
                  onClick={handleLoLNicknamePost}
                />
              </S.LoLNicknameModal>
            </ModalContainer>
          </S.ModalWrap>
        </S.Overlay>
      )}
    </S.card>
  );
}
