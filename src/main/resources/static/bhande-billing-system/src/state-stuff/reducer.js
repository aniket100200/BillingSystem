const initialState = {
    count : 0,
    isUserLoggedIn : Boolean(localStorage.getItem("token"))
}

export const actions ={
   LoggedIn : "LoggedIn",
   NotLoggedIn: "NotLoggedIn" 
}

export const reducer = (currentState = initialState, action)=>{
    
    if(action.type == actions.LoggedIn)
        return {...initialState, isUserLoggedIn : true}
    

    return initialState;

};