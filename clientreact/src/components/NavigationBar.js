import React from "react";
import {useDispatch, useSelector} from "react-redux";
import {Navbar, Nav} from "react-bootstrap";
import {Link} from "react-router-dom";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faUserPlus,
    faSignInAlt,
    faSignOutAlt, faUser,
} from "@fortawesome/free-solid-svg-icons";
import {logoutUser} from "../services/index";

const NavigationBar = () => {
    const login = useSelector((state) => state.login);
    const dispatch = useDispatch();

    const logout = () => {
        dispatch(logoutUser());
    };

    const guestLinks = (
        <>
            <div className="mr-auto"></div>
            <Nav className="navbar-right">
                <Link to={"register"} className="nav-link">
                    <FontAwesomeIcon icon={faUserPlus}/> Регистрация
                </Link>
                <Link to={"login"} className="nav-link">
                    <FontAwesomeIcon icon={faSignInAlt}/> Авторизация
                </Link>
            </Nav>
        </>
    );
    const userLinks = (
        <>
            <Nav className="mr-auto"></Nav>
            <Nav className="navbar-right">
                <Link to={"/user"} className="nav-link">
                    <FontAwesomeIcon icon={faUser}/> {login.username}
                </Link>
                <Link to={"/"} className="nav-link" onClick={logout}>
                    <FontAwesomeIcon icon={faSignOutAlt}/> Выйти
                </Link>
            </Nav>
        </>
    );

    return (
        <Navbar bg="dark" variant="dark">
            <Link to={login.isLoggedIn ? "/list" : ""} className="navbar-brand">
                <img
                    src="https://upload.wikimedia.org/wikipedia/commons/b/ba/Book_icon_1.png"
                    width="25"
                    height="25"
                    alt="brand"
                />{" "}
                Списки покупок
            </Link>
            {login.isLoggedIn ? userLinks : guestLinks}
        </Navbar>
    );
};

export default NavigationBar;
