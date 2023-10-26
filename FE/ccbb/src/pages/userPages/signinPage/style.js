import styled from "styled-components";

export const bg = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const signinMenu = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: end;
  width: 100%;

  & > a {
    margin-left: 20px;
    text-decoration: none;
    color: #fff;
    &:hover {
      text-decoration: underline;
    }
  }
`;

export const Img = styled.img`
  margin-top: 50px;
  margin-bottom: 30px;
`;

