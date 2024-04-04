import * as UT from "./listTypes";
import * as authUser from "../../utils/authUser";

const LIST_URI = "http://localhost:8765/list/";

export const findList = (listId) => {
    return async (dispatch) => {
        dispatch({
            type: UT.FIND_LIST_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'get',
                url: LIST_URI + listId
            });
            dispatch(success(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(failure(errorMessage));
        }
    };
};

export const saveList = (list) => {
    return async (dispatch) => {
        dispatch({
            type: UT.SAVE_LIST_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'post',
                url: LIST_URI,
                data: list
            });
            dispatch(success(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(failure(errorMessage));
        }
    };
};

export const updateList = (list) => {
    return async (dispatch) => {
        dispatch({
            type: UT.UPDATE_LIST_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'put',
                url: LIST_URI + "phone/" + localStorage.userPhone + "/update",
                data: list
            });
            dispatch(success(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(failure(errorMessage));
        }
    };
};

export const deleteList = (listId) => {
    return async (dispatch) => {
        dispatch({
            type: UT.DELETE_LIST_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'delete',
                url: LIST_URI + "phone/" + localStorage.userPhone + "/delete/" + listId
            });
            dispatch(success(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(failure(errorMessage));
        }
    };
};

export const addUser = (listId, userPhone) => {
    return async (dispatch) => {
        dispatch({
            type: UT.UPDATE_LIST_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'put',
                url: LIST_URI + listId + "/user/add?phone=" + userPhone
            });
            dispatch(success(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(failure(errorMessage));
        }
    };
};

export const deleteUser = (listId, userPhone) => {
    return async (dispatch) => {
        dispatch({
            type: UT.UPDATE_LIST_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'put',
                url: LIST_URI + listId + "/user/delete?phone=" + userPhone
            });
            dispatch(success(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(failure(errorMessage));
        }
    };
};

const success = (list) => {
    return {
        type: UT.LIST_SUCCESS,
        payload: list,
    };
};

const failure = (error) => {
    return {
        type: UT.LIST_FAILURE,
        payload: error,
    };
};

