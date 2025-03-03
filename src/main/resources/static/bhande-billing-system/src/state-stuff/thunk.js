import axios from "axios";
import { actions } from "./reducer";

const delay = (ms)=>{
    return new Promise((r)=>setTimeout(r,ms));
}

//I'll fetch my data from backend..
const baseURL = `http://${window.location.hostname}:8080`;

const endpoints = {
  allUsers : `${baseURL}/user/all`,
}

async function myThunk(dispatch) {
        try {
          const response = await axios({
            url: endpoints.allUsers,
            method: "GET",
            headers: {
              Authorization: "Bearer " + localStorage.getItem("token")
            },
          });          
         dispatch({type : actions.getData, payload : {data : response.data}});
        } catch (error) {
          console.error("API Error:", error); // Logs the actual error
        }
   
}

export default myThunk;