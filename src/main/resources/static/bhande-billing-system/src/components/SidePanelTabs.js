import React from 'react'
import { Link, useNavigate } from 'react-router-dom';
import '../components/styles/sidepanel.scss';
import { useSelector } from 'react-redux';



const data=[
        {val: "Home",nav:"/home",icon:"home", className:"link active"},
        {val:"Utensiles",nav:"/utensile",icon:'local_dining',className:'link'},
        {val: "Users", nav:"/user", icon:'group', className:'link'}
    ]

const SidePanelTabs = () => {
    const navigate = useNavigate();
    const user= useSelector(s=>s.user.currentUser);
  return (
    <div className='tabs'>
        {
            data.map((val,idx)=>{
               
                if( user?.role !== "admin" && val.val === "Users")
                return <></>

                
                return<>
                    <Link to={val.nav} onClick={(e)=>{
                       const activeElement = document.querySelector('.active');
                       activeElement.classList.remove('active');
                       e.target.classList.add('active');
                        
                    }} key={idx} className={val.className}>{<span className="material-icons">{val.icon}</span>}{val.val}</Link>
                </>
            })
        }
    </div>
  )
}

export default SidePanelTabs
