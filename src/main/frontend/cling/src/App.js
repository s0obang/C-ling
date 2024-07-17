import React from 'react'
import { BrowserRouter, Route, Router, Routes } from 'react-router-dom'
import Checkschool from "./components/contents/signup/checkschool";
import Main from './components/Main';
import Create from './components/contents/signup/create';

import './assets/scss/section/base.scss'

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path='/' element={<Main />} />
                <Route path='/create' element={<Checkschool />} />
                <Route path='/create/info' element={<Create />} />
                
            </Routes>
        </BrowserRouter>

    );
}

export default App;
