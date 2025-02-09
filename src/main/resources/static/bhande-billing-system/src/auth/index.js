import axios from "axios";
import {notification} from "antd";

const baseURL = "http://192.168.206.107:8080";
const endpoints ={
    login:`${baseURL}/auth/login`,
    signup:`${baseURL}/user/create`,
}

export async function login(formData){
    try{
       const resp = await  axios({
            url: endpoints.login,
            method: "POST",
            data : formData
        });       

       notification.success({message:"Login successfully"});

       const token = resp?.data?.message;
       if(token)
       localStorage.setItem("token",resp?.data?.message);
       
       
       return {success:true};


    }catch(e){
        notification.error({message:"Login failed"});
        return  {success:false};
    }
}