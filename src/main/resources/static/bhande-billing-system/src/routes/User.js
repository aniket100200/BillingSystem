import React, { useEffect, useRef } from 'react'
import '../styles/home.scss';
import { useSelector } from "react-redux";
import { Button } from 'antd';
import { deleteUser, editUser } from '../auth';
import { useNavigate } from 'react-router-dom';
import UserCard from '../components/usercomponents/UserCard';
import Login from './Login';

const User = () => {
  const userData = useSelector(state => {
    return state.user.userData;
  });

  const navigate = useNavigate();
  var columns = [];
  if (userData) {
    for (var col in userData[0]) {
      if (col !== "uuid" && col !== "zip" && col != "message" && col != "country" && col != "address")
        columns.push(col);
    }
  }

  const deleteUserByUuid = (uuid) => {
    const resp = deleteUser(uuid);
    resp.then((data) => {
      const { success } = data;
      if (success) {
        navigate("/user");
        alert("Your Data has been deleted");
      }

      else alert("Something Happend")
    })
  }

  const eiditUser = (uuid)=>{

    console.log(uuid);
    const resp = editUser(uuid);
    resp.then((data)=>{
      
    })

  }

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
                    
                    onClick={()=>{
                      eiditUser(data.uuid);
                    }}>Edit</Button></th>
                  </tr>)
              })
            }

          </tbody>
        </table>
        }
      </div>
    </div>
  )
}

export default User;
