import styled from "styled-components";
import { Img } from "../signinPage/style";

export { Img };

export const GenderBox = styled.div`
    color: white;
`

export const GenderRadio = styled.input`
  appearance: none; /* Remove default radio button style */
  width: 20px; /* Set the width of the custom radio button */
  height: 20px; /* Set the height of the custom radio button */
  border: 2px solid #333; /* Add a border around the custom radio button */
  border-radius: 50%;
  cursor: pointer;
  outline: none;
  
  &:checked {
    background-color: #333;
  }
`;