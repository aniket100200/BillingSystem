import React, { useState } from 'react'
import '../styles/card.scss'
import { Button } from 'antd';

const UtensileCard = ({ card, uuid, onCardDoubleClick }) => {
    const[quantity,setQuantity] = useState(0);
    
    if (!card) return <h1> Loading</h1>

    const increase = ()=>{
        setQuantity(prev=>{
            if(prev+1<= card.quantity)
                return prev+1;
            return prev;
        });
    }
    const decrease = ()=>{
        setQuantity(prev=>{
            if(prev)return prev-1;
            return prev;
        });
    }

    
    return (
        <div className='card' >
            <div className='img' onDoubleClick={()=>{
                onCardDoubleClick(card.uuid);
            }}>
                <img src={card.imageURL} alt={card.name} key={card.uuid}/>
            </div>
            <div className='details'>
                <div className='title'>{card.name}</div>
                <div className='price'> RS.{card.price}</div>
                <div className=''>
                    {
                        quantity ? <><Button onClick={decrease}>-</Button>{quantity}<Button onClick={increase}>+</Button></>
                        : <Button onClick={()=>setQuantity(1)}>Add to Cart</Button>
                    }
                </div>
            </div>
        </div>
    )
}

export default UtensileCard
