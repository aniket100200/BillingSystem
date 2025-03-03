import React from 'react'
import SidePanel from '../components/SidePanel'
import '../styles/home.scss';
import '../components/styles/utensiles.scss'
import {Button} from "antd";
import { useNavigate } from 'react-router-dom';
import ShowUtensiles from './ShowUtensiles';

const Utensiles = () => {
  const navigate = useNavigate();
  return (
    <div className='home'>
        <div>
            <div className={"edit-utensiles"}>
                <Button className={"btn"} onClick={()=>{
                    navigate("/create/utensile");
                }}>Create Utensile</Button>
              
                <ShowUtensiles/>
              
            </div>
        </div>
    </div>
  )
}

export default Utensiles
