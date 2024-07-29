import React from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Checkschool from "./components/contents/signup/checkschool";
import Landing from './components/landing';
import NoticeWrite from './components/contents/community/noticeWrite';
import Myhome from "./components/contents/my/myhome";
import BadgeRequest from "./components/contents/my/badgeRequest";
import BadgeManage from "./components/contents/my/badgeManage";
import Main from './components/Main';
import Mainhome from './components/contents/homes/mainhome';
import NoticePage from './components/contents/homes/noticePage';
import Create from './components/contents/signup/create';
import NoMatching from './components/contents/matching/no_matches';
import MatchingInfo from './components/contents/matching/match_info';
import Samemajor from './components/contents/matching/samemajor';
import Othermajor from './components/contents/matching/othermajor';
import Match from './components/contents/matching/match';
import Matchprofile from './components/contents/matching/match_profile';
import Chat from './components/contents/chat/chat';

import './assets/scss/section/base.scss'

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Main />} />
                <Route path='/mainhome' element={<Mainhome />} />
                <Route path='/notice' element={<NoticePage />} />
                <Route path='/' element={<Main />} />
                <Route path='/' element={<Landing />} />
                <Route path='/create' element={<Checkschool />} />
                <Route path='/noticeWrite' element={<NoticeWrite />} />
                <Route path='/badgeRequest' element={<BadgeRequest />} />
                <Route path='/badgeManage' element={<BadgeManage />} />
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
