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


