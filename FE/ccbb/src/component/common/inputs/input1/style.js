import styled from "styled-components";

export const StyledInput = styled.input`
  border: none;
  border-bottom: 1px solid white;
  padding: 10px;
  width: ${(props) => props.width || "100%"};
  height: ${(props) => props.height || "auto"};
  box-sizing: border-box;
  background: transparent;
  color: white;
  font-size: 20px;
  margin-bottom: 20px;
  &:focus {
    outline: none;
  }
`;

export const Inputlabel = styled.label`
  color: white;
  padding-bottom: 5px;
`;

export const InputBox = styled.div`
  display: flex;
  flex-direction: column;
`;
