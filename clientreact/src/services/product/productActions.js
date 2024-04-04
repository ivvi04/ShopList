import * as UT from "./productTypes";
import * as authUser from "../../utils/authUser";

const PRODUCT_URI = "http://localhost:8765/product/";

export const findAllProducts = (listId) => {
    return async (dispatch) => {
        dispatch({
            type: UT.FIND_PRODUCT_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'get',
                url: PRODUCT_URI + listId + "/products"
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

export const findProduct = (productId) => {
    return async (dispatch) => {
        dispatch({
            type: UT.FIND_PRODUCT_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'get',
                url: PRODUCT_URI + productId
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

export const saveProduct = (product) => {
    return async (dispatch) => {
        dispatch({
            type: UT.SAVE_PRODUCT_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'post',
                url: PRODUCT_URI,
                data: product
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

export const updateProduct = (product) => {
    return async (dispatch) => {
        dispatch({
            type: UT.UPDATE_PRODUCT_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'put',
                url: PRODUCT_URI + "update",
                data: product
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

export const deleteProduct = (productId) => {
    return async (dispatch) => {
        dispatch({
            type: UT.DELETE_PRODUCT_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'delete',
                url: PRODUCT_URI + productId
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

export const deleteAllPurchasedProducts = (listId) => {
    return async (dispatch) => {
        dispatch({
            type: UT.DELETE_PRODUCT_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'put',
                url: PRODUCT_URI + listId + "/delPurchased"
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

const success = (product) => {
    return {
        type: UT.PRODUCT_SUCCESS,
        payload: product,
    };
};

const failure = (error) => {
    return {
        type: UT.PRODUCT_FAILURE,
        payload: error,
    };
};

