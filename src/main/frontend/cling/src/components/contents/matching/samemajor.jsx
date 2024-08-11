import React, { useEffect } from 'react';
import '../../../assets/scss/contents/matching/same_major.scss';
import { Link, useLocation } from 'react-router-dom';
import { motion, useAnimation } from 'framer-motion';
import { useInView } from 'react-intersection-observer';
import Header from '../../Header';
import Bubble1 from '../../../assets/img/matching/speech-bubble1.png';
import Bubble2 from '../../../assets/img/matching/speech-bubble2.png';

const Samemajor = () => {
    const location = useLocation();
    const profiles = location.state?.profiles || [];

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
                animate={containerControls}
            >
                {profiles.map((profile, index) => (
                    <motion.div
                        key={profile.studentId}
                        className={`profileimg profileimg${index + 1}`}
                        variants={profileVariants}
                    >
                        <Link 
                            to={`/matchprofile/${profile.studentId}`} 
                            className="profile-link"
                            state={{ profiles }}
                        >
                            <img src={profile.profileImageUrl} alt="프로필사진" />
                            <div className="bubbleimg">
                                <img src={index % 2 === 0 ? Bubble1 : Bubble2} alt="말풍선" />
                                <div className="bubble-text">
                                    {profile.studentId} {profile.name}입니다.
                                </div>
                            </div>
                        </Link>
                    </motion.div>
                ))}
            </motion.div>
        </div>
    );
};

export default Samemajor;