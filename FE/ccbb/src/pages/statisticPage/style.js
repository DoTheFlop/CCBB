import styled from "styled-components";

export const main = styled.main`
  display: flex;
  flex: 1;
  flex-direction: column;
  align-items: center;
  width: 100%;
  header {
    width: 80%;
    margin: 20px 0 20px 0;
  }
`;

export const StatisticSection = styled.div`
  display: flex;
  flex: 1;
  flex-direction: column;
  width: 100%;
  padding: 2% 10% 3% 10%;
  box-sizing: border-box;
  background-color: white;
  flex-wrap: wrap;
`;

export const PieWithDescription = styled.div`
  display: flex;
  flex-direction: column;
  position: relative;
  margin: 0 1% 10% 1%;
  max-width: 600px;
  min-width: 250px;
  width: 48%;
  height: fit-content;
  text {
    font-weight: bold;
  }
  & > svg {
    /* position: absolute; */
    width: 125%;
    margin-left: -10%;
    height: 125%;
    margin-top: -20%;
  }
`;

export const DualPie = styled.div`
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
`;
