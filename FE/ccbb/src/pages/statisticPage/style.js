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
  padding: 2% 12% 3% 12%;
  box-sizing: border-box;
  background-color: white;
  flex-wrap: wrap;
`;

export const PieWithDescription = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 10px 10% 10px;
  max-width: 40%;
  min-width: 250px;
  width: 40%;
  text {
    font-weight: bold;
  }
  & > svg {
    width: 100%;
    height: 100%;
  }
`;

export const DualPie = styled.div`
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
  justify-content: space-between;
`;
