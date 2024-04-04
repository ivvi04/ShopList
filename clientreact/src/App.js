import React from "react";
import "./App.css";
import { useSelector } from "react-redux";
import { Container, Row, Col } from "react-bootstrap";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import NavigationBar from "./components/NavigationBar";
import Home from "./components/Home";
import Login from "./components/User/Login";
import Register from "./components/User/Register";
import List from "./components/Lists/List";
import ListList from "./components/Lists/ListList";
import Product from "./components/Product/Product";
import User from "./components/User/User";
import Footer from "./components/Footer";

const App = () => {
  // window.onbeforeunload = (event) => {
  //   const e = event || window.event;
  //   e.preventDefault();
  //   if (e) {
  //     e.returnValue = "";
  //   }
  //   return "";
  // };

  const login = useSelector((state) => state.login);
  login.username = localStorage.getItem('userName');
  login.userphone = localStorage.getItem('userPhone');
  login.isLoggedIn = localStorage.getItem('isLoggedIn');
  return (
    <Router>
      <NavigationBar />
      <Container>
        <Row>
          <Col lg={12} className={"margin-top"}>
            <Switch>
              <Route path="/" exact component={Home} />
              <Route path="/login" exact component={Login} />
              <Route path="/register" exact component={Register} />
              <Route path="/list/edit/:id" exact component={List} />
              <Route path="/list" exact component={ListList} />
              <Route path="/product/edit/:id" exact component={Product} />
              <Route path="/user" exact component={User} />
              <Route
                path="/logout"
                exact
                component={() => (
                  <Login message="Пользователь успешно вышел из системы" />
                )}
              />
            </Switch>
          </Col>
        </Row>
      </Container>
      <Footer />
    </Router>
  );
};

export default App;
