import React, { useState } from 'react';
import styled from "styled-components";

export const Main = styled.main`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
`;

export const Head = styled.header`
  margin-top: 20px;
  display: flex;
  flex-direction: column;
  width: 85%;
`;

export const Headtop = styled.header`
  display: flex;
  align-items: center;
`;

export const Menuhead = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-left: 10%;
  padding-right: 10%;
  width: 80%;
  border-bottom: 1px solid #ccc;
`;

export const Votebodycover = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  background-color: white;
  padding-bottom: 5vh;
`;

export const Headbottom = styled.div`
  padding-left: 4%;
  h3 {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }
`;
export const HeadLeft = styled.div``;

export const HeadRight = styled.div``;

export const DetailBody = styled.div`
  display: flex;
  justify-content: center;
  margin-top: 80px;
  flex-direction: column;
  white-space: pre-line;
  width: 85%;
`;

export const Moviebody = styled.div`
  position: relative;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export const Votebody = styled.div`
  display: flex;
  flex-direction: column;
  margin: 2% 10%;
  font-size: large;
  padding-bottom: 20px;
  white-space: pre-line;
`;

export const VoteBodybot = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 20px;
  p {
    font-weight: bold;
  }
`;

export const Votebutton = styled.div`
  color: black;
  display: flex;
  margin-top: 10px;
`;

export const ImgVS = styled.img`
  margin: 0 50px;
`;
export const Imgward = styled.img``;


export const ProfileBox = styled.div`
  display: flex;
  background-color: ${(props) => props.$bgcolor};
  border-radius: 8px;
  padding: 5px 10px;
  cursor: pointer;
`;

export const ArticleMenu = styled.div`
  margin-top: 15%;
`;

export const PromiseP = styled.div`
  display: flex;
  align-items: center;

  & > h3 {
    cursor: pointer;
  }

  & > svg {
    cursor: pointer;
  }
`;

export const PromisePageWrapper = styled.div`
  display: ${(props) => (props.$opened ? "block" : "none")};
  flex-direction: column;
`;

export const BodyBottom = styled.div`
  border-top: 1px solid #ccc;
  margin: 0 10%;
  h4 {
    margin-top: 0;
  }
`;

export const Createcomment = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  align-items: center;
  textarea {
    flex: 1;
  }
`;

export const CommentBody = styled.div``;

export const VoteBodyButtonBox = styled.div`
  display: flex;
  justify-content: space-between;
  width: 320px;
`;

export const voteUser1Box = styled.div`
`

export const voteUser2Box = styled.div`
`
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
    <h2>보증금 관련 
       알림</h2>
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