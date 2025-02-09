import React, { useEffect } from 'react'
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

const PageNotFound = () => {
   const isUserLoggedIn =  useSelector(state => state.isUserLoggedIn);
   const navigate = useNavigate();
  useEffect(()=>{
    if(isUserLoggedIn) navigate("/home");
    else navigate("/login");
  },isUserLoggedIn);
  return (
    <div>
        Page Not Found<div></div>
    
    </div>
  )
}

export default PageNotFound;
