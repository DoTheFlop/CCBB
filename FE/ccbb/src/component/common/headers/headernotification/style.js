import styled from "styled-components";

export const Container = styled.div`
  background-color: white;
  // border: 1px solid #ddd;
  border-radius: 20px;
  // padding: 10px;
  margin: 10px 0;
  width: 400px;
  position: absolute;
  top: 100%;
  right: 0;
  color: black;
  z-index: 2;
  max-height: 400px;
  overflow: auto;
  box-shadow: 0px 0px 20px rgba(1, 1, 1, 0.5);
  opacity: ${(props) => (props.$isVisible ? 1 : 0)};
  transform: ${(props) =>
    props.$isVisible ? "translateY(0)" : "translateY(10px)"};
  transition: opacity 0.3s, transform 0.3s;

  /* 스크롤바 스타일 */
  &::-webkit-scrollbar {
    width: 8px;
  }
  &::-webkit-scrollbar-track {
    background: transparent;
  }
  &::-webkit-scrollbar-thumb {
    background: #ddd;
    border-radius: 4px;
  }
  &::-webkit-scrollbar-thumb:hover {
    background: #bbb;
  }
`;

export const Top = styled.div`
  padding: 10px 30px;
  font-weight: bold;
  // border-bottom: 1px solid;
  background-color: #ddd;
`;

export const Empty = styled.div`
  padding: 10px 20px;
`;
