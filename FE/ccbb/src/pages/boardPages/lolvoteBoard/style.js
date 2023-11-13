import styled from "styled-components";
import React, { useState } from 'react';

export const Main = styled.main`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  color: white;
  width: 100%;
`;
export const Head = styled.header`
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  width: 85%;
`;
export const Headtop = styled.header`
  background-color: #0b123f;
  margin-bottom: 20px;
  width: fit-content;
`;
export const Headbottom = styled.header`
  background-color: #154c61;
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
`;
export const Menuhead = styled.div`
  display: flex;
  flex-direction: row;
  padding-left: 30px;

  h3 {
    cursor: pointer;
    color: #a4a4a4;
    padding-bottom: 5px;
    margin-right: 10px;
  }

  h3.active {
    color: white;
    border-bottom: 2px solid white;
  }
`;
export const Actions = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  width: 100%;
  font-size: 17px;
`;
export const A_1 = styled.div`
  margin-left: auto;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding-right: 30px;
`;

export const Votebodycover = styled.div`
  display: flex;
  align-items: center;
  flex-direction: column;
  background-color: #c6d8d0;
  margin-bottom: 30px;
  padding-bottom: 10px;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  width: 85%;
  padding-top: 10px;
`;

export const VotebodyC = styled.div`
  display: flex;
  width: 100%;
  justify-content: center; /* 수평 중앙 정렬 */
`;

export const Votebody = styled.div`
  display: flex;
  flex-wrap: wrap;
  padding : 0 7.5%;
  min-width: 85%;
`;

export const Dropdown = styled.div`
  padding-left: 30px;
  .css-1xc3v61-indicatorContainer {
    color: white;
  }
  .css-1xc3v61-indicatorContainer:hover {
    color: white;
  }
  .css-15lsz6c-indicatorContainer {
    color: white;
  }
  .css-15lsz6c-indicatorContainer:hover {
    color: white;
  }
`;

export const PaginationBox = styled.div`
  .pagination {
    display: flex;
    justify-content: center;
    margin-top: 15px;
  }
  ul {
    list-style: none;
    padding: 0;
  }
  ul.pagination li {
    display: inline-block;
    width: 30px;
    height: 30px;
    border: 1px solid #e2e2e2;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: 1rem;
    color: white;
  }
  ul.pagination li:first-child {
    border-radius: 5px 0 0 5px;
  }
  ul.pagination li:last-child {
    border-radius: 0 5px 5px 0;
  }
  ul.pagination li a {
    text-decoration: none;
    color: #337ab7;
    font-size: 1rem;
  }
  ul.pagination li.active a {
    color: white;
  }
  ul.pagination li.active {
    background-color: #337ab7;
  }
  ul.pagination li a:hover,
  ul.pagination li a.active {
    color: blue;
  }
`;

const ModalBackground = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ModalContent = styled.div`
  width: 80%;
  max-width: 500px;
  background-color: #f3f3f3;
  padding: 20px;
  border-radius: 10px;
  color: black;
`;

export const DepositModal = ({ isOpen, onClose, onAgree, children }) => {
  const [agree, setAgree] = useState(false); // 체크박스의 상태를 관리하는 state를 추가합니다.

  const handleAgree = () => {
    setAgree(!agree); // 체크박스를 클릭하면 상태를 변경합니다.
  };

  return (
    <ModalBackground onClick={onClose}>
  <ModalContent onClick={(e) => e.stopPropagation()}>
    <h2>보증금 관련 중요 알림</h2>
    <p>
      CC.BB는 게임 내 분쟁의 공정한 해결을 위한 플랫폼입니다. 이를 위해, 투표에서 패배한 당사자는 사전에 동의한 공약을 이행해야 합니다.
    </p>
    <p>
      공약 이행을 위해 미리 보증금을 입금해야 하는데, 이 보증금은 공약 이행을 보장하는 중요한 도구입니다.
    </p>
    <p>
      만약 공약 이행을 하지 않을 경우, 보증금은 반환되지 않습니다. 대신, 이 보증금은 사회적 기부금으로 사용됩니다.
    </p>
    <p>
      이는 게임의 공정성을 유지하고, 공약을 신중하게 설정하도록 돕는 중요한 규칙입니다. 따라서 이 점을 숙지하고, 공약을 설정하실 때 신중하게 결정해주시기 바랍니다.
    </p>
    <input type="checkbox" checked={agree} onChange={handleAgree} />
    <label>위의 보증금 관련 규칙에 동의하십니까?</label>
    <button disabled={!agree} onClick={onAgree}>동의하고 계속하기</button>
    <button onClick={onClose}>취소</button>
  </ModalContent>
</ModalBackground>
  );
};