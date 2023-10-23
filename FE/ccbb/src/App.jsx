import {Routes,Route} from "react-router-dom"
import MainPage from "./pages"


export default function App() {
    return (
        <Routes>
            <Route path="/" element={<MainPage />}></Route>
        </Routes>
    )
}