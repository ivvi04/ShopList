import * as UT from "./userTypes";
import * as authUser from "../../../utils/authUser";

const USER_URL = "http://localhost:8765/user/";

export const findUser = (userPhone) => {
    return async (dispatch) => {
        dispatch({
            type: UT.FIND_USER_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'get',
                url: USER_URL + userPhone
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

export const updateUser = (user) => {
    return async (dispatch) => {
        dispatch({
            type: UT.UPDATE_USER_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'put',
                url: USER_URL + "update",
                data: user
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

export const changePassword = (phone, oldPassword, password, confirmPassword) => async (dispatch) => {
    dispatch({
        type: UT.USER_REQUEST,
    });
    try {
        const response = await authUser.makeAPIRequest({
            method: 'post',
            url: "http://localhost:8765/auth/change-password",
            data: {
                phone: phone,
                oldPassword: oldPassword,
                password: password,
                confirmPassword: confirmPassword
            }
        });
        authUser.setUserToStorage(phone, response.data.username, true, response.data.token);
        dispatch(successChangePassword());
    } catch (error) {
        let errorMessage;
        if (error.response && error.response.data) errorMessage = error.response.data;
        else errorMessage = error.message;
        dispatch(failure(errorMessage));
    }
};

const success = (user) => {
    return {
        type: UT.USER_SUCCESS,
        payload: user,
    };
};

const successChangePassword = () => {
    return {
        type: UT.USER_CHANGE_PASSWORD_SUCCESS,
        payload: "",
    };
};

const failure = (error) => {
    return {
        type: UT.USER_FAILURE,
        payload: error,
    };
};
