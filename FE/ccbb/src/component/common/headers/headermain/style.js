import styled from "styled-components";

export const Header = styled.header`
  display: flex;
  background-color: #110C1F;
  color: white;
  justify-content: space-between;
  margin-top: 20px;
  padding-left: 30px;
  padding-right: 20px;
  height: 60px;
  width: 90%;
  border-radius: 10px;
  p {
    font-size: 20px;
    padding: 10px;
  }
  
  a {
    text-decoration: none; /* 밑줄 제거 */
    color: inherit; /* 부모 요소로부터 텍스트 색상 상속 (기본 텍스트 색상 사용) */
    cursor: pointer; /* 커서 스타일을 포인터로 설정하여 클릭 가능한 것처럼 보이게 함 */
  }
  > div {
    margin: 10px; /* 각 섹션의 마진 설정 */
  }
`
export const leftmenu = styled.div`
  display: flex;
  align-items: center;
`

export const rightmenu = styled.div`
  display: flex;
  align-items: center;
`
export const gamemenu = styled.div`
  display: flex;
  align-items: center;
`

export const webmenu = styled.div`
  display: flex;
  align-items: center;
`


