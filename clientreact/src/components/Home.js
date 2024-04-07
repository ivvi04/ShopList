import React from "react";
import {Alert} from "react-bootstrap";

const Home = (props) => {
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
