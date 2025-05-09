//redux-thunk => helps us in acheiving the asynchrounous updates to the store..
const initialState = {
    isUserLoggedIn : Boolean(sessionStorage.getItem("token")),
}

export const actions ={
   LoggedIn : "LoggedIn",
   NotLoggedIn: "NotLoggedIn",
   getData : "getData",
    selectUser : "selectUser",
    currentUser: "currentUser"
}

export const reducer = (currentState = initialState, action)=>{
    
    if(action.type == actions.LoggedIn)
        return {...initialState, isUserLoggedIn : true}

    if(action.type == actions.NotLoggedIn)
        return {...currentState, isUserLoggedIn:false}

    return initialState;

};