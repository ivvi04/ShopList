import { combineReducers } from "redux";
import loginReducer from "./user/login/loginReducer";
import registerReducer from "./user/register/registerReducer";
import userReducer from "./user/user/userReducer";
import listReducer from "./list/listReducer";
import productReducer from "./product/productReducer";

const rootReducer = combineReducers({
  login: loginReducer,
  register: registerReducer,
  user: userReducer,
  list: listReducer,
  product: productReducer,
});

export default rootReducer;
