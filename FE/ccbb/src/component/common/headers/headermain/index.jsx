import React, { useState, useEffect } from "react";
import Button1 from "../../buttons";
import * as S from "./style";
import GavelIcon from "@mui/icons-material/Gavel";
import UserProfile from "../../profile";
import Headernotification from "../headernotification";
import { Link, useNavigate } from "react-router-dom";
import { useRecoilValue, useSetRecoilState, useResetRecoilState } from "recoil";
import { UrlAtom } from "../../../../recoil/UrlAtom";
import { userState } from "../../../../recoil/UserAtom";
import { EventSourcePolyfill } from "event-source-polyfill";

export default function Headermain() {
  const navigate = useNavigate();
  const setToUrl = useSetRecoilState(UrlAtom);
  const user = useRecoilValue(userState);
  const resetUserState = useResetRecoilState(userState);
  const [notificationVisible, setNotificationVisible] = useState(false);
  const handleSigninClick = () => {
    //로그인페이지로 가기 전 url을 기억하자
    const toUrl = window.location.pathname;
    setToUrl(toUrl);
    navigate("/signin");
  };
  const handleLogoutClick = () => {
    localStorage.removeItem("token");
    resetUserState();
    navigate("/");
  };

  const handleNotificationButtonClick = (event) => {
    event.stopPropagation();
    setNotificationVisible((state) => !state);
  };

  useEffect(() => {
    let eventSource;
    const token = localStorage.getItem("token");

    if (token) {
      const fetchSse = async () => {
        const url = "http://localhost:8081/api/subscribe";
        const headers = {
          Authorization: `Bearer ${token}`,
          withCredentials: true,
        };

        eventSource = new EventSourcePolyfill(url, { headers });

        eventSource.onmessage = async (event) => {
          console.log("hi");
          console.log(event.data);
          // const newNotification = JSON.parse(event.data);
          // setNotifications((oldNotifications) => [
          //   ...oldNotifications,
          //   newNotification,
          // ]);
        };

        eventSource.onerror = async (event) => {
          if (!event.error.message.includes("No activity")) eventSource.close();
        };
      };
      fetchSse();
    }

    return () => {
      if (eventSource) {
        eventSource.close();
        console.log("closed");
      }
    };
  }, []);

  return (
    <S.Header>
      <S.leftmenu>
        <Link to="/">
          <h1>CC.BB</h1>
        </Link>
        <GavelIcon style={{ color: "#AC0000", paddingLeft: "10px" }} />
        <S.gamemenu>
          <Link to="/lolvote">
            <p>LoL</p>
          </Link>
          <Link to="/1">
            <p>컴포넌트</p>
          </Link>
        </S.gamemenu>
      </S.leftmenu>
      <S.rightmenu>
        <S.webmenu>
          <Link to="/free">
            <p>자유게시판</p>
          </Link>
          <Link to="/gift">
            <p>경품추첨</p>
          </Link>
          <Link to="/donate">
            <p>기부내역</p>
          </Link>
        </S.webmenu>
        {localStorage.getItem("token") ? (
          <S.usermenu>
            <Link to="/mypage">
              <UserProfile name={user.nickname} size={42} />
            </Link>
            <S.NotificationWrapper>
              <svg
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
                strokeWidth="1.5"
                stroke="currentColor"
                aria-hidden="true"
                width={32}
                cursor={"pointer"}
                onClick={handleNotificationButtonClick}
              >
                <path d="M14.857 17.082a23.848 23.848 0 005.454-1.31A8.967 8.967 0 0118 9.75v-.7V9A6 6 0 006 9v.75a8.967 8.967 0 01-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 01-5.714 0m5.714 0a3 3 0 11-5.714 0"></path>
              </svg>
              {notificationVisible && (
                <Headernotification
                  props={{
                    state: notificationVisible,
                    setState: setNotificationVisible,
                  }}
                />
              )}
            </S.NotificationWrapper>
            <Button1
              text={"로그아웃"}
              onClick={handleLogoutClick}
              width={"90px"}
              height={"30px"}
            />
          </S.usermenu>
        ) : (
          <Button1
            onClick={handleSigninClick}
            text={"로그인"}
            width={"75px"}
            height={"30px"}
          />
        )}
      </S.rightmenu>
    </S.Header>
  );
}
