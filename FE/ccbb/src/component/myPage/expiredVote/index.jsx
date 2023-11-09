import { Link } from "react-router-dom";
import * as S from "./style";
// import VoteRate from "../../voteBoard/voteRate";
import VoteCard from "../../voteBoard/Cardsection";

export default function ExpiredVote() {
  const dummyData = [
    {
      id: 1,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 1 Title",
      amount: 100,
    },
    {
      id: 2,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 2 Title",
      amount: 150,
    },
    {
      id: 3,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 3 Title",
      amount: 75,
    },
    {
      id: 4,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 4 Title",
      amount: 200,
    },
    {
      id: 5,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 5 Title",
      amount: 50,
    },
    {
      id: 6,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 6 Title",
      amount: 120,
    },
    {
      id: 7,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 7 Title",
      amount: 90,
    },
    {
      id: 8,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 8 Title",
      amount: 175,
    },
    {
      id: 9,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 9 Title",
      amount: 60,
    },
    {
      id: 10,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 10 Title",
      amount: 110,
    },
    {
      id: 11,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 11 Title",
      amount: 130,
    },
    {
      id: 12,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 12 Title",
      amount: 85,
    },
    {
      id: 13,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 13 Title",
      amount: 190,
    },
    {
      id: 14,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 14 Title",
      amount: 70,
    },
    {
      id: 15,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 15 Title",
      amount: 140,
    },
    {
      id: 16,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 16 Title",
      amount: 95,
    },
    {
      id: 17,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 17 Title",
      amount: 180,
    },
    {
      id: 18,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 18 Title",
      amount: 65,
    },
    {
      id: 19,
      videoUrl: "resource/LoLsample.mp4",
      title: "Video 19 Title",
      amount: 160,
    },
  ];
  return (
    <S.main>
      <Link to="/lolvote">
        <h1>누구탓이냐고</h1>
      </Link>
      {/* <S.VoteGrid>
        {dummyData.map((item) => (
          <VoteCard
            key={item.id}
            videoUrl={item.videoUrl}
            title={item.title}
            amount={item.amount}
          />
        ))}
      </S.VoteGrid> */}
    </S.main>
  );
}
