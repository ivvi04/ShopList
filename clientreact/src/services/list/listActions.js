import * as UT from "./listTypes";
import axios from "axios";
import * as authUser from "../../utils/authUser";

export const saveList = (list) => {
    return (dispatch) => {
        dispatch({
            type: UT.SAVE_LIST_REQUEST,
        });
        axios
            .post("http://localhost:8081/rest/books", list)
            .then((response) => {
                dispatch(listSuccess(response.data));
            })
            .catch((error) => {
                dispatch(listFailure(error));
            });
    };
};

export const fetchList = (listId) =>
    // async (dispatch) =>
    {
    // dispatch({
    //     type: UT.FETCH_LIST_REQUEST,
    // });
    // try {
    //     const response = await authUser.makeAPIRequest({
    //         method: 'get',
    //         url: "http://localhost:8765/list/" + listId
    //     });
    //     dispatch(listSuccess(response.data));
    //     return Promise.resolve(response.data);
    // } catch (error) {
    //     dispatch(listFailure(error));
    //     return Promise.reject(error);
    // };


    return (dispatch) => {
        dispatch({
            type: UT.FETCH_LIST_REQUEST,
        });
        try {
            const response = authUser.makeAPIRequest({
                method: 'get',
                url: "http://localhost:8765/list/" + listId
            });
            dispatch(listSuccess(response.data));
        } catch (error) {
            dispatch(listFailure(error));
        };
        // axios
        //     .get("http://localhost:8765/list/" + listId)
        //     .then((response) => {
        //         dispatch(listSuccess(response.data));
        //     })
        //     .catch((error) => {
        //         dispatch(listFailure(error));
        //     });
    };
};

export const updateList = (list) => {
    return (dispatch) => {
        dispatch({
            type: UT.UPDATE_LIST_REQUEST,
        });
        axios
            .put("http://localhost:8081/rest/books", list)
            .then((response) => {
                dispatch(listSuccess(response.data));
            })
            .catch((error) => {
                dispatch(listFailure(error));
            });
    };
};

export const deleteList = (listId) => async (dispatch) => {
    dispatch({
        type: UT.DELETE_LIST_REQUEST,
    });
    try {

        const response = await authUser.makeAPIRequest({
            method: 'delete',
            url: "http://localhost:8765/list/" + listId + "?userPhone=" + localStorage.userPhone
        });
        dispatch(listSuccess(response.data));
        return Promise.resolve(response.data);
    } catch (error) {
        dispatch(listFailure(error));
        return Promise.reject(error);
    };
};

const listSuccess = (list) => {
    return {
        type: UT.LIST_SUCCESS,
        payload: list,
    };
};

const listFailure = (error) => {
    return {
        type: UT.LIST_FAILURE,
        payload: error,
    };
};

export const fetchLanguages = () => {
    return (dispatch) => {
        dispatch({
            type: UT.FETCH_LANGUAGES_REQUEST,
        });
        axios
            .get("http://localhost:8081/rest/books/languages")
            .then((response) => {
                dispatch({
                    type: UT.LANGUAGES_SUCCESS,
                    payload: response.data,
                });
            })
            .catch((error) => {
                dispatch({
                    type: UT.LANGUAGES_FAILURE,
                    payload: error,
                });
            });
    };
};

export const fetchGenres = () => {
    return (dispatch) => {
        dispatch({
            type: UT.FETCH_GENRES_REQUEST,
        });
        axios
            .get("http://localhost:8081/rest/books/genres")
            .then((response) => {
                dispatch({
                    type: UT.GENRES_SUCCESS,
                    payload: response.data,
                });
            })
            .catch((error) => {
                dispatch({
                    type: UT.GENRES_FAILURE,
                    payload: error,
                });
            });
    };
};
