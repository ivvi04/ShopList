import * as UT from "./userTypes";

const initialState = {
  user: "",
  error: ""
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case UT.USER_REQUEST:
    case UT.FIND_USER_REQUEST:
    case UT.UPDATE_USER_REQUEST:
    case UT.USER_CHANGE_PASSWORD_SUCCESS:
      return {
        ...state,
      };
    case UT.USER_SUCCESS:
      return {
        user: action.payload,
        error: "",
      };
    case UT.USER_FAILURE:
      return {
        user: "",
        error: action.payload,
      };
    default:
      return state;
  }
};

export default reducer;
