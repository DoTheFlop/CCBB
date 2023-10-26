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
  & > span {
    margin-bottom: 10px;
  }
`;
