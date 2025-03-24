import {baseURL} from "../index";
import axios from "axios";

const endpoints = {
    utensile: `${baseURL}/utensile`
}

export async function changeUtensile(uuid,formData){
    debugger;
    try{
        const resp = await  axios({
            url: endpoints.utensile+"/update",
            method: "PUT",
            data : formData,
            params :{
                uuid : uuid
            },
            headers : {
                Authorization: "Bearer " + sessionStorage.getItem("token")
            }

        });

        return {success:true, message:"Successfully Updated Utensile"};

    }catch (e){

        return { success:false, data : []};

    }
}