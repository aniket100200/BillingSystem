import React, { useEffect, useState } from 'react'
import { useDispatch, useSelector } from 'react-redux';
import { actions } from '../state-stuff/reducer';
import axios from 'axios';
import myThunk from '../state-stuff/thunk';
import { useNavigate } from 'react-router-dom';
import '../styles/home.scss'
import SidePanel from '../components/SidePanel';
import MainScreen from '../components/MainScreen';



const Home = () => {
  const dispatch = useDispatch();
  const [userList,setUserList] = useState(0);
  const navigate = useNavigate();

  const currentUser = useSelector((s)=>s.user.currentUser);

  if(!sessionStorage.getItem("user")){
      sessionStorage.setItem("user",JSON.stringify(currentUser));
  }else if(!currentUser){
    dispatch({type:actions.currentUser,payload : {data : JSON.parse(sessionStorage.getItem("user"))}});
  }

  return (
    <div className='home'>
      <MainScreen/>
    </div>
  )
};

export default Home;
