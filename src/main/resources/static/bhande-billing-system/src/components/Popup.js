import {Button, Form, Input, Select} from "antd";
import {useSelector} from "react-redux";
import React, {useEffect} from "react";
import {Option} from "antd/es/mentions";
import './styles/popup.scss'
import {updateUser} from "../auth";


const Popup = ({popup,setRefresh,refresh}) => {
    const closePopup = () => {
        popup.current.style.display = "none";
        setRefresh(!refresh);
    }

    const selectedUser = useSelector(state =>{
      return state.user.selectedEditUser
    });
    const user =[];
    useEffect(() => {
        for (const key in selectedUser) {
            user.push(selectedUser[key]);
        }

    }, [selectedUser]);

    const onSubmitForm = (e)=>{
        e.preventDefault();
        const target = e.target;
        const uuid = target.uuid?.value;
        const name= target.name?.value;
        const email = target.email?.value;
        const phone = target?.phone?.value;
        const address = target?.address?.value;
        const city = target.address?.value;
        const state = target.state?.value;
        const zip = target.zip?.value ? target.zip.value : "441924";
        const country = target?.country?.value;
        const role = target?.role?.value;


      const user =  { name, email, phone, address, city, state, zip,country,role};
        console.log(user);
        

      const resp = updateUser(uuid,user);
       resp.then((data)=>{
                const {success, msg} = data;

                if(success) alert(msg);
       })



    }

    return (<div id="popup" ref={popup}>

        <div className={"popup-container"}>
            <form onSubmit={onSubmitForm} className={"form"}>
                <div className={"form-group"}>
                    <input type={"hidden"} id="uuid" defaultValue={selectedUser?.uuid} />
                </div>
                <div className={"form-group"}>
                    <label htmlFor="name">Name:</label>
                    <input id="name" type="text" defaultValue={selectedUser?.name} required contentEditable={"true"} />
                </div>
                <div className={"form-group"}>
                    <label htmlFor="email">Email:</label>
                    <input id="email" type="text" defaultValue={selectedUser?.email} required/>
                </div>
                <div className={"form-group"}>
                    <label htmlFor="phone">Phone:</label>
                    <input id={"phone"} type="text" defaultValue={selectedUser?.phone} required={true}/>
                </div>
                <div className={"form-group"}>
                    <label htmlFor="address">Address:</label>
                    <input id="address" type="text" defaultValue={selectedUser?.address} required={true}/>
                </div>
                <div className={"form-group"}>
                    <label htmlFor="city">City:</label>
                    <input id={"city"} type={"text"} defaultValue={selectedUser?.city} required={true}/>
                </div>
                <div className={"form-group"}>
                    <label htmlFor="state">State:</label>
                    <input id={"state"} type={"text"} defaultValue={selectedUser?.state} required={true}/>
                </div>

                <div className={"form-group"}>
                    <label htmlFor="zip">Zip:</label>
                    <input  id={"zip"} type="text" defaultValue={selectedUser?.zip}/>
                </div>
                <div className={"form-group"}>
                    <label htmlFor="country">Country:</label>
                    <input id="country" type={"text"} defaultValue={selectedUser?.country} required={true}/>
                </div>
                <div className={"form-group"}>
                    <label htmlFor="role">Country:</label>
                    <select id="role" name="role">
                        <option value={"user"} selected={selectedUser?.role==="user"}>User</option>
                        <option value={"admin"} selected={selectedUser?.role==="admin"} >Admin</option>
                        <option value={"viewer"} selected={selectedUser?.role==="viewer"}>Viewer</option>
                    </select>
                </div>
                <div className={"form-group btns"}>
                    <button type={'submit'} className={"btn"}> Submit</button>
                    <button onClick={closePopup} className={"btn"} type={"button"}>Cancel</button>
                </div>

            </form>

        </div>


    </div>);
};


export default Popup;
