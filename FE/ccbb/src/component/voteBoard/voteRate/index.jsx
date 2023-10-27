import * as S from "./style";
import UserProfile from "../../common/profile";
import { CategoryScale, LinearScale, BarElement, Chart } from "chart.js";
import ChartjsPluginStacked100 from "chartjs-plugin-stacked100";
import { Bar } from "react-chartjs-2";

Chart.register(CategoryScale, LinearScale, BarElement, ChartjsPluginStacked100);
export default function VoteRate() {
  const options = {
    indexAxis: "y",
    plugins: {
      stacked100: {
        enable: true,
        replaceTooltipLabel: false,
      },
    },
    responsive: false,
    // aspectRatio: 24,
    // maintainAspectRatio: false,
    // layout: {
    //   padding: 0,
    // },
    scales: {
      x: {
        stacked: true,
        display: false,
      },
      y: {
        stacked: true,
        display: false,
      },
    },
  };
  const data = {
    labels: [""],
    datasets: [
      {
        label: "좋아요",
        backgroundColor: "#7390ff",
        data: [10],
        borderRadius: 10,
        borderSkipped: "middle",
        barThickness: 10,
      },
      {
        label: "싫어요",
        backgroundColor: "#ff5964",
        data: [20],
        borderRadius: 10,
        borderSkipped: "middle",
        barThickness: 10,
      },
    ],
  };
  return (
    <S.main>
      <UserProfile name={"나"} color="black" />
      <Bar options={options} data={data} />
      <UserProfile name={"상대"} color="black" direction="reverse" />
    </S.main>
  );
}
