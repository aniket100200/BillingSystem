import React from 'react'
import {Form} from "antd";

const Signup = () => {
  const onSubmit = (e) => {
    e.preventDefault()
  }
  return (
    <div>
      <Form onFinish={onFinishForm}>

      </Form>
      
    </div>
  )
}

export default Signup; //default export for the lazy loading
