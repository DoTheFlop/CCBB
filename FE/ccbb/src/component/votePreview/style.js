import styled from "styled-components";

export const PreviewItem = styled.div`
  &:hover {
    background-color: #efedf0;
  }
  border-bottom: solid 1px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 10px 5px 10px 5px;
  a {
    display: flex;
    flex: 1;
  }
`;

export const TitleAndDate = styled.div`
  display: flex;
  flex-direction: column;
  flex: 1;
  width: 100%;
  .vote-title {
    font-size: 2rem;
  }
`;

export const IconAndCnt = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  svg {
    margin-right: 5px;
  }
  span {
    margin-right: 10px;
  }
`;
