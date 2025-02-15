import React, { useEffect, useState } from 'react'
import { useDispatch } from 'react-redux';
import { actions } from '../state-stuff/reducer';
import axios from 'axios';
import myThunk from '../state-stuff/thunk';
import { useNavigate } from 'react-router-dom';



const Home = () => {
  const dispatch = useDispatch();
  const [userList,setUserList] = useState([]);
  const navigate = useNavigate();
  const LogOut = ()=>{
    localStorage.clear("token");
    dispatch({type : actions.NotLoggedIn});
    navigate('/');
  
  }

  useEffect(()=>{

   dispatch(myThunk);
    
  },[]);
  return (
    <div>
      <button onClick={LogOut}>Log out</button>
    </div>
  )
};

export default Home;
