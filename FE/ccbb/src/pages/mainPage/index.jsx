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
  // const dummyData = [
  //   "resource/수정본1.mp4",
  //   "resource/2.mp4",
  //   "resource/3.mp4",
  //   "resource/1.mp4",
  //   "resource/2.mp4",
  // ]
  const handleMouseEnter = (index) => {
    if (index === activeSlideIndex) {
      setIsMouseOver(true);
    }
  };
  
  const handleMouseLeave = (index) => {
    if (index === activeSlideIndex) {
      setIsMouseOver(false);
    }
  };
  useEffect(() => {
    ccbbApi
      .get("post/vote/popularList?page=1")
      .then((response) => {
        setTopVideos(response.data.voteList.content.slice(0, 5));
      })
      .catch((error) => {
        console.error("Error fetching data:", error);
      });
  }, []);
  useEffect(() => {
    if (isMouseOver) {
      setPlaying(true);
      console.log(topVideos);
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
                  loop
                />
                {/* <ReactPlayer
                  url={video}
                //   controls
                  width="98%"
                  height="98%"
                  muted
                  playing={isMouseOver && index === activeSlideIndex && playing}
                  onMouseEnter={handleMouseEnter}
                  onMouseLeave={handleMouseLeave}
                  loop
                /> */}

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
                     handleMouseEnter(index)
                      
                    }
                  }}
                  onMouseLeave={() => {
                    if (index === activeSlideIndex) {
                      handleMouseLeave(index);
                    }
                  }}
                  onClick={() => handleVideoClick(video.postId)}
                ></div>
                <div
                  style={{
                    position: "absolute",
                    top: 0,
                    left: 0,
                    width: "100%",
                    height: "100%",
                    background: "rgba(128, 128, 128, 0.5)", // 약간 투명한 회색 배경
                    color: "white", // 글자 색
                    fontSize: "20px", // 글자 크기
                    fontFamily: "'Comic Sans MS', cursive, sans-serif", // 글자체
                    cursor: "pointer",
                    display:
                      isMouseOver && index === activeSlideIndex && playing
                        ? "none"
                        : "flex", // 마우스가 올라가면 display를 none으로, 아니라면 flex로 설정
                    justifyContent: "center", // 세로축 가운데 정렬
                    alignItems: "center", // 가로축 가운데 정렬
                  }}
                  onMouseEnter={() => handleMouseEnter(index)}
  onMouseLeave={() => handleMouseLeave(index)}
                  onClick={() => handleVideoClick(video.postId)}
                >
                  {video.argument}
                </div>
              </SwiperSlide>
            ))}
          </Swiper>
        </S.Moviebox>
      </S.Pagebottom>
    </S.Pagemain>
  );
}
