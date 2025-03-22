import React, { useState } from 'react'
import { useSelector } from 'react-redux';
import ImageUpload from './upload/image/ImageUpload';
import './mainscreen.scss';

const MainScreen = () => {
  const user = useSelector(s=> s.user.currentUser);
  const[popup,setPopup]  = useState("none");
  return (
    <div className='mainscreen'>
      <h1>Welcome, {user?.name} </h1>
      <p className="text-gray-600 text-sm">📞 {user?.phone}</p>
      <div className='profile' onDoubleClick={()=>{
        setPopup("flex");
      }}>
        <div className='prof' onClick={()=>{

        }}>
        <img src={"./profiles/"+user?.phone+".jpg"} title={user?.name} alt='Profile Pic' width="200" 
          height="200" 
          class="rounded-lg shadow-lg" 
          loading="lazy" 
          decoding="async"
          fetchpriority="high" />
        </div>
      </div>
      
      <div className='popup' style={{display : popup}}>
          <ImageUpload/>
          <button className='close' onClick={()=>{
            setPopup("none");
          }}>Close</button>
      </div>
    </div>
  )
}

export default MainScreen;
