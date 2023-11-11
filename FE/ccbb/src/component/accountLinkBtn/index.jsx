import * as S from "./style";
import AddIcon from "@mui/icons-material/Add";

export default function AccountLinkBtn({ title, onClick }) {
  return (
    //Todo: 계정연동되도록 onClick 적용하기
    <S.roundBtn onClick={onClick}>
      <AddIcon />
    </S.roundBtn>
  );
}
