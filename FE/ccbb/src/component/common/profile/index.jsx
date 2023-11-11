import { ccbbApi } from "../../../api/ccbbApi";
import * as S from "./style";
import React, { useEffect } from "react";

export default function UserProfile({
  name,
  color = "white",
  direction = "",
  size = 50,
  imgUrl,
}) {
  // const iconSize = "50px";
  const [profileImg, setProfileImg] = React.useState(imgUrl);

  useEffect(() => {
    const fetchImage = () => {
      ccbbApi
        .get(imgUrl)
        .then((res) => {
          if (res.data === "Image not found") {
            // console.log(res.data);
            setProfileImg("/resource/LoL.png");
          } else {
            setProfileImg(imgUrl);
          }
        })
        .catch((e) => console.log(e));
    };

    fetchImage();
  }, [imgUrl]);

  return (
    <S.Profile direction={direction}>
      <S.Img
        src={profileImg}
        alt="profile-img"
        style={{ width: size, height: size }}
      />
      <S.Name color={color} size={size}>
        {name}
      </S.Name>
    </S.Profile>
  );
}
