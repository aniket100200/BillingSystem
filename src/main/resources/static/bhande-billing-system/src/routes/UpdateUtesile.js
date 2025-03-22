import { Button, Form, Input, Select } from 'antd'
import FormItem from 'antd/es/form/FormItem';
import React, { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'
import '../styles/utensiles/updateUtesnsile.scss'
import { addUtensile } from '../auth';
import axios from 'axios';
import { Option } from 'antd/es/mentions';
import { useSelector } from 'react-redux';
import {changeUtensile} from "../auth/utensiles";

const UpdateUtensile = ({uuid,popupUtensile}) => {
    const navigate = useNavigate();

    const selectedUtensile = useSelector(state => state.utensile.selectedUtensile);

  const [isLoading, setIsLoading] = useState(false);
    const[images, setImages] = useState([]);

    const [form] = Form.useForm();

    const closePopup= ()=>{
        popupUtensile.current.style.display = "none";
    }

    useEffect(()=>{
        console.log(popupUtensile);
    },[])



    const saveOnSuccess = (e) => {

        e.preventDefault();

        // console.log(e.target?.imageUrl?.value);

        const name = e.target?.name?.value;
        const quantity = e.target?.quantity?.value;
        const price = e.target?.price?.value;
        const imgUrl = e.target?.imageUrl?.value;
        const uuid = e.target?.uuid?.value;

        const data ={
            name,
            quantity,
            price,
            imageUrl : imgUrl,
        }

        const resp = changeUtensile(uuid,data);

        resp.then(res=>{
          const {success}= res;
          if(success)alert("Updated SuccesFully");
        })




    }

     useEffect(()=>{
            (async()=>{
                const resp = await axios({
                    url : '/urls.json',
                    method: "GET"
                })

                const result = resp.data;
              setImages(result.images);

            })();

        },[]);

    return (

        <div className={"create-utensile"}>
        
        <div className='form-container'>
            <form className='form' layout='vertical' onSubmit={saveOnSuccess}>

                <input placeholder='Uuid' id='uuid' hidden={true} name='uuid' value={selectedUtensile?.uuid}/>
                <label htmlFor='name'> Name:</label>
                <input placeholder='Name' id='name' name='name' defaultValue={selectedUtensile?.name}/>

                <label htmlFor='quantity'> Quantity:</label>
                <input placeholder='Quantity' id='quantity' name='quantity' defaultValue={selectedUtensile?.quantity}/>


                <label htmlFor='price'> Price:</label>
                <input placeholder='Price' id='price' name='price' defaultValue={selectedUtensile?.price}/>

                <select defaultValue={images[0]}
                        style={{width: 200}} onChange={() => {
                }} id={"imageUrl"} name={"imageUrl"}>
                    {
                        images.map(img =>
                            <option value={img.url}>{img.name}</option>)
                    }
                </select>

                <button block={true} className='btn' htmlType='submit' loading={isLoading}>Submit</button>
                
            </form>

            <button className='close-btn' onClick={closePopup}>Close</button>
        </div>

        </div>

    )
}

export default UpdateUtensile;
