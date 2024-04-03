import { combineReducers } from "redux";
import userReducer from "./user/register/registerReducer";
import authReducer from "./user/login/loginReducer";
import listReducer from "./list/listReducer";

const rootReducer = combineReducers({
  user: userReducer,
  list: listReducer,
  auth: authReducer,
});

export default rootReducer;
