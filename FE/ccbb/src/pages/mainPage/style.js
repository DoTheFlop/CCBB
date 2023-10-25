import styled from "styled-components";

export const Pagemain = styled.main`
    display: flex;
    height: 700px;
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
    margin-top: 30px;
    font-weight: bold;
    font-size: 20px;
`


export const Moviebox = styled.article`

.swiper-slide {
  text-align: center;
  font-size: 18px;
  background: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
}

.swiper {
  width: 1400px;
  height: 250px;
}

/* 다음 버튼 아이콘의 색상을 변경 */
.swiper-button-next, .swiper-container-rtl .swiper-button-prev {
    color: white; /* 원하는 색상으로 변경 */
}

.swiper-button-prev, .swiper-container-rtl .swiper-button-next {
    color: white; /* 원하는 색상으로 변경 */
}
.swiper-pagination-bullet-active{
    background-color : white;
}
.swiper-pagination-bullet{
    background-color: white;
}
`
