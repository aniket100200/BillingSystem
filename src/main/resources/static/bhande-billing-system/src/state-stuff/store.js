import { applyMiddleware, combineReducers, legacy_createStore  as createStore } from "redux";
import { reducer } from "./reducer";
import { thunk } from 'redux-thunk'
import { userReducer } from "./userdatareducer";

const rootReducer =combineReducers({
    login: reducer,
    user : userReducer
});

/**
 * over all state :{
 * login :{
 * isUserLoggedin:false
 * },
 * userReducer}
 */
 const store = createStore(rootReducer, applyMiddleware(thunk));

 export default store;