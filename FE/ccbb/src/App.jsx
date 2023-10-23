import {Routes,Route} from "react-router-dom"
import MainPage from "./pages/mainPage"
import ComponentPage from "./pages/componentPage"


export default function App() {
    return (
        <Routes>
            <Route path="/" element={<MainPage />}></Route>
            <Route path="/1" element={<ComponentPage />}></Route>
        </Routes>
    )
}