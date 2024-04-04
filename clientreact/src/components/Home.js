import React from "react";
// import { useSelector } from "react-redux";
import {Alert} from "react-bootstrap";

const Home = (props) => {
    // const login = useSelector((state) => state.login);
    if (localStorage.isLoggedIn) return props.history.push("/list");
    else {
        return (
            <Alert style={{backgroundColor: "#343A40", color: "#ffffff80", display: "flex", justifyContent: "center"}}>
                Добро пожаловать!
            </Alert>
        );
    }
};

export default Home;
