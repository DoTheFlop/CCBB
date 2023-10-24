import * as S from './style';
import React, { useRef, useState } from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/css';
import 'swiper/css/pagination';
import 'swiper/css/navigation';
import { Pagination, Navigation } from 'swiper/modules';
import ReactPlayer from 'react-player';

export default function MainPage() {
    const CAROUSEL_VIDEOS = [
        'resource/168787.mp4',
        '/resource/168787.mp4',
        '/resource/168811.mp4',
        '/resource/173522.mp4',
        '/resource/183271.mp4',
    ]

    const [swiperRef, setSwiperRef] = useState(null);

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
                    onSwiper={setSwiperRef}
                    slidesPerView={3}
                    centeredSlides={true}
                    spaceBetween={30}
                    pagination={{
                    type: 'fraction',
                    }}
                    navigation={true}
                    modules={[Pagination, Navigation]}
                    className="mySwiper"
                >
                    {CAROUSEL_VIDEOS.map((video, index) => (
                            <SwiperSlide key={index}>
                                <ReactPlayer
                                    url={video}
                                    controls
                                    width="100%"
                                    height="100%"
                                />
                            </SwiperSlide>
                        ))}
                </Swiper>
                </S.Moviebox>
            </S.Pagebottom>
        </S.Pagemain>
    );
}
