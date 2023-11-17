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

export const CountAndBtn = styled.div`
  border-bottom: solid 1px;
  padding-bottom: 2%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  .total-count {
    font-size: 1.5rem;
  }
`;
