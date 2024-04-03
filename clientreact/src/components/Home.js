import React from "react";
import { useSelector } from "react-redux";
import { Alert } from "react-bootstrap";

const Home = () => {
  const login = useSelector((state) => state.login);
  return (
    <Alert style={{ backgroundColor: "#343A40", color: "#ffffff80", display: "flex", justifyContent: "center"}}>
        Добро пожаловать, {login.username}!
    </Alert>
  );
};

export default Home;
