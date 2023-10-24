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

.swiper-slide img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.swiper {
  width: 1400px;
  height: 250px;
  margin: 20px auto;
}
`;
