
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import { lazy, useState } from 'react';
import { LoadingWrapper } from './components/LoadingWrapper';

const Signup = lazy(()=> import('./routes/Signup'));
const Login = lazy(()=>import('./routes/Login'));
const Home = lazy(()=> import('./routes/Home')); // This is called lazy loading

function App() {
const[isUserLoggedIn,setIsUserLoggedIn] = useState(()=>{
  return Boolean(localStorage.getItem("token"));
});

//lazy Initialization.. 

  return <div className='App'>
  <BrowserRouter>
    <Routes>
    { isUserLoggedIn ? <Route path='home' element={<LoadingWrapper Component={Home}/>} /> :
    <>
    <Route path='login' element={<LoadingWrapper Component={Login}/>}/>
      <Route path='signup' element={<LoadingWrapper Component={Signup}/>}/>
    </> }
      <Route path='*' element={<h2> Page Not Found</h2>} />
    </Routes>
  </BrowserRouter>

  </div>
}

export default App;
