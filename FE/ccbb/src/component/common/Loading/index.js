import React from "react";
import SyncLoader from "react-spinners/SyncLoader";

function Loading() {
  return (
    <div>
      <div
        style={{
          position: "fixed",
          top: "50%",
          left: "50%",
          transform: "translate(-50%, -50%)",
        }}
      >
        <p>잠시만 기다려주세요</p>
        <SyncLoader color="#36d7b7" style={{ margin: 35 }} />
      </div>
    </div>
  );
}

export default Loading;
