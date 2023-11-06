/* eslint-disable import/no-extraneous-dependencies */
import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";
const { persistAtom } = recoilPersist();

export const userState = atom({
  key: "user",
  default: {
    userId: null,
    email: "",
    name: "",
    nickname: "",
    profileImg: null,
    social: "",
  },
  effects_UNSTABLE: [persistAtom],
});
