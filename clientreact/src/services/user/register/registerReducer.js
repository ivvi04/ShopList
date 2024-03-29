import * as UT from "./registerTypes";

const initialState = {
  username: "",
  userphone: "",
  isLoggedIn: ""
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case UT.REGISTER_REQUEST:
      return {
        ...state,
      };
    case UT.REGISTER_SUCCESS:
    case UT.REGISTER_SAVED_SUCCESS:
    case UT.REGISTER_FAILURE:
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
