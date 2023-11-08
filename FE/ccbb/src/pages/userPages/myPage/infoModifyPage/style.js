import styled from "styled-components";

export const Img = styled.img`
  border-radius: 50%;
  width: 180px;
`;

export const main = styled.main`
  margin-top: 100px;
  min-height: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
  .alert-message {
    color: red;
    margin-bottom: 10px;
    margin-top: -10px;
  }
`;

export const ModifyBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  width: fit-content;
  padding: 10%;
  margin-left: 7%;
  margin-right: 7%;
  border: solid 2px;
  border-color: white;
  border-radius: 10px;
  position: relative;
  box-sizing: border-box;
  h4 {
    color: white;
    margin-top: -20%;
    margin-left: -10%;
    background-color: #0b123f;
    position: absolute;
    padding: 10px;
  }
`;
