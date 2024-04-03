import * as UT from "./productTypes";
import * as authUser from "../../utils/authUser";

export const findAllProducts = (listId) => {
    return async (dispatch) => {
        dispatch({
            type: UT.FIND_PRODUCT_REQUEST,
        });
        try {
            const response = await authUser.makeAPIRequest({
                method: 'get',
                url: "http://localhost:8765/product/" + listId + "/products"
            });
            dispatch(productSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(productFailure(errorMessage));
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
                url: "http://localhost:8765/product/" + productId
            });
            dispatch(productSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(productFailure(errorMessage));
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
                url: "http://localhost:8765/product/",
                data: product
            });
            dispatch(productSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(productFailure(errorMessage));
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
                url: "http://localhost:8765/product/update",
                data: product
            });
            dispatch(productSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(productFailure(errorMessage));
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
                url: "http://localhost:8765/product/" + productId
            });
            dispatch(productSuccess(response.data));
        } catch (error) {
            let errorMessage;
            if (error.response && error.response.data) errorMessage = error.response.data;
            else errorMessage = error.message;
            dispatch(productFailure(errorMessage));
        }
    };
};

const productSuccess = (product) => {
    return {
        type: UT.PRODUCT_SUCCESS,
        payload: product,
    };
};

const productFailure = (error) => {
    return {
        type: UT.PRODUCT_FAILURE,
        payload: error,
    };
};

