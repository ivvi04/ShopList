import * as UT from "./loginTypes";
// import axios from "axios";
import * as authUser from "../../../utils/authUser";

const AUTH_URL = "http://localhost:8765/auth/sign-in";

export const authenticateUser = (phone, password) => async (dispatch) => {

    dispatch(loginRequest());
    try {
        const response = await authUser.makeAPIRequest({
            method: 'post',
            url: AUTH_URL,
            data: {
                phone: phone,
                password: password
            }
        });
        authUser.setUserToStorage(phone, response.data.username, true, response.data.token);
        dispatch(success({username: response.data.username, userphone: phone, isLoggedIn: true}));
        return Promise.resolve(response.data);
    } catch (error) {
        dispatch(failure(error.message));
        return Promise.reject(error);
    }
};

export const logoutUser = () => {
    return (dispatch) => {
        dispatch(logoutRequest());
        authUser.removeUserFromStorage();
        dispatch(success({username: "", isLoggedIn: false}));
    };
};

const loginRequest = () => {
    return {
        type: UT.LOGIN_REQUEST,
    };
};

const logoutRequest = () => {
    return {
        type: UT.LOGOUT_REQUEST,
    };
};

const success = (isLoggedIn) => {
    return {
        type: UT.LOGIN_SUCCESS,
        payload: isLoggedIn,
    };
};

const failure = (error) => {
    return {
        type: UT.LOGIN_FAILURE,
        payload: error,
    };
};
