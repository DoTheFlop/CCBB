/* eslint-disable import/no-extraneous-dependencies */
import { atom } from "recoil";
import { recoilPersist } from "recoil-persist";

const { persistAtom } = recoilPersist();

export const UrlAtom = atom({
  key: "toUrl",
  default: "",
  effects_UNSTABLE: [persistAtom],
});
