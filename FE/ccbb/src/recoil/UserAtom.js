/* eslint-disable import/no-extraneous-dependencies */
import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";
const { persistAtom } = recoilPersist();

export const userState = atom({
  key: "user",
  default: {
    userId: 1,
    email: "",
    name: "temp",
    nickname: "temp",
    imageUrl: "",
  },
  effects_UNSTABLE: [persistAtom],
});
