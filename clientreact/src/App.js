import React from "react";
import "./App.css";
import { useSelector } from "react-redux";
import { Container, Row, Col } from "react-bootstrap";
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";

import NavigationBar from "./components/NavigationBar";
// import Welcome from "./components/Welcome";
import List from "./components/Lists/List";
import ListList from "./components/Lists/ListList";
import UserList from "./components/User/UserList";
import Register from "./components/User/Register";
import Login from "./components/User/Login";
import Footer from "./components/Footer";
import Home from "./components/Home";

const App = () => {
  // window.onbeforeunload = (event) => {
  //   const e = event || window.event;
  //   e.preventDefault();
  //   if (e) {
  //     e.returnValue = "";
  //   }
  //   return "";
  // };

  const auth = useSelector((state) => state.auth);
  auth.username = localStorage.getItem('userName');
  auth.userphone = localStorage.getItem('userPhone');
  auth.isLoggedIn = localStorage.getItem('isLoggedIn');
  // window.onbeforeunload = null;
  // window.onbeforeunload = function () {
  //   // Your Code here
  //   return null;  // return null to avoid pop up
  // }
  return (
    <Router>
      <NavigationBar />
      <Container>
        <Row>
          <Col lg={12} className={"margin-top"}>
            <Switch>
              <Route path="/" exact component={Home} />
              {/*<Route path="/home" exact component={Home} />*/}
              <Route path="/add" exact component={List} />
              <Route path="/edit/:id" exact component={List} />
              <Route path="/lists" exact component={ListList} />
              <Route path="/users" exact component={UserList} />
              <Route path="/register" exact component={Register} />
              <Route path="/login" exact component={Login} />
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
