const initialState = {
    utensiles : [],
    selectedUtensile :null
}

export const utensileActions ={
    setUtensiles : "setUtensiles",
    selectedUtensile : "selectedUtensile"
}

export const utensileReducer = (currentState = initialState,action)=>{

    if(action.type === utensileActions.setUtensiles){
        return {...currentState, utensiles : action.payload.data};
    }

    if(action.type === utensileActions.selectedUtensile){
        const selected = currentState.utensiles.filter(e=>{
                 if(e.uuid == action.payload.card.uuid) return true;
                 else return false;
        })[0];      

        return {...currentState, selectedUtensile : selected};
    }

    return currentState;

}