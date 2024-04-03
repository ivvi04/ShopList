import axios from "axios";

// const authToken = () => {
//   if (getTokenFromStorage) {
//     axios.defaults.headers.common["Authorization"] = `${getTokenFromStorage}`;
//   } else {
//     delete axios.defaults.headers.common["Authorization"];
//   }
// };

const JSON_HEADERS = {
  'Accept': 'application/json',
  'Content-Type': 'application/json; charset=utf-8'
};

export const setUserToStorage = (phone, userName, isLoggedIn, token) => {
  localStorage.setItem('userPhone', phone);
  localStorage.setItem('userName', userName);
  localStorage.setItem('isLoggedIn', true);
  localStorage.setItem('jwtToken', token);
}
// export const getTokenFromStorage = (userName, isLoggedIn, token) => {
//   userName = localStorage.getItem('userName');
//   isLoggedIn = localStorage.getItem('isLoggedIn');
//   token = localStorage.getItem('jwtToken');
// }
export const removeUserFromStorage = () => {
  localStorage.removeItem('userPhone');
  localStorage.removeItem('userName');
  localStorage.removeItem('isLoggedIn');
  localStorage.removeItem('jwtToken');
}

const getHeaders = () => {

  if (!localStorage.getItem('jwtToken')) {
    return JSON_HEADERS;
  }
  return {
    JSON_HEADERS,
    'Authorization': 'Bearer ' + localStorage.getItem('jwtToken')
  };
};

export const makeAPIRequest = ({ method, url, data }) => {
  return axios({
    method: method,
    url: url,
    data: data,
    headers: getHeaders()
  });
}

// export default authToken;
