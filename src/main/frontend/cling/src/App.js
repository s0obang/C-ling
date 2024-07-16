import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Checkschool from "./components/contents/signup/checkschool";
import Main from './components/Main';
import './assets/scss/section/base.scss'

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Main />} />
                <Route path='/create' element={<Checkschool />} />
            </Routes>
        </BrowserRouter>

    );
}

export default App;
