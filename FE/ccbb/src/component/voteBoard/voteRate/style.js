import styled from "styled-components";

export const main = styled.div`
  display: flex;
  flex-direction: row;
  width: 100%;
  justify-content: space-around;
  align-items: center;
  /* background-color: lightcoral; */
  height: fit-content;
  .bar-wrapper {
    width: 100%;
    height: 80px;
    display: flex;
    flex: 1;
    align-items: center;
    /* background-color: lightsalmon; */
  }
  canvas {
    /* background-color: #44dd4455; */
  }
`;
