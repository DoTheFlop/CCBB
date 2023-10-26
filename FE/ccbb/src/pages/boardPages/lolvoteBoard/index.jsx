import * as S from './style';
import Headermenu from "../../../component/common/headers/headermenu";
import Button1 from '../../../component/common/buttons';
import VoteCard from '../../../component/voteBoard/Cardsection';
import { useState } from 'react';
import Select from 'react-select';

const options = [
    { value: 'Latest', label: '최신순' },
    { value: 'Popular', label: '인기순' },
    { value: 'Amount', label: '금액순' },
  ];

export default function LoLvoteboardPage() {

    const [activeTab, setActiveTab] = useState('ongoing'); // 'ongoing' 또는 'completed'
    const [selectedOption, setSelectedOption] = useState(options[0]);

    const toggleTab = (tab) => {
        setActiveTab(tab);
    };

    const handleOptionChange = (selected) => {
        setSelectedOption(selected);
      };
    
    
      const customStyles = {
        control: (provided) => ({
          ...provided,
          background: 'transparent', // 배경을 투명색으로 설정
          borderColor: 'transparent', // 테두리를 투명으로 설정 (선택 시 테두리 제거)
          boxShadow: 'none', // 테두리 그림자 제거
          '&:hover': { borderColor: 'transparent' },
        }),
        option: (provided, state) => ({
          ...provided,
          backgroundColor: state.isFocused ? '#154C61' : 'transparent', // 마우스 호버 상태일 때 배경 색상 변경
          color: state.isFocused ? 'white' : 'black', // 마우스 호버 상태일 때 글자색 변경
          border: 'none', // 구분선 제거
        }),
        indicatorSeparator: () => null, // 구분선 숨기기
      };

    const customControlStyles = {
    singleValue: (provided) => ({
        ...provided,
        color: 'white', // 텍스트 색상을 흰색으로 설정
        fontSize: '18px', // 텍스트 크기를 18px로 설정
        fontWeight: 'bold', // 글자를 굵게 표시
    }),
    };
    

    const dummyData = [
        {
          id: 1,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 1 Title',
          amount: 100,
        },
        {
          id: 2,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 2 Title',
          amount: 150,
        },
        {
          id: 3,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 3 Title',
          amount: 75,
        },
        {
          id: 4,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 4 Title',
          amount: 200,
        },
        {
          id: 5,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 5 Title',
          amount: 50,
        },
        {
          id: 6,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 6 Title',
          amount: 120,
        },
        {
          id: 7,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 7 Title',
          amount: 90,
        },
        {
          id: 8,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 8 Title',
          amount: 175,
        },
        {
          id: 9,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 9 Title',
          amount: 60,
        },
        {
          id: 10,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 10 Title',
          amount: 110,
        },
        {
          id: 11,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 11 Title',
          amount: 130,
        },
        {
          id: 12,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 12 Title',
          amount: 85,
        },
        {
          id: 13,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 13 Title',
          amount: 190,
        },
        {
          id: 14,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 14 Title',
          amount: 70,
        },
        {
          id: 15,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 15 Title',
          amount: 140,
        },
        {
          id: 16,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 16 Title',
          amount: 95,
        },
        {
          id: 17,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 17 Title',
          amount: 180,
        },
        {
          id: 18,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 18 Title',
          amount: 65,
        },
        {
          id: 19,
          videoUrl: 'resource/LoLsample.mp4',
          title: 'Video 19 Title',
          amount: 160,
        },
      ];
      


    return (
        <S.Main>
            <S.Head>
                <S.Headtop>
                    <Headermenu title={"리그 오브 레전드"}/>
                </S.Headtop>
                <S.Headbottom>
                <S.Menuhead>
                        <h3 onClick={() => toggleTab('ongoing')} className={activeTab === 'ongoing' ? 'active' : ''}>진행중인 투표</h3>
                        <h3 onClick={() => toggleTab('completed')} className={activeTab === 'completed' ? 'active' : ''}>종료된 투표</h3>
                    </S.Menuhead>
                    <S.Actions>
                        <S.Dropdown>
                        <Select
                        options={options}
                        value={selectedOption}
                        onChange={handleOptionChange}
                        styles={{ ...customStyles, ...customControlStyles }}
                        />
                        </S.Dropdown>
                        <S.A_1>
                            <p style={{paddingRight:'10px'}}>00건</p>
                            <Button1 text={"글 작성"} width={"75px"} height={"30px"} />
                        </S.A_1>
                    </S.Actions>
                </S.Headbottom>
            </S.Head>
            <S.Votebodycover>
                <S.Votebody>
                {dummyData.map((item) => (
                    <VoteCard
                    key={item.id}
                    videoUrl={item.videoUrl}
                    title={item.title}
                    amount={item.amount}
                    />
                    ))}
                </S.Votebody>
            </S.Votebodycover>
        </S.Main>
    );
}
