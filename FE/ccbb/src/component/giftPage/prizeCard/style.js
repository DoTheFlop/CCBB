import styled from "styled-components";

export const Img = styled.img`
  width: 200px;
  height: 200px;
  border-radius: 10px;
`;

export const card = styled.div`
  background-color: #d0d0c0;
  border-radius: 10px;
  width: 240px;
  height: 320px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: relative;
  margin: 0 30px 50px 30px;
  h3 {
    margin-top: 8px;
    margin-bottom: 8px;
  }
  p {
    margin-top: 0px;
    margin-bottom: 8px;
  }
`;

export const stockBadge = styled.div`
  border-radius: 50%;
  background-color: #0b123f;
  color: white;
  width: 60px;
  height: 60px;
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  margin: -300px -220px 0px 0px;
  //텍스트넘침처리용
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
`;



  
export const modal = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.6); // 반투명한 검은색 배경
    z-index: 9999; // 다른 요소들 위에 나타나도록 z-index 설정
  `;
  
export const modalContent = styled.div`
    background-color: white;
    padding: 20px;
    border-radius: 10px; // 모서리 둥글게
    width: 300px; // 너비 설정
    max-width: 80%; // 화면 너비에 따라 최대 너비 설정
    box-shadow: 0px 1px 10px rgba(0, 0, 0, 0.1); // 그림자 효과
    text-align: center; // 텍스트 가운데 정렬
  `;


