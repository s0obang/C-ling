import React from "react";
import '../../../assets/scss/contents/my/myhome.scss';
import Header from '../../Header';
import Myprofil from "./myprofil";
import LinkedCrystal from "./linkedCrystal";
import WantConnect from "./wantConnect";

const Myhome = () => {


    return (
        <div className="myhome">
            <Header />
            
            <div className='myprofil'>

                <Myprofil  />
            </div>
            <div>
                <span><LinkedCrystal /></span>
            </div>
            <div>
                <span><WantConnect /></span>
            </div>
        </div>
    );
}

export default Myhome;
