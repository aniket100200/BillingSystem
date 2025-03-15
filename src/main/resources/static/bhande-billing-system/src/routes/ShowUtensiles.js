import axios from 'axios';
import React, { useEffect, useRef, useState } from 'react'
import { baseURL } from '../auth';
import UtensileCard from '../components/UtensileCard';
import '../styles/utesnsiles.scss'
import { useDispatch, useSelector } from 'react-redux';
import UtensilePopup from '../components/utesnsiles/UtensilePopup';
import { utensileActions } from '../state-stuff/utensilereducer';

const ShowUtensiles = () => {

    const dispatch = useDispatch();
   
    const utensile = useSelector(state => state.utensile.utensiles);
   
    const popupUtensile = useRef(null);

    const headers = [];

    if (utensile) {
        for (let val in utensile[0]) {
            headers.push(val);
        }
    }

    

    const onCardDoubleClick = (uuid)=>{
    
        
        var currentUtensile = utensile.filter((u)=>{
            return u.uuid == uuid 
        });
        
        currentUtensile = currentUtensile[0];


        dispatch({type : utensileActions.selectedUtensile, payload : {card :currentUtensile}});

        popupUtensile.current.style.display = "flex";
    
        
    }

    useEffect(()=>{
        
    });
    

    return (
        <div className='show-utensiles cards'>
           {
            utensile.map((card,idx)=>{
                return  <UtensileCard card={ card} key={idx} uuid={card.uuid} onCardDoubleClick={onCardDoubleClick}/>
            })
           }

           <UtensilePopup popupRef={popupUtensile}  />
        </div>
    )
}

export default ShowUtensiles
