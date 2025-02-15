import { actions } from "./reducer";

const initialState ={
    userData :[]
}

export const userReducer = (currentState=initialState,action)=>{

    if(action.type === actions.getData){
        return {...currentState,userData: action?.payload?.data};
    }

    return currentState;
}