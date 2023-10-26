import * as S from './style';
import React, { useEffect, useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import { Pagination, Navigation } from 'swiper/modules';
import ReactPlayer from 'react-player';

export default function MainPage() {
    const CAROUSEL_VIDEOS = [
        'resource/LoLsample.mp4',
        '/resource/168787.mp4',
        '/resource/168811.mp4',
        '/resource/173522.mp4',
        '/resource/183271.mp4',
    ]
    const [activeSlideIndex, setActiveSlideIndex] = useState(0);
    const [playing, setPlaying] = useState(false);

    useEffect(() => {

        if (activeSlideIndex === 0) {
            setPlaying(true);
        }

    }, [activeSlideIndex]);

    return (
        <S.Pagemain>
            <S.Pageheader>
                <S.Img src="/resource/Logo.png" alt="CC.BB" style={{ width: "200px", height: "60px" }} />
                <p>여러분의 손으로 시시비비를 가리세요.</p>
            </S.Pageheader>
            <S.Pagebottom>
                <p>투표율 TOP5</p>
                <S.Moviebox>
                    <Swiper

                        onSlideChange={(swiper) => {
                            setActiveSlideIndex(swiper.realIndex);
                            setPlaying(true); // 슬라이드가 변경될 때마다 재생
                        }}
                        slidesPerView={3}
                        centeredSlides={true}
                        spaceBetween={30}
                        pagination={{
                            clickable: true ,
                        }}
                        loop={true}
                        loopedSlides={2}
                        navigation={true}
                        modules={[Pagination, Navigation]}
                        className="mySwiper"
                    >
                        {CAROUSEL_VIDEOS.map((video, index) => (
                                <SwiperSlide key={index}>
                                    <ReactPlayer
                                        url={video}
                                        controls
                                        width="98%"
                                        height="98%"
                                        muted
                                        playing={playing && index === activeSlideIndex}
                                    />
                                </SwiperSlide>
                            ))}
                    </Swiper>
                </S.Moviebox>
            </S.Pagebottom>
        </S.Pagemain>
    );
}
