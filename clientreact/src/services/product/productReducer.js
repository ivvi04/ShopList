import * as UT from "./productTypes";

const initialState = {
  product: "",
  error: "",
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case UT.FIND_PRODUCT_REQUEST:
    case UT.SAVE_PRODUCT_REQUEST:
    case UT.UPDATE_PRODUCT_REQUEST:
    case UT.DELETE_PRODUCT_REQUEST:
      return {
        ...state,
      };
    case UT.PRODUCT_SUCCESS:
      return {
        product: action.payload,
        error: "",
      };
    case UT.PRODUCT_FAILURE:
      return {
        product: "",
        error: action.payload,
      };
    default:
      return state;
  }
};

export default reducer;
