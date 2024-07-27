import React from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Checkschool from "./components/contents/signup/checkschool";
import Landing from './components/landing';

import NoticeWrite from './components/contents/community/noticeWrite';
import Myhome from "./components/contents/my/myhome";
import Badgemodal from "./components/contents/my/badgemodal";
import Main from './components/Main';
import Mainhome from './components/contents/homes/mainhome';
import Create from './components/contents/signup/create';
import NoMatching from './components/contents/matching/no_matches';
import MatchingInfo from './components/contents/matching/match_info';
import Samemajor from './components/contents/matching/samemajor';
import Othermajor from './components/contents/matching/othermajor';
import Match from './components/contents/matching/match';
import Matchprofile from './components/contents/matching/match_profile';
import Chat from './components/contents/chat/chat';
import Login from './components/contents/login/Login';


import './assets/scss/section/base.scss'

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Landing />} />
                <Route path='/create' element={<Checkschool />} />
                <Route path='/login' element={<Login />} />
                
                <Route path='/noticeWrite' element={<NoticeWrite />} />
                <Route path='/badgemodal' element={<Badgemodal />} />
                <Route path='/myhome' element={<Myhome />} />
                <Route path='/create/info' element={<Create />} />
                <Route path='/nomatches' element={<NoMatching />} />
                <Route path='/nomatches/info' element={<MatchingInfo />} />
                <Route path='/samemajor' element={<Samemajor />} />
                <Route path='/othermajor' element={<Othermajor />} />
                <Route path='/match' element={<Match />} />
                <Route path="/matchprofile/" element={<Matchprofile />} />
                <Route path="/chat/:userId" element={<Chat />} />
               
                
            </Routes>
        </BrowserRouter>

    );
}

export default App;
