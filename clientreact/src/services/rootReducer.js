import { combineReducers } from "redux";
import loginReducer from "./user/login/loginReducer";
import registerReducer from "./user/register/registerReducer";
import listReducer from "./list/listReducer";
import productReducer from "./product/productReducer";

const rootReducer = combineReducers({
  login: loginReducer,
  register: registerReducer,
  list: listReducer,
  product: productReducer,
});

export default rootReducer;
