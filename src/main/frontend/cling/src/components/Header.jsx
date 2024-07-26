import React from 'react'
import LOGO from '../assets/img/logo.png'
import '../assets/scss/section/Header.scss'
import '../assets/scss/section/base.scss'
import { Link } from 'react-router-dom'

const Header = () => {
  return (
    <div className='header'>
      <div className="logo">
        <img src={LOGO} alt="logo"  />
      </div>
      <div className="nav">
        <button className='Home'>HOME</button>
        <Link to= '/login'><button className='login nodisplay'>LOGIN</button></Link>
        <button className='Matching'>MATCHING</button>
        <button className='Community'>COMMUNITY</button>
        <button className='My'>MY</button>
      </div>
    </div>
  )
}

export default Header
