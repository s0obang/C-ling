import React, { useEffect } from 'react';
import '../../../assets/scss/contents/matching/same_major.scss';
import { Link } from 'react-router-dom';
import { motion, useAnimation } from 'framer-motion';
import { useInView } from 'react-intersection-observer';
import Bubble1 from '../../../assets/img/matching/speech-bubble1.png';
import Bubble2 from '../../../assets/img/matching/speech-bubble2.png';
import Ex from '../../../assets/img/eximg.png';
import Header from '../../Header';

const Samemajor = () => {
    const containerVariants = {
        hidden: {},
        visible: {
            transition: {
                staggerChildren: 0.8,
                duration: 1
            }
        }
    };
    
    const profileVariants = {
        hidden: { opacity: 0, y: 20 },
        visible: { opacity: 1, y: 0, transition: { duration: 0.6 } }
    };

    
    const containerControls = useAnimation();
    const [containerRef, inView] = useInView({ triggerOnce: true, threshold: 0.1 });

    useEffect(() => {
        if (inView) {
            containerControls.start("visible");
        }
    }, [containerControls, inView]);

    return (
        <div className='samemajor'>
            <Header />
            <div>
                <h1 className="text">크링된 수정이를 확인해보세요</h1>
            </div>
            <motion.div 
                className='content' 
                ref={containerRef} 
                variants={containerVariants}
                initial="hidden"
                animate={containerControls}>
                <motion.div className="profileimg profileimg1"
                    variants={profileVariants}>
                    <Link to="/matchprofile" className="profile-link">
                        <img src={Ex} alt="프로필사진" />
                        <div className="bubbleimg">
                            <img src={Bubble1} alt="말풍선" />
                            <div className="bubble-text">
                                20231139 박시현입니다.
                            </div>
                        </div>
                    </Link>
                </motion.div>

                <motion.div className="profileimg profileimg2"
                    variants={profileVariants}>
                    <Link to="/matchprofile" className="profile-link">
                        <img src={Ex} alt="프로필사진" />
                        <div className="bubbleimg">
                            <img src={Bubble2} alt="말풍선" />
                            <div className="bubble-text">
                                20051139 사쿠야입니다.
                            </div>
                        </div>
                    </Link>
                </motion.div>

                <motion.div className="profileimg profileimg3"
                    variants={profileVariants}>
                    <Link to="/matchprofile" className="profile-link">
                        <img src={Ex} alt="프로필사진" />
                        <div className="bubbleimg">
                            <img src={Bubble1} alt="말풍선" />
                            <div className="bubble-text">
                                20181139 떵쿠시입니다.
                            </div>
                        </div>
                    </Link>
                </motion.div>
            </motion.div>
        </div>
    );
};

export default Samemajor;
