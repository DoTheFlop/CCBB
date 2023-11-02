import * as S from "./style";
import SearchIcon from "@mui/icons-material/Search";

export default function SearchInput({ width, onClickIcon }) {
  return (
    <S.search className="search" width={width}>
      <S.input placeholder="검색"></S.input>
      <SearchIcon onClick={onClickIcon} />
    </S.search>
  );
}
