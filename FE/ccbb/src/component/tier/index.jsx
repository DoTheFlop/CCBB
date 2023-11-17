import * as S from "./style";
import KeyboardDoubleArrowUpIcon from '@mui/icons-material/KeyboardDoubleArrowUp';
export default function TierImg({ tier,size }) {
    return (
        <S.Main>
            <img height={size} width={size} src={`/resource/${tier}.png`} alt="tier" />
            <KeyboardDoubleArrowUpIcon color="black"/>
        </S.Main>
    );
}
