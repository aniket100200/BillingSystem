import React, { useState } from 'react';
import '../styles/login.scss';
import {Button, Form, Input, notification} from "antd";
import {Link, useNavigate} from "react-router-dom";
import {login} from "../auth/index";
import { useDispatch } from 'react-redux';
import { actions } from '../state-stuff/reducer';


const Login = () => {
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();

  const dispatch  = useDispatch();

  const onLogin = (data) => {
    //show a notification

    const resp =login(data); //this is promise
    setIsLoading(true);
    resp.then((data)=>{
      const{success,user} =data;

      if(success)
        {
          dispatch({ type: actions.LoggedIn});
          
          dispatch({type:actions.currentUser,payload:{data : user}});

          navigate("/home");
        }
      setIsLoading(false);
    })
    
  }

  return (
    <div className='form-container'>
      <Form className={"form"} layout="vertical" onFinish={onLogin}>
        <Form.Item label={"email"} rules={[{type: "email", message: "Email is required"}, {
          required: true,
          message: "Email is required"
        }]} name={"email"}>
          <Input placeholder={"Email"}/>
        </Form.Item>
        <Form.Item label={"password"} rules={[{required: true, message: "Passwords is required"}]} name={"password"}>
          <Input.Password placeholder="Password"/>
        </Form.Item>
        <Button block={true} className={"btn"} htmlType={"submit"} loading={isLoading}>Submit</Button>
        <div className={"message"}>don't have an Account? <Link to={"/signup"}>Signup</Link></div>
      </Form>
      <img />

    </div>
  )
};

export default Login;
