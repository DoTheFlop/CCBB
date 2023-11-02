import Button1 from '../../../../component/common/buttons';
import Headermenu from '../../../../component/common/headers/headermenu';
import Input2 from '../../../../component/common/inputs/input2';
import InputComment from '../../../../component/common/inputs/inputcomment';
import * as S from './style';
import { useState } from 'react';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css'; 
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import { MenuItem, Select } from '@mui/material';
import RemoveCircleIcon from '@mui/icons-material/RemoveCircle';



export default function LoLvoteCreatePage() {
    const options = [
        { value: 'Iron', label: '아이언' },
        { value: 'Bronze', label: '브론즈' },
        { value: 'Silver', label: '실버' },
        { value: 'Gold', label: '골드' },
        { value: 'Platinum', label: '플래티넘' },
        { value: 'Emerald', label: '에메랄드' },
        { value: 'Diamond', label: '다이아몬드' },
        { value: 'Master', label: '마스터' },
        { value: 'GrandMaster', label: '그랜드마스터' },
        { value: 'Challenger', label: '챌린저' },
    ];

    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedTier, setSelectedTier] = useState(options[0]);
    const handleDateChange = (date) => {
        setSelectedDate(date);
    };

    const handleTierChange = (selected) => {
        setSelectedTier(selected);
    };

    
    const [uploadedFiles, setUploadedFiles] = useState([]);

    const createPreview = (file) => {
        if (file.type.startsWith('image/')) {
          return URL.createObjectURL(file);
        } else if (file.type.startsWith('video/')) {
          return URL.createObjectURL(file);
        }
        return null;
      };

      

    const handleFileUpload = (event) => {
        const files = event.target.files;
        const updatedFiles = [...uploadedFiles];
    
        for (const file of files) {
        updatedFiles.push({
            file,
            preview: createPreview(file),
        });
        }
    
        setUploadedFiles(updatedFiles);
    };
      

      const handleDeleteFile = (index) => {
        const updatedFiles = [...uploadedFiles];
        updatedFiles.splice(index, 1);
        setUploadedFiles(updatedFiles);
      };


    return (
        <S.Main>
            <S.Head>
                <S.Headtop>
                    <Headermenu title={"리그 오브 레전드"}/>
                </S.Headtop>
            </S.Head>
            <S.CreateBodyCover>
                <S.CreateBody>
                    <Input2 label="제목" id="title" width="100%" height="40px"/>
                    <Input2 label="논점" id="argument" width="100%" height="40px"/>
                    <InputComment label="내용" id="content" width="100%" height="200px"/>
                    <h3>파일목록</h3>
                    <S.Uploadfile>
                        {uploadedFiles.length > 0 && (
                            <div>
                                <div style={{ display: 'flex', overflowX: 'auto', height:'162px',maxHeight: '200px', position: 'relative' ,scrollbarGutter: 'start'}}>
                                    {uploadedFiles.map((uploadedFile, index) => (
                                        <div key={index} style={{ listStyleType: 'none', padding:'0 30px 0 30px',position:'relative', border:'1px solid #ccc'}}>
                                            {uploadedFile.file.type.startsWith('image/') ? (
                                                <div>
                                                    <img
                                                        src={uploadedFile.preview}
                                                        alt={uploadedFile.file.name}
                                                        width="100"
                                                        height="100"
                                                    />
                                                </div>
                                            ) : uploadedFile.file.type.startsWith('video/') ? (
                                                <div>
                                                    <video width="100" height="100">
                                                        <source src={uploadedFile.preview} type={uploadedFile.file.type} />
                                                    </video>
                                                </div>
                                            ) : (
                                                <p>Unsupported File Type</p>
                                            )}
                                            <p style={{ overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap', maxWidth: '100px' }}>
                                                {uploadedFile.file.name}
                                            </p>
                                            <button
                                                style={{
                                                    marginLeft:'100px',
                                                    marginTop:'-150px'
                                                }}
                                                className="delete-button"
                                                onClick={() => handleDeleteFile(index)
                                                }
                                                >
                                                <RemoveCircleIcon/>
                                            </button>
                                        </div>
                                    ))}
                                </div>
                            </div>
                        )}
                        <input
                                type="file"
                                accept="image/*, video/*"
                                id='input-file'
                                multiple
                                onChange={handleFileUpload}
                                style={{display:'none'}}
                                
                            />
                    </S.Uploadfile>
                        <label className="input-file-button" for="input-file">
                            업로드
                        </label>
                    <Input2 label="공약" id="promise" width="100%" height="40px"/>
                    <S.CreateBodybottom>
                        <S.LeftBottom>
                            <Input2 label="받는사람" id="recieveuser" width="80%" height="40px"/>
                            <label style={{marginTop:'10px'}}>투표가능 최소티어</label>
                            <Select
                                value={selectedTier.value}
                                onChange={(event) => handleTierChange(options.find(option => option.value === event.target.value))}
                                style={{ width: '80%', height:'40px',marginTop:'10px',marginBottom:'10px',border: '1px solid black'}}
                                >
                                {options.map((option) => (
                                    <MenuItem key={option.value} value={option.value} style={{height:'38px'}}>
                                    {option.label}
                                    </MenuItem>
                                ))}
                            </Select>
                        </S.LeftBottom>
                        <S.RightBottom>
                            <S.VoteRange>
                                <label style={{ padding: '10px 0' }} for="voterange">
                                    투표마감일
                                </label>
                                <DatePicker
                                    selected={selectedDate}
                                    onChange={handleDateChange}
                                    showTimeSelect={false}
                                    dateFormat="yyyy-MM-dd"
                                    placeholderText="투표 마감일을 선택하세요"
                                    id='voterange'
        
                                />
                                <CalendarMonthIcon  style={{ position: 'absolute', top: '60%', left: '120%'}}/>
                            </S.VoteRange>
                            <Input2 label="보증금" id="amount" width="100%" height="40px" placeholder="10,000 원" disabled={true}/>
                        </S.RightBottom>
                    </S.CreateBodybottom>
                    <S.buttonBox>
                        <Button1 width={'70px'} height={'30px'} text={'보내기'} size={'15px'}/>
                    </S.buttonBox>
                </S.CreateBody>
            </S.CreateBodyCover>
                
        </S.Main>
    );
}
