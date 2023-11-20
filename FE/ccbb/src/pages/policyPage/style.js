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

export const section = styled.div`
  display: flex;
  flex: 1;
  width: 100%;
  padding: 4% 10% 3% 10%;
  box-sizing: border-box;
  background-color: white;
  flex-wrap: wrap;
  font-size: 18px;

  li {
    padding: 5px 0px;
  }
`;