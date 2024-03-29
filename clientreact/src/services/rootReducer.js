import { combineReducers } from "redux";
import userReducer from "./user/register/registerReducer";
import authReducer from "./user/login/loginReducer";
import bookReducer from "./list/listReducer";

const rootReducer = combineReducers({
  user: userReducer,
  book: bookReducer,
  auth: authReducer,
});

export default rootReducer;
