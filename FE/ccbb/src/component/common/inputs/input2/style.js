import styled from "styled-components";

export const StyledInput = styled.input`
  padding: 10px;
  width: ${(props) => props.width || "100%"};
  height: ${(props) => props.height || "auto"};
  box-sizing: border-box;
  font-size: 20px;
  border-radius: 5px;
`;

export const Inputlabel = styled.label`
  color: ${(props) => props.color || "black"};
  padding-bottom: 10px;
  font-size: 20px;
  font-weight: bold;
  display: flex;
  align-items: center;

  svg {
    margin-left: 5px;
  }
`;

export const InputBox = styled.div`
  display: flex;
  flex-direction: column;
  padding: 10px 0;
`;
