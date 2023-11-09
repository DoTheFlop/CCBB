import * as S from "./style";
import React, { useEffect, useState } from "react";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/pagination";
import "swiper/css/navigation";
import { Pagination, Navigation } from "swiper/modules";
import ReactPlayer from "react-player";
import { ccbbApi } from "../../api/ccbbApi";
import { useNavigate } from "react-router";

export default function MainPage() {
  const [topVideos, setTopVideos] = useState([]);
  const [activeSlideIndex, setActiveSlideIndex] = useState(0);
  const [playing, setPlaying] = useState(false);
  const [isMouseOver, setIsMouseOver] = useState(false);

  const handleMouseEnter = () => {
    setIsMouseOver(true);
  };

  const handleMouseLeave = () => {
    setIsMouseOver(false);
  };
  useEffect(() => {
    // 데이터를 가져오는 비동기 함수 정의
    const fetchData = async () => {
      try {
        const response = await ccbbApi.get("post/vote/popularList?page=1");
        setTopVideos(response.data.voteList.content.slice(0, 5));
        console.log(topVideos);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []); // 빈 배열을 전달하여 한 번만 실행
  useEffect(() => {
    if (isMouseOver) {
      setPlaying(true);
    } else {
      setPlaying(false);
    }
  }, [isMouseOver]);

  useEffect(() => {
    if (activeSlideIndex === 0) {
      setPlaying(true);
    }
  }, [activeSlideIndex]);
  const navigate = useNavigate();

  const handleVideoClick = (postId) => {
    navigate(`/lolvote/${postId}`);
  };
  return (
    <S.Pagemain>
      <S.Pageheader>
        <S.Img
          src="/resource/Logo.png"
          alt="CC.BB"
          style={{ width: "200px", height: "60px" }}
        />
        <p>여러분의 손으로 시시비비를 가리세요.</p>
      </S.Pageheader>
      <S.Pagebottom>
        <p>투표율 TOP5</p>
        <S.Moviebox>
          <Swiper
            onSlideChange={(swiper) => {
              setActiveSlideIndex(swiper.realIndex);
              setPlaying(true);
            }}
            slidesPerView={3}
            centeredSlides={true}
            spaceBetween={30}
            pagination={{
              clickable: true,
            }}
            loop={true}
            loopedSlides={2}
            navigation={true}
            modules={[Pagination, Navigation]}
            className="mySwiper"
          >
            {topVideos.map((video, index) => (
              <SwiperSlide key={index}>
                <ReactPlayer
                  url={`https://ccbb.pro/api/file/get/${video.fileId[0].fileId}`}
                //   controls
                  width="98%"
                  height="98%"
                  muted
                  playing={isMouseOver && index === activeSlideIndex && playing}
                  onMouseEnter={handleMouseEnter}
                  onMouseLeave={handleMouseLeave}
                  loop
                />

                <div
                  style={{
                    position: "absolute",
                    top: 0,
                    left: 0,
                    width: "100%",
                    height: "100%",
                    background: "transparent",
                    cursor: "pointer",
                  }}
                  onMouseEnter={() => {
                    if (index === activeSlideIndex) {
                      handleMouseEnter();
                    }
                  }}
                  onMouseLeave={() => {
                    if (index === activeSlideIndex) {
                      handleMouseLeave();
                    }
                  }}
                  onClick={() => handleVideoClick(video.postId)}
                ></div>
              </SwiperSlide>
            ))}
          </Swiper>
        </S.Moviebox>
      </S.Pagebottom>
    </S.Pagemain>
  );
}
