import * as UT from "./listTypes";
import * as authUser from "../../utils/authUser";

export const findList = (listId) => {
    return async (dispatch) => {
        dispatch({
            type: UT.FIND_LIST_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'get',
                url: "http://localhost:8765/list/" + listId
            });
            dispatch(listSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(listFailure(errorMessage));
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
                url: "http://localhost:8765/list/",
                data: list
            });
            dispatch(listSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(listFailure(errorMessage));
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
                url: "http://localhost:8765/list/phone/" + localStorage.userPhone + "/update",
                data: list
            });
            dispatch(listSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(listFailure(errorMessage));
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
                url: "http://localhost:8765/list/phone/" + localStorage.userPhone + "/delete/" + listId
            });
            dispatch(listSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(listFailure(errorMessage));
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
                url: "http://localhost:8765/list/" + listId + "/user/add?phone=" + userPhone
            });
            dispatch(listSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(listFailure(errorMessage));
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
                url: "http://localhost:8765/list/" + listId + "/user/delete?phone=" + userPhone
            });
            dispatch(listSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(listFailure(errorMessage));
        }
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

