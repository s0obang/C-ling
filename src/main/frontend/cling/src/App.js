import React from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Checkschool from "./components/contents/signup/checkschool";
import Commuhome from './components/contents/community/commuhome';
import Myhome from "./components/contents/my/myhome";
import Badgemodal from "./components/contents/my/badgemodal";
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
                <Route path='/commuhome' element={<Commuhome />} />
                <Route path='/badgemodal' element={<Badgemodal />} />
                <Route path='/myhome' element={<Myhome />} />
                <Route path='/create/info' element={<Create />} />
                <Route path='/matching' element={<Matching />} />
                
            </Routes>
        </BrowserRouter>

    );
}

export default App;
