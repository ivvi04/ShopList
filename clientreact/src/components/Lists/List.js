import React, {Component} from "react";
import {connect} from "react-redux";
import {findList, saveList, updateList, deleteList, addUserToList, deleteUserFromList} from "../../services/index";

import {Alert, Button, ButtonGroup, Card, Col, Form, FormControl, InputGroup, Table} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEdit, faList, faPlusSquare, faSave, faTimes, faTrash, faUndo,} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";
import * as authUser from "../../utils/authUser";

class List extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialListState;
        this.state = {
            search: "",
            users: []
        };
    }

    initialListState = {
        id: "",
        name: "",
        phone: localStorage.userPhone,
        users: ""
    };

    componentDidMount() {
        const listId = +this.props.match.params.id;
        if (listId) this.findListById(listId);
    }

    findListById = (listId) => {
        this.props.findList(listId)
            .then(() => {
                let list = this.props.listObject.list;
                if (list != null) {
                    this.setState({
                        id: list.id,
                        name: list.name,
                        phone: list.phone,
                        users: list.users
                    });
                }
            })
            .catch((error) => {
                let errorMessage;
                if (error.response && error.response.data) errorMessage = error.response.data;
                else errorMessage = error.message;
                this.setState({show: true});
                this.setState({error: errorMessage})
            })
    };

    resetList = () => {
        this.setState(() => this.initialListState);
    };

    submitList = (event) => {
        event.preventDefault();

        const list = {
            name: this.state.name,
            phone: localStorage.userPhone,
            users: this.state.users
        };
        this.props.saveList(list)
            .then(() => {
                let list = this.props.listObject.list;
                if (list != null) {
                    this.setState({show: true});
                    this.setState({message: "Список добавлен!"});
                    setTimeout(() => this.setState({show: false}), 3000);
                }
            })
            .catch((error) => {
                let errorMessage;
                if (error.response && error.response.data) errorMessage = error.response.data;
                else errorMessage = error.message;
                this.setState({show: true});
                this.setState({error: errorMessage})
            })
        this.setState(this.initialListState);
        this.listList();
    };

    updateList = (event) => {
        event.preventDefault();

        const list = {
            id: this.state.id,
            name: this.state.name,
            phone: this.state.phone,
            users: this.state.users,
        };
        this.props.updateList(list);
        setTimeout(() => {
            if (this.props.listObject.list != null) {
                this.setState({show: true});
                this.setState({message: "Список обновлен!"});
                setTimeout(() => this.setState({show: false}), 3000);
            } else {
                this.setState({show: false});
            }
        }, 2000);
        this.setState(this.initialListState);
        this.listList();
    };

    deleteList = (listId) => {
        this.props.deleteList(listId)
            .then(() => {
                    this.setState({show: true});
                    this.setState({message: "Список удален!"});
                    setTimeout(() => {
                        this.setState({show: false})
                    }, 3000);
                }
            )
            .catch((error) => {
                let errorMessage;
                if (error.response && error.response.data) errorMessage = error.response.data;
                else errorMessage = error.message;
                this.setState({show: true});
                this.setState({error: errorMessage})
            });
        // this.setState(this.initialListState);
    };

    listChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
    };

    listList = () => {
        return this.props.history.push("/lists");
    };

    searchUserChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
    };

    cancelUserSearch = () => {
        this.setState({search: ""});
    };

    addUserToList = () => {
        authUser.makeAPIRequest({
            method: 'get',
            url: "http://localhost:8765/user/" + this.state.search
            // + "/users"
        })
            .then((response) => {
                if (response.data.phone != localStorage.userPhone) {
                    if (this.state.id) this.props.addUserToList(this.state.id, response.data.phone);
                    this.setState(state => {
                        const list = state.users.concat(response.data);
                        return {users: list}
                    })
                }
                this.cancelUserSearch();
            })
            .catch((error) => {
                let errorMessage;
                if (error.response && error.response.data) errorMessage = error.response.data;
                else errorMessage = error.message;
                this.setState({show: true});
                this.setState({error: errorMessage})
            });
    };

    deleteUserFromList = (userPhone) => {
        if (this.state.id) this.props.deleteUserFromList(this.state.id, userPhone);
        this.setState(state => {
            const list = state.users.filter(item => item.phone !== userPhone);
            return {users: list}
        })
        this.cancelUserSearch();
    };

    render() {
        const {
            name, search
            // , coverPhotoURL, isbnNumber, price, language, genre
        } =
            this.state;

        return (
            <div>
                <div style={{display: this.state.show && !this.state.error ? "block" : "none"}}>
                    <MyToast
                        show={this.state.show}
                        message={this.state.message}
                        type={"success"}
                    />
                </div>
                {this.state.show && this.state.error && (
                    <Alert variant="danger" onClose={() => this.setState({show: false})} dismissible>
                        {this.state.error}
                    </Alert>
                )}

                {/*<div style={{display: this.state.show ? "block" : "none"}}>*/}
                {/*    <MyToast*/}
                {/*        show={this.state.show}*/}
                {/*        message={*/}
                {/*            this.state.method === "put"*/}
                {/*                ? "List Updated Successfully."*/}
                {/*                : "List Saved Successfully."*/}
                {/*        }*/}
                {/*        type={"success"}*/}
                {/*    />*/}
                {/*</div>*/}
                <Card className={"border border-dark bg-dark text-white"}>

                    <Form
                        onSubmit={this.state.id ? this.updateList : this.submitList}
                        onReset={this.resetList}
                        id="listFormId"
                    >
                        <Card.Header>
                            <FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare}/>{" "}
                            {this.state.id ? "Редактирование списка" : "Добавление списка"}
                        </Card.Header>
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col} controlId="formGridName">
                                    <Form.Control
                                        required
                                        autoComplete="off"
                                        type="text"
                                        name="name"
                                        value={name || ''}
                                        onChange={this.listChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Введите название списка"
                                    />
                                </Form.Group>
                            </Form.Row>
                            {/*<div style={{float: "left"}}>*/}
                            {/*    <FontAwesomeIcon icon={faList}/> Список продуктов*/}
                            {/*    <InputGroup size="sm">*/}
                            {/*        <FormControl*/}
                            {/*            placeholder="Добавить продукт"*/}
                            {/*            name="search"*/}
                            {/*            value={search || ''}*/}
                            {/*            type='int'*/}
                            {/*            className={"info-border bg-dark text-white"}*/}
                            {/*            onChange={this.searchUserChange}*/}
                            {/*        />*/}
                            {/*        <InputGroup.Append>*/}
                            {/*            <Button*/}
                            {/*                size="sm"*/}
                            {/*                variant="outline-info"*/}
                            {/*                type="button"*/}
                            {/*                onClick={this.searchUserData}*/}
                            {/*            >*/}
                            {/*                <FontAwesomeIcon icon={faSave}/>*/}
                            {/*            </Button>*/}
                            {/*            <Button*/}
                            {/*                size="sm"*/}
                            {/*                variant="outline-danger"*/}
                            {/*                type="button"*/}
                            {/*                onClick={this.cancelUserSearch}*/}
                            {/*            >*/}
                            {/*                <FontAwesomeIcon icon={faTimes}/>*/}
                            {/*            </Button>*/}

                            {/*        </InputGroup.Append>*/}
                            {/*    </InputGroup>*/}
                            {/*</div>*/}
                            {/*<div style={{float: "right"}}>*/}
                            <FontAwesomeIcon icon={faList}/> Список пользователей
                            <InputGroup size="sm">
                                <FormControl
                                    placeholder="Добавить пользователя"
                                    name="search"
                                    value={search || ''}
                                    type='int'
                                    className={"info-border bg-dark text-white"}
                                    onChange={this.searchUserChange}
                                />
                                <InputGroup.Append>
                                    <Button
                                        size="sm"
                                        variant="outline-info"
                                        type="button"
                                        onClick={this.addUserToList}
                                    >
                                        <FontAwesomeIcon icon={faSave}/>
                                    </Button>
                                    <Button
                                        size="sm"
                                        variant="outline-danger"
                                        type="button"
                                        onClick={this.cancelUserSearch}
                                    >
                                        <FontAwesomeIcon icon={faTimes}/>
                                    </Button>
                                </InputGroup.Append>
                            </InputGroup>

                            <Table bordered hover striped variant="dark">
                                <tbody>
                                {this.state.users.length === 0 ? (
                                    <tr align="center">
                                        <td colSpan="7">Список пользователей пуст</td>
                                    </tr>
                                ) : (
                                    this.state.users.map((user) => (
                                        <tr key={user.id}>
                                            <td style={{width: '90%'}}>{user.phone}</td>
                                            <td>
                                                <ButtonGroup>
                                                    <Button
                                                        size="sm"
                                                        variant="outline-danger"
                                                        onClick={() => this.deleteUserFromList(user.phone)}
                                                    >
                                                        <FontAwesomeIcon icon={faTrash}/>
                                                    </Button>
                                                </ButtonGroup>
                                            </td>
                                        </tr>
                                    ))
                                )}
                                </tbody>
                            </Table>
                            {/*</div>*/}
                        </Card.Body>
                        <Card.Footer style={{textAlign: "right"}}>
                            <Button size="sm" variant="success" type="submit">
                                <FontAwesomeIcon icon={faSave}/>{" "}
                                {/*{this.state.id ? "Изменить" : "Сохранить"}*/}
                            </Button>{" "}
                            <Button size="sm" variant="info" type="reset">
                                <FontAwesomeIcon icon={faUndo}/>
                            </Button>{" "}
                            <Button size="sm" variant="outline-danger" onClick={() => this.deleteList(this.state.id)}>
                                <FontAwesomeIcon icon={faTrash}/>
                            </Button>{" "}
                        </Card.Footer>

                    </Form>
                </Card>
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        listObject: state.list,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        findList: (listId) => dispatch(findList(listId)),
        saveList: (list) => dispatch(saveList(list)),
        updateList: (list) => dispatch(updateList(list)),
        deleteList: (listId) => dispatch(deleteList(listId)),
        addUserToList: (listId, userPhone) => dispatch(addUserToList(listId, userPhone)),
        deleteUserFromList: (listId, userPhone) => dispatch(deleteUserFromList(listId, userPhone))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(List);
