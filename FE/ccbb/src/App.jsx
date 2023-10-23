import {Routes,Route} from "react-router-dom"
import MainPage from "./pages/mainpage"


export default function App() {
    return (
        <Routes>
            <Route path="/" element={<MainPage />}></Route>
        </Routes>
    )
}