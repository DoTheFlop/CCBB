import styled from "styled-components";
import { StyledInput } from "../common/inputs/inputcomment/style";

export { StyledInput };
export const Box = styled.div`
  background-color: ${(props) => props.bgcolor || "transparent"};
  /* border-radius: 10px; */
  display: flex;
  flex-direction: column;
  // margin-top: 1%;
  // margin-bottom: 1%;
  padding: 1% 2% 0.5% 2%;
  & > hr {
    align-self: center;
    height: 0px;
    width: 99%;
    margin: 0;
    border: solid 1px;
    border-color: lightgray;
    border-radius: 10px;
  }
`;

export const CommentHead = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  p {
    font-size: 12px;
  }
`;

export const CommentBody = styled.div`
  p {
    white-space: pre-line;
  }
`;

export const CommentBottom = styled.div`
  display: flex;
  justify-content: end;
  .divider {
    margin-left: 3px;
    margin-right: 3px;
  }
  .save-comment:hover {
    text-decoration: underline;
  }
  .modify-comment:hover {
    text-decoration: underline;
  }
  .delete-comment:hover {
    text-decoration: underline;
  }
`;
