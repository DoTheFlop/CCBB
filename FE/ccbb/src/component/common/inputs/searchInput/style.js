import styled from "styled-components";

export const search = styled.div`
  width: ${(props) => props.width || "100%"};
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  border-bottom: solid 1px;
  padding-bottom: 2px;
`;

export const input = styled.input`
  flex: 1;
  border: 0;
  box-shadow: none;
  &:focus {
    outline: none;
    box-shadow: none;
  }
`;
