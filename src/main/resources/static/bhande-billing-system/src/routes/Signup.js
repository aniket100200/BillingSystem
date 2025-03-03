import React from 'react'
import {Button, Form, Input, Radio, Select,CheckboxOptionType} from "antd";
import '../styles/login.scss';
import {Link} from "react-router-dom";
import { signup } from '../auth';
import {Option} from "antd/es/mentions";

const Signup = ({uuid}) => {
  const onFinishForm = (data) => {
       const resp =signup(data);
  }
  return (
    <div className={"form-container"}>
      <Form onFinish={onFinishForm} layout="horizontal" className={"form"}>

        <div className={"form-group"}>
          <Form.Item name={"name"} rules={[{required: true, message: "Name is required"}]} label={"Name"}>
            <Input placeholder={"Name"}/>

          </Form.Item>
          <Form.Item name="email"
                     label={"Email"}
                     rules={[{type: "email", message: "Please enter a valid email."}, {
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

          <Form.Item name={"Zip"} rules={[{required: true, message: "Zip is required"}]} label={"zip"}>
            <Input placeholder={"zip"}/>
          </Form.Item>
          <Form.Item name={"country"} rules={[{required: true, message: "Country is required"}]} label={"Country"}>
            <Input placeholder={"Country"}/>
          </Form.Item>
          <Form.Item name={"password"} rules={[{required: true, message: "Passwords are required"}]} label={"Password"}>
            <Input.Password placeholder="Password"/>
          </Form.Item>

          <Form.Item name={"role"} rules={[{required: true, message: "Role is required"}]} label={"Role"}>
            <Select
                defaultValue="user"
                style={{ width: 200 }}
                onChange={()=>{}}
            >
              <Option value="admin">Admin</Option>
              <Option value="user">User</Option>
              <Option value="viewer">Viewer</Option>
            </Select>
          </Form.Item>
        </div>
        <div>
        <Button block htmlType={"submit"} className={"btn"}>Submit</Button>
        {uuid ? <></> : <div className={"message"}>Already have an Account? <Link to={"/"}>Login</Link></div>}
        </div>
      </Form>

    </div>
  )
}

export default Signup; //default export for the lazy loading
