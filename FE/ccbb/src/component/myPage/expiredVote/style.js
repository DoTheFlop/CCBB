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

export const VoteGrid = styled.div`
  display: flex;
  flex-wrap: wrap;
  /* grid-template-columns: 1fr 1fr 1fr 1fr; */
  justify-content: start;
`;
