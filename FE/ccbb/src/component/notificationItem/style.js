// style.js
import styled from "styled-components";

export const Container = styled.div`
  padding: 10px 20px 0px 20px;
  // border-bottom: 1px solid;
  display: flex;
  cursor: pointer;
  background-color: ${(props) => (props.$isRead ? "white" : "#ECF2FF")};

  &:last-child {
    border-radius: 20px;
  }
`;

export const TopSection = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 40px;
`;

export const RightSection = styled.div`
  margin-left: 10px;
`;

export const Img = styled.img`
  width: 30px;
  height: 30px;
  border-radius: 9999px;
`;

export const Username = styled.div`
  font-weight: bold;
  font-size: 16px;
  color: #555;
`;

export const Content = styled.div`
  font-size: 16px;
  padding: 5px 0 20px 0;
`;
