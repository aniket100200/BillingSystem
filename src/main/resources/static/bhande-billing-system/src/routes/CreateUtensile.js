import { Button, Form, Input } from 'antd'
import FormItem from 'antd/es/form/FormItem';
import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'
import '../styles/createUtensiles.scss'
import '../styles/login.scss'
import { addUtensile } from '../auth';

const CreateUtensile = ({uuid}) => {
    const navigate = useNavigate();

  const [isLoading, setIsLoading] = useState(false);

    const saveOnSuccess = (data) => {
       
        const resp = addUtensile(data);
        setIsLoading(true);

        resp.then((data)=>{
            const {success} = data;
            if(success)
                alert("added Successfully");
            setIsLoading(false);
        }).catch((err)=>{
            alert("error is there")
        });
    

    }

    return (

        <div className={"create-utensile"}>
            <button onClick={() => {
                navigate('/utensile');
            }}><span className="material-icons">arrow_back</span></button>

            <div>
                Creating an Utensile
            </div>

            <div className='form-container'>
                <Form className='form' layout='vertical' onFinish={saveOnSuccess}>
                    <Form.Item label={"Name"} rules={[{ type: "string", message: "Name is Required" }, { required: true, message: "Name is Required" }]} name={"name"}>
                        <Input placeholder='Name' />
                    </Form.Item>

                    <Form.Item label="Quantity" rules={[ { required: true, message: "Quantity is required" },{pattern:/^[1-9]\d*$/, message:"Quantity Should be either One or More"}]} name={"quantity"}>
                        <Input placeholder='Quantity' type='number' />
                    </Form.Item>


                    <Form.Item label="Price" rules={[ { required: true, message: "Price is required" },{pattern:/^[0-9]\d*$/, message:"Price Should be Positive or Zero"}]} name={"price"}>
                        <Input placeholder='Price' type='number' />
                    </Form.Item>


                    <Form.Item label="ImageUrl" rules={[ {type:"url", message:"It should be Url"},{ required: true, message: "ImageUrl is required" }]} name={"imageUrl"}>
                        <Input placeholder='ImageUrl'/>
                    </Form.Item>

                    <Button block={true} className='btn' htmlType='submit' loading={isLoading}>Submit</Button>
                </Form>
            </div>

        </div>

    )
}

export default CreateUtensile
