import React from 'react'
import {Button, Form, Input, Radio} from "antd";
import '../styles/login.scss';
import {Link} from "react-router-dom";

const Signup = () => {
  const onFinishForm = (data) => {

  }
  return (
    <div className={"form-container"}>
      <Form onFinish={onFinishForm} layout="horizontal" className={"form"}>

        <Form.Item name={"name"} rules={[{required: true, message: "Name is required"}]} label={"Name"}>
          <Input placeholder={"Name"}/>

        </Form.Item>
        <Form.Item name="email" label={"Email"} rules={[{type: "email", message: "Please enter a valid email."}, {
          required: true,
          message: "Email is required"
        }]}>
          <Input placeholder={"Email"}/>
        </Form.Item>
        <Form.Item label={"Phone"} name={"phone"} rules={[{required: true, message: "Phone is required"}]}>
          <Input placeholder={"Phone"}/>
        </Form.Item>
        <Form.Item name={"address"} rules={[{required: true, message: "Address is required"}]} label={"Address"}>
          <Input placeholder={"Address"}/>
        </Form.Item>

        <Form.Item name={"city"} rules={[{required: true, message: "City is required"}]} label={"City"}>
          <Input placeholder={"City"}/>
        </Form.Item>

        <Form.Item name={"state"} rules={[{required: true, message: "State is required"}]} label={"State"}>
          <Input placeholder={"State"}/>
        </Form.Item>

        <Form.Item name={"State"} rules={[{required: true, message: "State is required"}]} label={"State"}>
          <Input placeholder={"State"}/>
        </Form.Item>
        <Form.Item name={"country"} rules={[{required: true, message: "Country is required"}]} label={"Country"}>
          <Input placeholder={"Country"}/>
        </Form.Item>
        <Form.Item name={"password"} rules={[{required: true, message: "Passwords are required"}]} label={"Password"}>
          <Input.Password placeholder="Password"/>
        </Form.Item>

        <Form.Item name={"role"} rules={[{required: true, message: "Role is required"}]} label={"Role"}>
          <Radio.Group>
            <Radio value="admin">Admin</Radio>
            <Radio value="user">User</Radio>
          </Radio.Group>
        </Form.Item>


        <Button block htmlType={"submit"} className={"btn"}>Submit</Button>

        <div className={"message"}>Already have an Account? <Link to={"/Login"}>Login</Link></div>
      </Form>

    </div>
  )
}

export default Signup; //default export for the lazy loading
