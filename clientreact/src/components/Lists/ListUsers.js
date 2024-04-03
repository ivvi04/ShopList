import React, { Component } from "react";
import { connect } from "react-redux";
import { fetchUsers } from "../../services/index";

import "./../../assets/css/Style.css";
import {
  Card,
  Table,
  InputGroup,
  FormControl,
  Button,
  Alert,
} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faUsers,
  faStepBackward,
  faFastBackward,
  faStepForward,
  faFastForward,
} from "@fortawesome/free-solid-svg-icons";

class ListUsers extends Component {
  constructor(props) {
    super(props);
    this.state = {
      users: [],
      currentPage: 1,
      usersPerPage: 5,
    };
  }

  componentDidMount() {
    this.props.fetchUsers();
  }

  render() {
    const { currentPage, usersPerPage } = this.state;
    const lastIndex = currentPage * usersPerPage;
    const firstIndex = lastIndex - usersPerPage;

    const userData = this.props.userData;
    const users = userData.users;
    const currentUsers = users && users.slice(firstIndex, lastIndex);
    const totalPages = users && users.length / usersPerPage;

    return (
      <div>
        {userData.error ? (
          <Alert variant="danger">{userData.error}</Alert>
        ) : (
          <Card className={"border border-dark bg-dark text-white"}>
            <Card.Header>
              <FontAwesomeIcon icon={faUsers} /> Пользователя списка
            </Card.Header>
            <Card.Body>
              <Table bordered hover striped variant="dark">
                <thead>
                  <tr>
                    <td>Название</td>
                    <td>Номер телефона</td>
                  </tr>
                </thead>
                <tbody>
                  {users.length === 0 ? (
                    <tr align="center">
                      <td colSpan="6">No Users Available</td>
                    </tr>
                  ) : (
                    currentUsers.map((user, index) => (
                      <tr key={index}>
                        <td>
                          {user.first} {user.last}
                        </td>
                        <td>{user.name}</td>
                        <td>{user.address}</td>
                        <td>{user.created}</td>
                        <td>{user.balance}</td>
                      </tr>
                    ))
                  )}
                </tbody>
              </Table>
            </Card.Body>
            {/*{users.length > 0 ? (*/}
            {/*  <Card.Footer>*/}
            {/*    <div style={{ float: "left" }}>*/}
            {/*      Showing Page {currentPage} of {totalPages}*/}
            {/*    </div>*/}
            {/*    <div style={{ float: "right" }}>*/}
            {/*      <InputGroup size="sm">*/}
            {/*        /!*<InputGroup.Prepend>*!/*/}
            {/*          /!*<Button*!/*/}
            {/*          /!*  type="button"*!/*/}
            {/*          /!*  variant="outline-info"*!/*/}
            {/*          /!*  disabled={currentPage === 1 ? true : false}*!/*/}
            {/*          /!*  onClick={this.firstPage}*!/*/}
            {/*          /!*>*!/*/}
            {/*          /!*  <FontAwesomeIcon icon={faFastBackward} /> First*!/*/}
            {/*          /!*</Button>*!/*/}
            {/*          /!*<Button*!/*/}
            {/*          /!*  type="button"*!/*/}
            {/*          /!*  variant="outline-info"*!/*/}
            {/*          /!*  disabled={currentPage === 1 ? true : false}*!/*/}
            {/*          /!*  onClick={this.prevPage}*!/*/}
            {/*          /!*>*!/*/}
            {/*        /!*    <FontAwesomeIcon icon={faStepBackward} /> Prev*!/*/}
            {/*        /!*  </Button>*!/*/}
            {/*        /!*</InputGroup.Prepend>*!/*/}
            {/*        <FormControl*/}
            {/*          className={"page-num bg-dark"}*/}
            {/*          name="currentPage"*/}
            {/*          value={currentPage}*/}
            {/*          onChange={this.changePage}*/}
            {/*        />*/}
            {/*        <InputGroup.Append>*/}
            {/*          <Button*/}
            {/*            type="button"*/}
            {/*            variant="outline-info"*/}
            {/*            disabled={currentPage === totalPages ? true : false}*/}
            {/*            onClick={this.nextPage}*/}
            {/*          >*/}
            {/*            <FontAwesomeIcon icon={faStepForward} /> Next*/}
            {/*          </Button>*/}
            {/*          <Button*/}
            {/*            type="button"*/}
            {/*            variant="outline-info"*/}
            {/*            disabled={currentPage === totalPages ? true : false}*/}
            {/*            onClick={this.lastPage}*/}
            {/*          >*/}
            {/*            <FontAwesomeIcon icon={faFastForward} /> Last*/}
            {/*          </Button>*/}
            {/*        </InputGroup.Append>*/}
            {/*      </InputGroup>*/}
            {/*    </div>*/}
            {/*  </Card.Footer>*/}
            ) : null}
          </Card>
        )}
      </div>
    );
  }
}

const mapStateToProps = (state) => {
  return {
    userData: state.user,
  };
};

const mapDispatchToProps = (dispatch) => {
  return {
    fetchUsers: () => dispatch(fetchUsers()),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(ListUsers);
