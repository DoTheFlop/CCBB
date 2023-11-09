import styled from "styled-components";

export const main = styled.main`
  display: flex;
  flex: 1;
  flex-direction: column;
  align-items: center;
  width: 100%;
  header {
    width: 80%;
    margin: 20px 0 20px 0;
  }
`;

export const prizeSection = styled.div`
  display: flex;
  flex: 1;
  width: 100%;
  padding: 4% 10% 3% 10%;
  box-sizing: border-box;
  background-color: white;
  flex-wrap: wrap;
`;



export const userPointSection = styled.div`
  position: absolute;  // 화면의 위치를 고정시킵니다.
  top: 12.2%;  // 상단에서부터의 위치를 설정합니다. "경품응모"와 같은 높이로 설정하려면 이 값을 조절하면 됩니다.
  right: 35px;  // 오른쪽에 붙입니다.
  width: 400px;  // 너비를 설정합니다.
  padding: 20px;  // 패딩을 설정합니다.
  background-color: transparent;  // 배경색을 투명하게 설정합니다.
  text-align: center;  // 텍스트를 중앙 정렬합니다.
  h3 {
    color: gold;  // 텍스트 색상을 형광색으로 설정합니다.
    font-size: 20px;  // 텍스트 크기를 설정합니다.
  }
`;

