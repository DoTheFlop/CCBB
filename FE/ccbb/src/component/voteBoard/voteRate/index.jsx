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
  const totalVote = cnt1 + cnt2;
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
            label += totalVote ? context.raw : 0;
            return label;
          },
        },
      },
    },
    layout: {
      padding: {
        top: 0,
        bottom: 0,
      },
    },

    responsive: true,
    aspectRatio: 10,
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
        data: [totalVote ? cnt1 : 1],
        borderRadius: 10,
        borderSkipped: "middle",
        barThickness: 20,
      },
      {
        label: nickname2,
        backgroundColor: "#ff5964",
        data: [totalVote ? cnt2 : 1],
        borderRadius: 10,
        borderSkipped: "middle",
        barThickness: 20,
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
      <div className="bar-wrapper">
        <Bar options={options} data={data} style={{ width: "100%" }} />
      </div>
      <UserProfile
        name={nickname2}
        color="black"
        direction="reverse"
        imgUrl={`${process.env.REACT_APP_BASE_SERVER}profileimg/${userId2}`}
      />
    </S.main>
  );
}
