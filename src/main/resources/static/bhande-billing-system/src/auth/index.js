import axios from "axios";
import {notification} from "antd";

export const baseURL = `http://${window.location.hostname}:8080`;
const endpoints ={
    login:`${baseURL}/auth/login`,
    signup:`${baseURL}/user/create`,
    addUtensile:`${baseURL}/utensile/create`,
    deleteUser: `${baseURL}/user/deleteByUuid`,
    editUser :  `${baseURL}/user/get`,
    updateUser : `${baseURL}/user/update`,
    getUtensiles : `${baseURL}/utensile/get`
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
       sessionStorage.setItem("token",resp?.data?.message);
       

       
       return {success:true,user: resp?.data};


    }
    catch(e){
        notification.error({message:"Login failed"});
        return  {success:false};
    }
}

export async function signup(formData) {
    try{
        const resp = await  axios({
             url: endpoints.signup,
             method: "POST",
             data : formData,

         });       
 
        notification.success({message:"Signup successfully"});
        alert('Success');

        return {success:true};
 
 
     }
     catch(e){
         alert('Failed')
         return  {success:false};
     }
}


export async function addUtensile(formData){
    try{
        const resp = await  axios({
            url: endpoints.addUtensile,
            method: "POST",
            data : formData,
            headers:{
                Authorization: "Bearer " + sessionStorage.getItem("token")
            }

        })

        return { success: true}

    }catch (t){
        return {success:false};
    }
}

export async function deleteUser(uuid) {
    try{
       const resp = await axios({
        url: endpoints.deleteUser,
        method: "DELETE",
        params:{
            "uuid":uuid
        },
        headers:{
            Authorization: "Bearer "+sessionStorage.getItem("token")
        }
       })

       return {success : true};
    }catch(e){

        return {success: false};

    }
    
}

export async function editUser(uuid) {
    try{
       const resp = await axios({
        url: endpoints.editUser+"/"+uuid,
        method: "GET",
       
        headers:{
            Authorization: "Bearer "+sessionStorage.getItem("token")
        },
       })

       return {success : true, data : resp.data};
    }catch(e){

        return {success: false};

    }
    
}


export async  function updateUser(uuid,data) {
    try {
        const resp = await axios({
            url: endpoints.updateUser,
            method: "PUT",
            data :data,
            params:{
                uuid : uuid
            },
            headers:{
                Authorization: "Bearer "+sessionStorage.getItem("token")
            },

        })

        return {success : true, msg: "User Updated User Successfully!!!"};

    }catch (t){
        return {success:false};
    }

}

export async function getUtensiles() {
    try {
        const resp = await axios({
            method: "GET",
            url: endpoints.getUtensiles,
            headers: {
                Authorization: "Bearer " + sessionStorage.getItem("token")
            }

        });
        
        return {success : true, data : resp.data};
    } catch (error) {

        return { success : false, data : []};
        console.log(error);
    }
    
}