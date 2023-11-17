import * as S from "./style";
import Headermenu from "../../component/common/headers/headermenu";
import React, { useState } from "react";
import SearchInput from "../../component/common/inputs/searchInput";

export default function DonatePage({ amount = 1238000 }) {
  const [modalOpen, setModalOpen] = useState(false);
  const [modalImgSrc, setModalImgSrc] = useState(null);

  const formattedAmount = amount.toLocaleString();

  const Modal = ({ closeModal, imgSrc }) => (
    <S.ModalWrapper onClick={closeModal}>
      <S.ModalContent onClick={(e) => e.stopPropagation()}>
        <img src={imgSrc} alt="modal content" />
      </S.ModalContent>
    </S.ModalWrapper>
  );

  const handleClickSearch = () => {
    console.log("clicked search");
  };

  const openModal = (imgSrc) => {
    setModalImgSrc(imgSrc);
    setModalOpen(true);
  };

  const closeModal = () => {
    setModalOpen(false);
    setModalImgSrc(null);
  };

  const dummy = [
    {
      title: "2023년 11월 기부현황",
      donors: "d59 외 3명",
      donation: "120,000",
      date: "2023.11.03",
      imgSrc: "/resource/DonateSample4.png",
    },
    {
      title: "2023년 10월 기부현황",
      donors: "김롤붕 외 6명",
      donation: "210,000",
      date: "2023.10.01",
      imgSrc: "/resource/DonateSample3.png",
    },
    {
      title: "2023년 09월 기부현황",
      donors: "천밑배종기성 외 13명",
      donation: "408,000",
      date: "2023.09.02",
      imgSrc: "/resource/DonateSample2.png",
    },
    {
      title: "2023년 08월 기부현황",
      donors: "그래안해 외 8명",
      donation: "500,000",
      date: "2023.08.01",
      imgSrc: "/resource/DonateSample1.png",
    },
  ];

  return (
    <S.main>
      <Headermenu title="기부내역"></Headermenu>
      <S.donateSection>
        <h2>누적 기부액</h2>
        <S.accumulatedDonation>
          <h1>{formattedAmount} 원</h1>
        </S.accumulatedDonation>
        <SearchInput width={"300px"} onClickIcon={handleClickSearch} />
        <hr />
        {dummy.map((item, index) => {
          return (
            <S.donatePreview key={index} onClick={() => openModal(item.imgSrc)}>
              <p>{item.title}</p>
              <p>{item.donors}</p>
              <p>{item.donation}원</p>
              <p>{item.date}</p>
            </S.donatePreview>
          );
        })}
        {modalOpen && <Modal closeModal={closeModal} imgSrc={modalImgSrc} />}
      </S.donateSection>
    </S.main>
  );
}
