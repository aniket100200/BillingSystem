
import { BrowserRouter, Route, Routes, useNavigate } from 'react-router-dom';
import './App.css';
import './App.scss'
import { lazy, useEffect, useRef} from 'react';
import ReactDOM from 'react-dom'
import { LoadingWrapper } from './components/LoadingWrapper';
import PageNotFound from './components/PageNotFound';
import {  useDispatch, useSelector } from 'react-redux';
import Utensiles from './routes/Utensiles';
import User from './routes/User';
import myThunk from './state-stuff/thunk';
import CreateUtensile from "./routes/CreateUtensile";
import SidePanel from './components/SidePanel';
import { actions } from './state-stuff/reducer';
import ShowUtensiles from './routes/ShowUtensiles';
import { Button } from 'antd';

const Signup = lazy(() => import('./routes/Signup'));
const Login = lazy(() => import('./routes/Login'));
const Home = lazy(() => import('./routes/Home')); // This is called lazy loading

function App() {
  const isUserLoggedIn = useSelector(state =>state.login.isUserLoggedIn);
  //lazy Initialization..
  const dispatch = useDispatch();  
  const popupRef = useRef(null);
  
  useEffect(()=>{

    dispatch(myThunk);
  //  popupRef.current.innerHTML="Aniket";
     
   },[]);

   const closePopup = ()=>{
    if (popupRef.current) {
      popupRef.current.style.display = "none"; // Hide popup
      
      ReactDOM.createPortal (Login,popupRef.current);

      console.log(popupRef.current);
    } else {
      console.error("popupRef is null!");
    }
   }

  return(
    <div className='App'>
    
    


    <div id = 'toast' style={{display : "none", position:"fixed"}}>

    </div>
      <BrowserRouter>
      <Routes>
       {isUserLoggedIn ? <>
        <Route path='/*' element={<LoadingWrapper Component={SidePanel} /> } />
       </> :<></>} 
      </Routes>
        <Routes>

          {isUserLoggedIn ? <>
            <Route path='/home' element={<LoadingWrapper Component={Home} />} />
            <Route path='/utensile' element={<LoadingWrapper Component={Utensiles} />} />
            <Route path='/user' element={<LoadingWrapper Component={User} />} />
              <Route path={"/create/utensile"} element={<LoadingWrapper Component={CreateUtensile}/>} />
              <Route path={"/create/*"}  />
          </> :
            <>
              <Route path='/' element={<LoadingWrapper Component={Login} />} />
              <Route path='/login' element={<LoadingWrapper Component={Login} />} />
              <Route path='/signup' element={<LoadingWrapper Component={Signup} />} />
            </>}
          <Route path='*' element={<LoadingWrapper Component={PageNotFound} />} />
        </Routes>
      </BrowserRouter>

    </div>
 )
}

export default App;
