import React, { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux';
import { actions } from '../state-stuff/reducer';
import axios from 'axios';
import myThunk from '../state-stuff/thunk';
import { useNavigate } from 'react-router-dom';
import '../styles/home.scss'
import SidePanel from '../components/SidePanel';
import MainScreen from '../components/MainScreen';



const Home = () => {
  const dispatch = useDispatch();
  const [userList,setUserList] = useState([]);
  const navigate = useNavigate();
  

  return (
    <div className='home'>
      <MainScreen/>
    </div>
  )
};

export default Home;
