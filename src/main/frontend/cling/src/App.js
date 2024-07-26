import React from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Checkschool from "./components/contents/signup/checkschool";
import Landing from './components/landing';
import Commuhome from './components/contents/community/commuhome';
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
import Test from './components/contents/matching/test';

import './assets/scss/section/base.scss'

function App() {
    return (
        <BrowserRouter>
            <Routes>
<<<<<<< HEAD
                <Route path='/' element={<Main />} />
                <Route path='/mainhome' element={<Mainhome />} />
=======
                <Route path='/' element={<Landing />} />
>>>>>>> 55a3244d7211ddbf127734c6e5219b8778d6a73f
                <Route path='/create' element={<Checkschool />} />
                <Route path='/commuhome' element={<Commuhome />} />
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
                <Route path="/test" element={<Test />} />
                
            </Routes>
        </BrowserRouter>

    );
}

export default App;
