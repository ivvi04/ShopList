import * as UT from "./listTypes";

const initialState = {
  list: "",
  error: "",
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case UT.FIND_LIST_REQUEST:
    case UT.SAVE_LIST_REQUEST:
    case UT.UPDATE_LIST_REQUEST:
    case UT.DELETE_LIST_REQUEST:
      return {
        ...state,
      };
    case UT.LIST_SUCCESS:
      return {
        list: action.payload,
        error: "",
      };
    case UT.LIST_FAILURE:
      return {
        list: "",
        error: action.payload,
      };
    default:
      return state;
  }
};

export default reducer;
