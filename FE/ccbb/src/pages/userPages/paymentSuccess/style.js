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
  /* background-color: #ffffff30; */
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

export const textAndBtn = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: start;
  align-items: center;
  svg {
    color: gold;
  }
`;

//탭그룹
export const tabGroup = styled.div`
  width: 90%;
  height: fit-content;
  display: flex;
  flex-direction: row;
  align-items: center;
  /* background-color: lightblue; */
  h3 {
    cursor: pointer;
    color: #a4a4a4;
    margin-right: 10px;
    margin-bottom: 0px;
    padding: 10px 10px 10px 10px;
  }

  h3.active {
    color: #110c1f;
    background-color: white;
    margin-bottom: 0px;
    border-radius: 10px 10px 0 0;
  }
`;

//마이페이지 하단영역(탭아래)
export const bottomSection = styled.div`
  width: 100%;
  flex: 1;
  box-sizing: border-box;
  /* padding: 20px 100px 20px 100px; */
  padding: 2% 10% 3% 10%;
  background-color: white;
`;
