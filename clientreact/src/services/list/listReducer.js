import * as UT from "./listTypes";

const initialState = {
  book: "",
  error: "",
};

const reducer = (state = initialState, action) => {
  switch (action.type) {
    case UT.SAVE_LIST_REQUEST:
    case UT.FETCH_LIST_REQUEST:
    case UT.UPDATE_LIST_REQUEST:
    case UT.DELETE_LIST_REQUEST:
    case UT.FETCH_LANGUAGES_REQUEST:
    case UT.FETCH_GENRES_REQUEST:
      return {
        ...state,
      };
    case UT.LIST_SUCCESS:
      return {
        book: action.payload,
        error: "",
      };
    case UT.LIST_FAILURE:
      return {
        book: "",
        error: action.payload,
      };
    case UT.LANGUAGES_SUCCESS:
      return {
        languages: action.payload,
        error: "",
      };
    case UT.LANGUAGES_FAILURE:
      return {
        languages: "",
        error: action.payload,
      };
    case UT.GENRES_SUCCESS:
      return {
        genres: action.payload,
        error: "",
      };
    case UT.GENRES_FAILURE:
      return {
        genres: "",
        error: action.payload,
      };
    default:
      return state;
  }
};

export default reducer;
