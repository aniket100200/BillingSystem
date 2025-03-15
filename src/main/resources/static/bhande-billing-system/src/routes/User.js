import React, {useEffect, useRef, useState} from 'react'
import '../styles/home.scss';
import {useDispatch, useSelector} from "react-redux";
import { Button } from 'antd';
import { deleteUser, editUser } from '../auth';
import { useNavigate } from 'react-router-dom';
import UserCard from '../components/usercomponents/UserCard';
import Login from './Login';
import ReactDOM from "react-dom";
import Popup from "../components/Popup";
import {actions} from "../state-stuff/reducer";

const User = () => {
  const userData = useSelector(state => {
    return state.user.userData;
  });

  const dispatch = useDispatch();

  const popupRef = useRef(null);

  const closePopup = ()=>{
    if (popupRef.current) {
      popupRef.current.style.display = "none"; // Hide popup

      ReactDOM.createPortal (Login,popupRef.current);

      console.log(popupRef.current);
    } else {
      console.error("popupRef is null!");
    }
  }

  const navigate = useNavigate();
  var columns = [];
  if (userData) {
    for (var col in userData[0]) {
      if (col !== "uuid" && col !== "zip" && col != "message" && col != "country" && col != "address")
        columns.push(col);
    }
  }

  const deleteUserByUuid = (uuid) => {
    const Resp=prompt("Hey Do you Really Wanted to Delete this User Then Type 'YES'");
    if (Resp === "YES") {
      const resp = deleteUser(uuid);
      resp.then((data) => {
        const {success} = data;
        if (success) {
          navigate("/user");
          alert("Your Data has been deleted");
        } else alert("Something Happend")
      })
    }
  }

  const eiditUser = (uuid)=>{
    dispatch({type : actions.selectUser, payload : {uuid}});

    popupRef.current.style.display = "flex";

  }
  const [refresh,setRefresh] = useState(true);

  useEffect(() => {

  }, [refresh]);

  return (
      <div className='home'>
        <div>
          {<table>
            <thead>
            <tr>
              {
                columns.map((col) => {
                  return <th> {col.toLocaleUpperCase()}</th>
                })
              }
              <th>Actions</th>

            </tr>
            </thead>
            <tbody>

            {
              userData.map((data) => {
                return (
                    <tr>
                      {
                        columns.map((col) => {
                          return <td>{data[col]}</td>;
                        })
                      }
                      <th><Button onClick={() => {
                        deleteUserByUuid(data.uuid)
                      }}>Delete</Button> <Button

                          onClick={() => {
                            eiditUser(data.uuid);
                          }}>Edit</Button></th>
                    </tr>)
              })
            }

            </tbody>
          </table>
          }
        </div>
        <Popup popup = {popupRef} setRefresh = {setRefresh} refresh={refresh}/>
      </div>

  )
}

export default User;
