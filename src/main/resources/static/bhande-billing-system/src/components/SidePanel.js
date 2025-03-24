
import React from 'react'
import {Button} from "antd";
import SidePanelTabs from './SidePanelTabs';
import { useNavigate } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { actions } from '../state-stuff/reducer';

const SidePanel = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  const LogOut = ()=>{
    sessionStorage.clear("token");
    dispatch({type : actions.NotLoggedIn});
    navigate('/');
  
  }
  return (
    <div className='sidepanel'>
        <div className='top-bar'>
            <div className='logo' onClick={()=>{navigate('/home')}}>UBS</div>
            <SidePanelTabs/>
        </div>
        <Button onClick={LogOut} className='logout'> <span className="material-icons">logout</span>LogOut</Button>
    </div>
  )
}

export default SidePanel;
