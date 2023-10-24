import styled from "styled-components";

export const StyledInput = styled.input`
  border-bottom: 1px solid #ccc;  
  padding: 10px;         
  width: ${(props) => props.width || "100%"};
  height: ${(props) => props.height || "auto"};
  box-sizing: border-box;
  font-size: 20px;
  border-radius: 5px;
  
`;

export const Inputlabel = styled.label`
    color: white;
    padding-bottom: 5px;
`

export const InputBox = styled.div`
    display: flex;
    flex-direction: column;
`