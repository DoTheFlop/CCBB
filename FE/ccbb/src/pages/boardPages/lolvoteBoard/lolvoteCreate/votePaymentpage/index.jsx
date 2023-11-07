import React, { useState } from "react";
import * as S from "./style";
import { Button1 } from "../../../../../component/common/buttons/style";

export default function VotePaymentModal({ isOpen, onClose, payment }) {
  const handlePayment = () => {
    if (payment) {
      // Redirect the user to the payment URL
      console.log(payment)
      window.location.href = payment;
    }
    onClose();
  };

  return (
    <>
      <S.ModalContainer>
        <S.ModalBtn onClick={onClose}>
          {isOpen ? "Opened!" : "Open Modal"}
        </S.ModalBtn>
        {isOpen ? (
          <S.ModalBackdrop onClick={onClose}>
            <S.ModalView onClick={(e) => e.stopPropagation()}>
              <h1>결제하시겠습니까?</h1>
              <S.buttonWrapper>
                <Button1 className="desc" onClick={handlePayment}>
                  결제하기
                </Button1>
                <Button1 onClick={onClose}>닫기</Button1>
              </S.buttonWrapper>
            </S.ModalView>
          </S.ModalBackdrop>
        ) : null}
      </S.ModalContainer>
    </>
  );
}
