import React from 'react'
import { useDispatch } from 'react-redux';
import { actions } from '../state-stuff/reducer';

const Home = () => {
  const dispatch = useDispatch();
  const LogOut = ()=>{
    localStorage.clear("token");

    dispatch(actions.NotLoggedIn);
  }
  return (
    <div>
      <button onClick={LogOut}>Log out</button>
    </div>
  )
};

export default Home;
