import { Select } from '@mui/material';
import CommentBox from '../../component/commentBox';
import Button1 from '../../component/common/buttons';
import Headermenu from '../../component/common/headers/headermenu';
import Input1 from '../../component/common/inputs/input1';
import Input2 from '../../component/common/inputs/input2';
import UserProfile from '../../component/common/profile';
import * as S from './style';
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import { useState } from 'react';

const options = [
    { value: 'Latest', label: '최신순' },
    { value: 'Popular', label: '인기순' },
    { value: 'Amount', label: '금액순' },
  ];
export default function ComponentPage() {
    const [selectedOption, setSelectedOption] = useState(options[0]);
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
    return (
        <S.main>
            <Headermenu title={"리그 오브 레전드"}/>
            <Headermenu title={"자유게시판"}/>
            <Input1 label="닉네임" id="nicknameInput" width="300px" height="40px" />
            <Input2 label="닉네임" id="nickname" width="300px" height="40px" />
            <Button1 text={"로그인"} width={'75px'} height={'30px'}/>
            <UserProfile name={"챌우혁"}/>
            <CalendarMonthIcon/>
            <CommentBox bgcolor="#97A7FF" comment="This is a comment" userId="user123" date="2023-10-31" />
            <S.Actions>
                        <S.Dropdown>
                        <Select
                        options={options}
                        value={selectedOption}
                        onChange={handleOptionChange}
                        styles={{ ...customStyles, ...customControlStyles }}
                        />
                        </S.Dropdown>
                    </S.Actions>
        </S.main>  
    )
}