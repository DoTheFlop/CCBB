import styled from "styled-components";

export const card = styled.div`
  width: 400px;
  display: flex;
  flex-direction: column;
  color: white;
`;

export const gameLinks = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-around;
`;

export const gameLinkItem = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 80px;
  & > span {
    margin-bottom: 10px;
    &:hover {
      cursor: pointer;
      text-decoration: underline;
    }
  }
`;

export const Overlay = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.5);
  z-index: 99;
`;

export const ModalWrap = styled.div``;

export const LoLNicknameModal = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  svg {
    color: white;
    align-self: end;
    margin-right: -30px;
    cursor: pointer;
  }
  width: 400;
  height: fit-content;
  border-radius: 10px;
  background-color: #110c1f;
  padding: 10px 50px 10px 50px;
  box-sizing: border-box;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 999;
  span {
    color: red;
    margin-bottom: 10px;
  }
`;

export const LoLTierImg = styled.img`
  width: 60px;
`;
