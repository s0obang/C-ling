import React from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Checkschool from "./components/contents/user/checkschool";
import Landing from './components/landing';
import NoticeWrite from './components/contents/crew/noticeWrite';
import NoticeWrites from './components/contents/homes/noticeWrites';
import NoticeOpen from './components/contents/homes/noticeOpen';
import Myhome from "./components/contents/my/myhome";
import BadgeRequest from "./components/contents/my/badgeRequest";
import BadgeManage from "./components/contents/my/badgeManage";
import Main from './components/Main';
import Mainhome from './components/contents/homes/mainhome';
import NoticePage from './components/contents/homes/noticePage';
import Create from './components/contents/user/create';
import NoMatching from './components/contents/matching/no_matches';
import MatchingInfo from './components/contents/matching/match_info';
import Samemajor from './components/contents/matching/samemajor';
import Othermajor from './components/contents/matching/othermajor';
import Match from './components/contents/matching/match';
import Matchprofile from './components/contents/matching/match_profile';
import Loading from './components/contents/matching/loading';
import Chat from './components/contents/chat/chat';
import Login from './components/contents/user/Login';
import Findpw from './components/contents/user/Findpw';
import Newpw from './components/contents/user/Newpw';
import Crew from './components/contents/crew/Crew';
import Mycrew from './components/contents/crew/Mycrew';   
import NoticeDetail from './components/contents/crew/NoticeDetail';  
import PassList from './components/contents/crew/PassList';



import './assets/scss/section/base.scss'

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Landing />} />
                <Route path='/mainhome' element={<Mainhome />} />
                <Route path='/notice' element={<NoticePage />} />
                <Route path='/login/findpw' element = {<Findpw />} />
                <Route path='/login/newpw' element = {<Newpw />} />
                <Route path='/crew' element = {<Crew />} />
                <Route path='/mycrew' element = {<Mycrew />} />
                <Route path='/mycrew/PassList' element = {<PassList />} />
                <Route path='/mycrew/:crewId' element={<Mycrew />} />  
                <Route path='/notice/:noticeId' element={<NoticeDetail />} />   

                <Route path='/create' element={<Checkschool />} />
                <Route path='/login' element={<Login />} />
                <Route path='/noticeWrite' element={<NoticeWrite />} />
                <Route path='/noticeWrites' element={<NoticeWrites />} />
                <Route path='/noticeOpen' element={<NoticeOpen />} />
                <Route path='/badgeRequest' element={<BadgeRequest />} />
                <Route path='/badgeManage' element={<BadgeManage />} />
                <Route path='/myhome' element={<Myhome />} />
                <Route path='/create/info' element={<Create />} />
                <Route path='/nomatches' element={<NoMatching />} />
                <Route path='/nomatches/info' element={<MatchingInfo />} />
                <Route path='/samemajor' element={<Samemajor />} />
                <Route path='/othermajor' element={<Othermajor />} />
                <Route path='/match' element={<Match />} />
                <Route path="/matchprofile" element={<Matchprofile />} />
                <Route path="/loading" element={<Loading />} />
                <Route path="/chat/:userId" element={<Chat />} />
                <Route path="/chat/:currentUserId/:otherUserId" element={<Chat />} />

               
                
                
            </Routes>
        </BrowserRouter>

    );
}

export default App;
