import styled from "styled-components";

export const StyledInput = styled.textarea`
  padding: 10px;
  width: ${(props) => props.width || "100%"};
  height: ${(props) => props.height || "90px"};
  box-sizing: border-box;
  font-size: 16px;
  border-radius: 5px;
  resize: none;
  margin-right: 10px;
  font-family: Pretendard, "Noto Sans KR", sans-serif;
`;

export const Inputlabel = styled.label`
  color: black;
  padding-bottom: 10px;
  font-size: 20px;
  font-weight: bold;
`;

export const InputBox = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
  padding: 20px 0;
`;
export const InputAndButton = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
`;
