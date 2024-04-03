import * as UT from "./registerTypes";
import axios from "axios";
import * as authToken from "../../../utils/authUser";
import {authenticateUser} from "../login/loginActions";

const REGISTER_URL = "http://localhost:8765/auth/sign-up";

export const fetchUsers = () => {
    return (dispatch) => {
        dispatch(userRequest());
        axios
            .get(
                "https://randomapi.com/api/6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole"
            )
            .then((response) => {
                dispatch(userSuccess(response.data));
            })
            .catch((error) => {
                dispatch(userFailure(error.message));
            });
    };
};

export const registerUser = (userObject) => async (dispatch) => {
    dispatch(userRequest());
    try {
        const response = await authToken.makeAPIRequest({
            method: 'post',
            url: REGISTER_URL,
            data: userObject
        });
        dispatch(authenticateUser(userObject.phone, userObject.password));
        return Promise.resolve(response.data);
    } catch (error) {
        dispatch(userFailure(error.message));
        return Promise.reject(error);
    }
};

const userRequest = () => {
    return {
        type: UT.REGISTER_REQUEST,
    };
};

const userSuccess = (isLoggedIn) => {
    return {
        type: UT.REGISTER_SUCCESS,
        payload: isLoggedIn,
    };
};

const userFailure = (error) => {
    return {
        type: UT.REGISTER_FAILURE,
        payload: error,
    };
};
