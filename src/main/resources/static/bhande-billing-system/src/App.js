
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import { lazy} from 'react';
import { LoadingWrapper } from './components/LoadingWrapper';
import PageNotFound from './components/PageNotFound';
import {  useSelector } from 'react-redux';

const Signup = lazy(() => import('./routes/Signup'));
const Login = lazy(() => import('./routes/Login'));
const Home = lazy(() => import('./routes/Home')); // This is called lazy loading

function App() {
  const isUserLoggedIn = useSelector(state =>state.login.isUserLoggedIn);
  //lazy Initialization.. 

  return(
    <div className='App'>
      <BrowserRouter>
        <Routes>
          {isUserLoggedIn ? <Route path='/home' element={<LoadingWrapper Component={Home} />} /> :
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
