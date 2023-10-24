import {Routes,Route} from "react-router-dom"
import MainPage from "./pages/mainPage"
import ComponentPage from "./pages/componentPage"
import SigninPage from "./pages/userPages/signinPage"
import SignupPage from "./pages/userPages/siginupPage"
import LoLvoteboardPage from "./pages/boardPages/lolvoteBoard"
import FreeboardPage from "./pages/boardPages/freeBoard"
import GiftPage from "./pages/giftPage"
import DonatePage from "./pages/donatePage"


export default function App() {
    return (
        <Routes>
            <Route path="/" element={<MainPage />}></Route>
            <Route path="/1" element={<ComponentPage />}></Route>
            <Route path="/signin" element={<SigninPage />}></Route>
            <Route path="/signup" element={<SignupPage />}></Route>
            <Route path="/lolvote" element={<LoLvoteboardPage />}></Route>
            <Route path="/free" element={<FreeboardPage />}></Route>
            <Route path="/gift" element={<GiftPage />}></Route>
            <Route path="/donate" element={<DonatePage />}></Route>
        </Routes>
    )
}