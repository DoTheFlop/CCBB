import * as S from "./style";
import UserProfile from "../../common/profile";
import {
  CategoryScale,
  LinearScale,
  BarElement,
  Chart,
  Tooltip,
} from "chart.js";
import ChartjsPluginStacked100 from "chartjs-plugin-stacked100";
import { Bar } from "react-chartjs-2";

Chart.register(
  CategoryScale,
  LinearScale,
  BarElement,
  ChartjsPluginStacked100,
  Tooltip
);
export default function VoteRate({
  cnt1,
  cnt2,
  nickname1,
  nickname2,
  userId1,
  userId2,
}) {
  const options = {
    indexAxis: "y",
    plugins: {
      stacked100: {
        enable: true,
        replaceTooltipLabel: false,
      },
      tooltip: {
        enabled: true,
        mode: "nearest",
        xAlign: "left",
        yAlign: "bottom",
        callbacks: {
          label: function (context) {
            var label = context.dataset.label || "";
            if (label) {
              label += ": ";
            }
            label += context.raw;
            return label;
          },
        },
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
        label: nickname1,
        backgroundColor: "#7390ff",
        data: [cnt1],
        borderRadius: 10,
        borderSkipped: "middle",
        barThickness: 10,
      },
      {
        label: nickname2,
        backgroundColor: "#ff5964",
        data: [cnt2],
        borderRadius: 10,
        borderSkipped: "middle",
        barThickness: 10,
      },
    ],
  };
  return (
    <S.main>
      <UserProfile
        name={nickname1}
        color="black"
        imgUrl={`${process.env.REACT_APP_BASE_SERVER}profileimg/${userId1}`}
      />
      <Bar options={options} data={data} />
      <UserProfile
        name={nickname2}
        color="black"
        direction="reverse"
        imgUrl={`${process.env.REACT_APP_BASE_SERVER}profileimg/${userId2}`}
      />
    </S.main>
  );
}
