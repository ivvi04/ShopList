import * as UT from "./registerTypes";
import * as authToken from "../../../utils/authUser";
import {authenticateUser} from "../login/loginActions";

const REGISTER_URL = localStorage.addressIp + "/auth/sign-up";

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
