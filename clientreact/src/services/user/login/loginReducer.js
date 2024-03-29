import * as UT from "./loginTypes";

const initialState = {
  username: "",
  userphone: "",
  isLoggedIn: "",
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case UT.LOGIN_REQUEST:
    case UT.LOGOUT_REQUEST:
      return {
        ...state,
      };
    case UT.LOGIN_SUCCESS:
    case UT.LOGIN_FAILURE:
      return {
        username: action.payload.username,
        userphone: action.payload.userphone,
        isLoggedIn: action.payload.isLoggedIn,
      };
    default:
      return state;
  }
};

export default reducer;
