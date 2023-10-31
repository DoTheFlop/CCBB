import Button1 from '../common/buttons';
import UserProfile from '../common/profile';
import * as S from './style';

export default function CommentBox({ bgcolor, comment, userId, date }) {
  return (
    <S.Box bgcolor={bgcolor}>
      <S.CommentHead>
        <UserProfile name={userId} color={'black'} iconSize={'30px'} fontsize={'15px'}/>
        <p>{date}</p>
      </S.CommentHead>
      <S.CommentBody>
        <p>{comment}</p>
      </S.CommentBody>
      <S.CommentBottom>
        <Button1 text={'수정'} height={'30px'} width={'50px'} fontsize={'15px'} />
        <Button1 text={'삭제'} color={'red'} height={'30px'} width={'50px'} fontsize={'15px'}/>
      </S.CommentBottom>
    </S.Box>
  );
}
