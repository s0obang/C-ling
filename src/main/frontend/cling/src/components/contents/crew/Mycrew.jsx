import React, { useState } from 'react';
import '../../../assets/scss/contents/crew/crew.scss';
import Header from '../../Header';
import left from '../../../assets/img/crew/left.png';
import NoticeList from './NoticeList';
import Recrewting from './Recrewting';
import { Link, useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';
import List from './List';
import axios from 'axios'


const Mycrew = () => {
   
    const navigate = useNavigate();

    const handleBackClick = () => {
        navigate(-1);
    };
    const fadeIn = {
        hidden: { opacity: 0 },
        visible: { opacity: 1, transition: { duration: 1 } }
    };

    return (
        <div className='mycrew-page'>
            <Header />
            <motion.div className="wrap"
                variants={fadeIn}
                initial="hidden"
                animate="visible">
                <div className="title">
                    <img src={left} alt="뒤로가기" onClick={handleBackClick} />
                    <h1>컴퓨터공학과 제 13대 학생회</h1>
                </div>
                <NoticeList  />
                <Recrewting />
                <List />
            </motion.div>
            <div className="empty"></div>
            <div className="empty"></div>
        </div>
    );
};

export default Mycrew;
