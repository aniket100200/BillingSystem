import React, { useEffect } from 'react'
import SidePanel from '../components/SidePanel'
import '../styles/home.scss';
import '../components/styles/utensiles.scss'
import {Button} from "antd";
import { useNavigate } from 'react-router-dom';
import ShowUtensiles from './ShowUtensiles';
import { getUtensiles } from '../auth';
import { useDispatch } from 'react-redux';
import { utensileActions } from '../state-stuff/utensilereducer';

const Utensiles = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(()=>{
    const resp = getUtensiles();
    resp.then(data1=>{
      const {success, data} = data1;
      if(success)
        dispatch({type : utensileActions.setUtensiles, payload : {data : data}});
      
    })
  });
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
