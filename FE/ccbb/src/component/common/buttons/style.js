import styled from "styled-components";

export const Button1 = styled.button`
  width: ${(props) => props.width};
  height: ${(props) => props.height};
  background-color: ${(props) => props.color || "#1F2A70"};
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: ${(props) => props.size || '18px'};
  font-weight: bold;
`;
