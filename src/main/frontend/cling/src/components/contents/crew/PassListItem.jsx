import React, { useState } from 'react';
import '../../../assets/scss/contents/crew/passlist.scss';
import { motion } from 'framer-motion';

const PassListItem = ({ studentId, name, fileName }) => {
    const [pass, setPass] = useState(null);

    const passClick = () => {
        if (pass === null) {
            setPass(true);
        } else if (pass === true) {
            setPass(false);
        } else {
            setPass(null);
        }
    };

    const getClassName = () => {
        if (pass === null) {
            return 'noselect';
        } else if (pass === true) {
            return 'pass';
        } else {
            return 'nonpass';
        }
    };

    const getPassText = () => {
        if (pass === null) {
            return '미정';
        } else if (pass === true) {
            return '합격';
        } else {
            return '불합격';
        }
    };

    return (
        <div className="row">
            <div className='passlist-info'>{studentId} {name}</div>
            <div className='passlist-file'> • <div className="file">{fileName}</div></div>
            <div className='passlist-select'>
                <motion.button
                    className={`select-pass ${getClassName()}`}
                    onClick={passClick}
                    whileTap={{ scale: 1.3}}
                >
                    {getPassText()}
                </motion.button>
            </div>
        </div>
    );
};

export default PassListItem;
