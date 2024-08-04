import React, { useEffect } from 'react';
import Header from '../../Header';
import '../../../assets/scss/contents/matching/other_major.scss';
import { Link } from 'react-router-dom'
import { motion, useAnimation } from 'framer-motion';
import { useInView } from 'react-intersection-observer';
import Bubble1 from '../../../assets/img/matching/speech-bubble1.png';
import Bubble2 from '../../../assets/img/matching/speech-bubble2.png';
import SmallBubble1 from '../../../assets/img/matching/small-speech-bubble1.png';
import SmallBubble2 from '../../../assets/img/matching/small-speech-bubble2.png';
import Ex from '../../../assets/img/eximg.png';

const OtherMajor = () => {
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
        <div className='othermajor'>
            <Header />
            <div>
                <h1 className="text">크링된 다른 과 수정이를 확인해보세요</h1>
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
                                20230039 고양이입니다.
                            </div>
                        </div>
                        <div className="smallbubbleimg">
                            <img src={SmallBubble1} alt="말풍선" />
                            <div className="bubble-text">
                                실용음학과
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
                                20235539 시온입니다.
                            </div>
                        </div>
                        <div className="smallbubbleimg">
                            <img src={SmallBubble2} alt="말풍선" />
                            <div className="bubble-text">
                                의상 디자인학과
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
                                20234439 찐떵깨입니다.
                            </div>
                        </div>
                        <div className="smallbubbleimg">
                            <img src={SmallBubble1} alt="말풍선" />
                            <div className="bubble-text">
                                식품공학과
                            </div>
                        </div>
                    </Link>
                </motion.div>
            </motion.div>
        </div>
    )
}

export default OtherMajor;
