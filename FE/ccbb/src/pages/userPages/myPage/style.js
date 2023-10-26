import styled from "styled-components";

export const Img = styled.img`
  border-radius: 50%;
  width: 180px;
`;
//마이페이지 상단영역
export const profileInfo = styled.div`
  width: 90%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  background-color: #ffffff30;
  margin-top: 20px;
`;

//프사영역
export const imgSection = styled.div`
  width: fit-content;
  height: fit-content;
`;

//닉네임, 포인트영역
export const textSection = styled.div`
  width: auto;
  display: flex;
  flex: 1;
  padding-left: 2%;
  flex-direction: column;
  color: white;
`;
