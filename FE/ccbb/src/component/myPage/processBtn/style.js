import styled from "styled-components";

export const roundBtn = styled.button`
  background-color: #0b123f;
  color: white;
  border: solid 8px;
  border-radius: 50%;
  border-color: ${(props) => (props.$isOngoing ? "lightblue" : "transparent")};
  width: 10vw;
  height: 10vw;
  max-width: 100px;
  max-height: 100px;
  svg {
    width: 70%;
    height: 70%;
  }
`;
