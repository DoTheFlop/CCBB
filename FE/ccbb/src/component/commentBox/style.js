import styled from "styled-components";

export const Box = styled.div`
  background-color: ${(props) => props.bgcolor || "transparent"};
  border-radius: 10px;
  display: flex;
  flex-direction: column;
  margin-top: 1%;
  margin-bottom: 1%;
  padding: 1% 2% 2% 2%;
`;

export const CommentHead = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  p {
    font-size: 12px;
  }
`;

export const CommentBody = styled.div``;

export const CommentBottom = styled.div`
  display: flex;
  justify-content: end;
  .divider {
    margin-left: 3px;
    margin-right: 3px;
  }
  .modify-comment:hover {
    text-decoration: underline;
  }
  .delete-comment:hover {
    text-decoration: underline;
  }
`;
