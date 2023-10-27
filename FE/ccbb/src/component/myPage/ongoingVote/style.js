import styled from "styled-components";

export const main = styled.main`
  display: flex;
  flex-direction: column;
  a {
    width: fit-content;
    text-decoration: none;
    color: black;
  }
  h1 {
    width: fit-content;
  }
`;

export const processChart = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
`;

export const processStep = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;
