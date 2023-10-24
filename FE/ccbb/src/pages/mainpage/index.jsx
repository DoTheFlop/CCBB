import * as S from './style';
import { Navigation, Pagination, Scrollbar, A11y } from 'swiper/modules';
import { Swiper, SwiperSlide } from 'swiper/react';

// Import Swiper styles
import 'swiper/css';
import 'swiper/css/navigation';
import 'swiper/css/pagination';
import 'swiper/css/scrollbar';

export default function MainPage() {
    const CAROUSEL_IMAGES = [
        'https://img.freepik.com/free-photo/vivid-blurred-colorful-background_58702-2545.jpg',
        'https://img.freepik.com/premium-vector/abstract-pastel-color-background-with-pink-purple-gradient-effect-graphic-design-decoration_120819-463.jpg',
        'https://media.architecturaldigest.com/photos/6080a73d795a7b010f3dd2e0/2:1/w_2700,h_1350,c_limit/GettyImages-1213929929.jpg',
    ]

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
                        // Install Swiper modules
                        modules={[Navigation, Pagination, Scrollbar, A11y]}
                        spaceBetween={50}
                        slidesPerView={3}
                        navigation
                        pagination={{ clickable: true }}
                        scrollbar={{ draggable: true }}
                        onSwiper={(swiper) => console.log(swiper)}
                        onSlideChange={() => console.log('slide change')}
                    >
                        {CAROUSEL_IMAGES.map((image, index) => (
                            <SwiperSlide key={index}>
                                <img src={image} alt={`Image ${index + 1}`} />
                            </SwiperSlide>
                        ))}
                    </Swiper>
                </S.Moviebox>
            </S.Pagebottom>
        </S.Pagemain>
    );
}
