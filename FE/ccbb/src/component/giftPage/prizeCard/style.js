import styled from "styled-components";

export const Img = styled.img`
  width: 200px;
  height: 200px;
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
