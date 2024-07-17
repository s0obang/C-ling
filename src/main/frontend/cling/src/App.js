import React from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Checkschool from "./components/contents/signup/checkschool";
import Main from './components/Main';
import Create from './components/contents/signup/create';
import Matching from './components/contents/matching/no_matches';

import './assets/scss/section/base.scss'

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Main />} />
                <Route path='/create' element={<Checkschool />} />
                <Route path='/create/info' element={<Create />} />
                <Route path='/matching' element={<Matching />} />
                
            </Routes>
        </BrowserRouter>

    );
}

export default App;
