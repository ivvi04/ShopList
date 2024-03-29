import React from "react";
import { useSelector } from "react-redux";
import { Alert } from "react-bootstrap";

const Home = () => {
  const auth = useSelector((state) => state.auth);
  return (
    <Alert style={{ backgroundColor: "#343A40", color: "#ffffff80", display: "flex", justifyContent: "center"}}>
        Welcome {auth.username}
    </Alert>
  );
};

export default Home;
