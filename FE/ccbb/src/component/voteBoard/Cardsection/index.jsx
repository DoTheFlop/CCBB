import React from 'react';
import * as S from './style';

const VoteCard = ({ videoUrl, title, amount }) => {
    return (
        <S.Card>
            <video width={"100%"} src={videoUrl} alt='hi' style={{ borderTopLeftRadius: '10px' ,borderTopRightRadius:'10px'}}></video>
            <h3 style={{marginLeft:'10px'}}>{title}</h3>
            <p style={{marginLeft:'10px'}}>Amount: ${amount}</p>
        </S.Card>
    );
};

export default VoteCard;
