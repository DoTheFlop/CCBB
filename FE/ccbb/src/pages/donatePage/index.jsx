import * as S from "./style";
import Headermenu from "../../component/common/headers/headermenu";
import SearchInput from "../../component/common/inputs/searchInput";

export default function DonatePage({ amount = 1000000 }) {
  const formattedAmount = amount.toLocaleString();

  const handleClickSearch = () => {
    console.log("clicked search");
  };

  const dummy = [
    {
      title: "2023.10.30일 기부현황",
      date: "2023.10.30",
    },
    {
      title: "2023.10.30일 기부현황",
      date: "2023.10.30",
    },
    {
      title: "2023.10.30일 기부현황",
      date: "2023.10.30",
    },
    {
      title: "2023.10.30일 기부현황",
      date: "2023.10.30",
    },
  ];
  return (
    <S.main>
      <Headermenu title="기부내역"></Headermenu>
      <S.donateSection>
        <h2>누적 기부액</h2>
        <S.accumulatedDonation>
          <h1>{formattedAmount} 원</h1>
        </S.accumulatedDonation>
        <SearchInput width={"300px"} onClickIcon={handleClickSearch} />
        <hr />
        {dummy.map((item, index) => {
          return (
            <S.donatePreview key={index}>
              <p>{item.title}</p>
              <p>{item.date}</p>
            </S.donatePreview>
          );
        })}
      </S.donateSection>
    </S.main>
  );
}
