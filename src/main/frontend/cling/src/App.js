import React from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Checkschool from "./components/contents/signup/checkschool";
import Commuhome from './components/contents/community/commuhome';
import NoticeWrite from './components/contents/community/noticeWrite';
import Myhome from "./components/contents/my/myhome";
import Badgemodal from "./components/contents/my/badgemodal";
import Main from './components/Main';
import Mainhome from './components/contents/homes/mainhome';
import Create from './components/contents/signup/create';
import NoMatching from './components/contents/matching/no_matches';
import MatchingInfo from './components/contents/matching/match_info';
import Samemajor from './components/contents/matching/check_samemajor';
import Othermajor from './components/contents/matching/check_othermajor';

import './assets/scss/section/base.scss'

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Main />} />
                <Route path='/mainhome' element={<Mainhome />} />
                <Route path='/create' element={<Checkschool />} />
                <Route path='/commuhome' element={<Commuhome />} />
                <Route path='/noticeWrite' element={<NoticeWrite />} />
                <Route path='/badgemodal' element={<Badgemodal />} />
                <Route path='/myhome' element={<Myhome />} />
                <Route path='/create/info' element={<Create />} />
                <Route path='/nomatches' element={<NoMatching />} />
                <Route path='/nomatches/info' element={<MatchingInfo />} />
                <Route path='/nomatches/info/samemajor' element={<Samemajor />} />
                <Route path='/nomatches/othermajor' element={<Othermajor />} />
                
            </Routes>
        </BrowserRouter>

    );
}

export default App;
