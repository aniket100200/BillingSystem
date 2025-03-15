import { applyMiddleware, combineReducers, legacy_createStore  as createStore } from "redux";
import { reducer } from "./reducer";
import { thunk } from 'redux-thunk'
import { userReducer } from "./userdatareducer";
import { utensileReducer } from "./utensilereducer";

const rootReducer =combineReducers({
    login: reducer,
    user : userReducer,
    utensile: utensileReducer
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