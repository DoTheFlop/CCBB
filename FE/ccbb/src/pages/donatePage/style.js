import styled from "styled-components";

export const main = styled.main`
  display: flex;
  flex: 1;
  flex-direction: column;
  align-items: center;
  width: 100%;
  header {
    width: 80%;
    margin: 20px 0 20px 0;
  }
`;

export const donateSection = styled.div`
  display: flex;
  flex: 1;
  flex-direction: column;
  width: 100%;
  padding: 2% 12% 3% 12%;
  box-sizing: border-box;
  background-color: white;
  flex-wrap: wrap;
  hr {
    border: 2px solid;
    width: 100%;
    margin: 10px 0 0 0;
  }
  .search {
    margin-top: 20px;
    align-self: flex-end;
  }
`;

export const accumulatedDonation = styled.div`
  border: solid 2px;
  border-radius: 10px;
  background-color: aliceblue;
  display: flex;
  justify-content: end;
  align-items: center;
  h1 {
    margin: 1%;
    margin-right: 30px;
  }
`;

export const donatePreview = styled.div`
  border-bottom: solid 1px;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  cursor: pointer;
  align-items: center;
  height: 70px;
  p {
    flex: 1;
    text-align: center;
    margin: 0;
  }
  &:hover {
    background-color: aliceblue; // 연한 회색
  }
`;

export const ModalWrapper = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
`;

export const ModalContent = styled.div`
  position: fixed;
  background: white;
  width: 50%;
  height: auto;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  img {
    max-width: 100%;
    height: auto;
  }
`;
