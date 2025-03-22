import React from 'react'
import { useSelector } from 'react-redux';
import UpdateUtensile from '../../routes/UpdateUtesile';

const UtensilePopup = ({popupRef, card}) => {
    const selectedCard = useSelector(state => state.utensile.selectedUtensile);
  return (
    <div className='popup' ref={popupRef}>
        <div className='container'>
            <UpdateUtensile popupUtensile={popupRef}/>
        </div>
    </div>
  );
}

export default UtensilePopup;
