import { Routes, Route } from "react-router-dom";
import MainPage from "./pages/mainPage";
import ComponentPage from "./pages/componentPage";
import SigninPage from "./pages/userPages/signinPage";
import SignupPage from "./pages/userPages/siginupPage";
import MyPage from "./pages/userPages/myPage";
import InfoModifyPage from "./pages/userPages/myPage/infoModifyPage";
import BeforeModifyPage from "./pages/userPages/myPage/beforeModifyPage";
import LoLvoteboardPage from "./pages/boardPages/lolvoteBoard";
import FreeboardPage from "./pages/boardPages/freeBoard";
import GiftPage from "./pages/giftPage";
import DonatePage from "./pages/donatePage";
import KakaoLoginPage from "./pages/userPages/kakaoLoginPage";
import { ThemeProvider } from "styled-components";
import { theme } from "./styles/Theme";
import Headermain from "./component/common/headers/headermain";
import * as S from "./style";
import LoLvoteDetailPage from "./pages/boardPages/lolvoteBoard/lolvoteDetail";
import LoLvoteCreatePage from "./pages/boardPages/lolvoteBoard/lolvoteCreate";
import PaymentSuccessPage from "./pages/userPages/paymentSuccess";

export default function App() {
  return (
    <ThemeProvider theme={theme}>
      <S.main>
        <Headermain />
        <Routes>
          <Route path="/" element={<MainPage />}></Route>
          <Route path="/1" element={<ComponentPage />}></Route>
          <Route path="/signin" element={<SigninPage />}></Route>
          <Route path="/signup" element={<SignupPage />}></Route>
          <Route path="/mypage" element={<MyPage />}></Route>
          <Route path="/mypage/pwcheck" element={<BeforeModifyPage />} />
          <Route path="/mypage/modify" element={<InfoModifyPage />} />
          <Route path="/payment-success"element={<PaymentSuccessPage/>}></Route>

          <Route path="/lolvote" element={<LoLvoteboardPage />}></Route>
          <Route path="/statistic" element={<statisticPage />}></Route>
          <Route
            path="/lolvote/:postId"
            element={<LoLvoteDetailPage />}
          ></Route>
          <Route path="/lolvote/create" element={<LoLvoteCreatePage />}></Route>

          <Route path="/free" element={<FreeboardPage />}></Route>
          <Route path="/gift" element={<GiftPage />}></Route>
          <Route path="/donate" element={<DonatePage />}></Route>
          <Route path="/kakaologin/:token" element={<KakaoLoginPage />}></Route>
        </Routes>
      </S.main>
    </ThemeProvider>
  );
}
