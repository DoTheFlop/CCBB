import styled from 'styled-components';

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

export const IntroduceWrapper = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 100%;
    margin: 0 auto;
    padding: 20px;
    background-color: white;  // 배경색을 흰색으로 설정
    box-sizing: border-box;
`;

export const Title = styled.h1`
    color: #b8860b;  // 색상을 덜 밝은 골드로 조정
    font-size: 32px;
    text-align: center;
    margin-bottom: 20px;
`;

export const Description = styled.p`
    color: black;
    font-size: 20px;
    text-align: justify;
    text-align: center;
    margin-bottom: 20px;
`;

export const SubTitle = styled.h2`
    color: black;
    font-size: 24px;
    text-align: left;
    text-align: center;
    margin-bottom: 10px;
`;

export const Text = styled.p`
    color: black;
    font-size: 18px;
    text-align: justify;
    text-align: center;
    margin-bottom: 20px;
`;
