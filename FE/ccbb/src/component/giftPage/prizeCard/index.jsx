import React, { useState } from "react";
import * as S from "./style";
import Button1 from "../../common/buttons";
import { ccbbApi } from "../../../api/ccbbApi";
import { useParams } from "react-router-dom";

export default function PrizeCard({
  stock = 1,
  title,
  point,
  fileId,
  goodsId,
  updateGoods,
  getUserPoint,
  setUserPoint,
  userPoint,
}) {
  const token1 = localStorage.getItem("token");
  const [modalOpen, setModalOpen] = useState(false);
  const [modalMessage, setModalMessage] = useState("");

  const handleEntry = () => {
    const headers = {
      Authorization: `Bearer ${token1}`,
    };

    ccbbApi
      .post(`/event/goods/entry?goodsId=${goodsId}`, {}, { headers })
      .then((res) => {
        let message = res.data.message;

        if (message === "응모에 성공했습니다.") {
          message =
            "당첨 되셨습니다. 이주내로 정보에 적은 주소로 전송 할 예정입니다.";
          updateGoods();
        } else if (message === "응모에 실패했습니다.") {
          message = "당첨에 실패했습니다.";
        }
        setModalMessage(message);
        setModalOpen(true);
        getUserPoint();
        setUserPoint(userPoint - point);
      })
      .catch((error) => {
        if (error.response && error.response.data) {
          let errorMessage = error.response.data.message;
          if (errorMessage === "fail: 포인트가 부족합니다.") {
            errorMessage = "포인트가 부족합니다.";
          }
          setModalMessage(errorMessage);
          setModalOpen(true);
        } else {
          console.log(error);
        }
      });
  };

  const closeModal = () => {
    setModalOpen(false);
  };

  return (
    <>
      <S.card>
        {stock > 0 && <S.stockBadge>{stock}개</S.stockBadge>}
        <S.Img
          src={`https://ccbb.pro/api/file/get/${fileId}`}
          alt="profile-img"
        />
        <h3>{title}</h3>
        <p>{point}P</p>
        {token1 ? (
          <Button1
            width="90px"
            height="30px"
            text={stock ? "응모하기" : "Sold Out"}
            color={stock ? null : "#c30f0f"}
            onClick={handleEntry}
          />
        ) : null}
      </S.card>

      {modalOpen && (
        <S.modal>
          <S.modalContent>
            <p>{modalMessage}</p>
            <Button1
              width="100px"
              height="30px"
              text="닫기"
              onClick={closeModal}
            />
          </S.modalContent>
        </S.modal>
      )}
    </>
  );
}
