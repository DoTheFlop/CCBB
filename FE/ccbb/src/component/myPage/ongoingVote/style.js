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

export const processChart = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
  .arrow {
    color: #0b123f;
    margin-bottom: 5%;
  }
`;

export const processStep = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;
