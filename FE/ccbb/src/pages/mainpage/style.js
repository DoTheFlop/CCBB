import styled from "styled-components";

export const Pagemain = styled.main`
    display: flex;
    flex-direction: column;
    p {
        color: white;
    }
`

export const Pageheader = styled.section`
    display: flex;
    flex-direction: column;
    align-items: center;
    font-size: 18px;
`
export const Img = styled.img`
  margin-top: 50px;
`

export const Pagebottom = styled.section`
    font-weight: bold;
`


export const Moviebox = styled.article`
  display: flex;
  overflow: hidden; /* 넘치는 컨텐츠 숨기기 */
  position: relative;
  max-width: 100%;
  margin: 0 auto;
  padding: 0 20px;

  .carousel-container {
    display: flex;
    transition: transform 0.3s ease;
  }

  .carousel-item {
    flex: 0 0 70%; /* 각 영상 너비 설정 */
    margin: 0 15px;
    transition: flex 0.3s ease;
  }

  .carousel-arrow {
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
    cursor: pointer;
  }

  .carousel-arrow.left {
    left: 0;
  }

  .carousel-arrow.right {
    right: 0;
  }

  .carousel-indicators {
    display: flex;
    justify-content: center;
    margin-top: 10px;
  }

  .carousel-indicator {
    width: 10px;
    height: 10px;
    border-radius: 50%;
    background: #000;
    margin: 0 5px;
    cursor: pointer;
  }

  .carousel-indicator.active {
    background: #fff;
  }
`;
