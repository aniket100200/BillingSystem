import { actions } from "./reducer";

const initialState ={
    userData :[],
    selectedEditUser : null
}

export const userReducer = (currentState=initialState,action)=>{


    if(action.type === actions.getData){
        return {...currentState,userData: action?.payload?.data};
    }

    if(action.type == actions.selectUser) {
       const selectedUser = currentState.userData.filter(u=> u.uuid === action?.payload?.uuid)[0];
        return {...currentState, selectedEditUser: selectedUser};
    }

    return currentState;
}