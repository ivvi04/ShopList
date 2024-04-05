import React, {Component} from "react";

import {connect} from "react-redux";
import {deleteList, saveList} from "../../services/index";

import "./../../assets/css/Style.css";
import {
    Card,
    Table,
    ButtonGroup,
    Button,
    Alert, InputGroup, FormControl,
} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faEdit,
    faTrash,
    faPlus, faTimes,
} from "@fortawesome/free-solid-svg-icons";
import {Link} from "react-router-dom";
import MyToast from "../MyToast";
import * as authUser from "../../utils/authUser";

class ListList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            lists: [],
            searchList: "",
        };
    }

    componentDidMount() {
        this.findAllLists();
    }

    findAllLists() {
        authUser.makeAPIRequest({
            method: 'get',
            url: localStorage.addressIp + "/list/phone/" + localStorage.userPhone
        })
            .then((response) => {
                this.setState({lists: response.data});
            })
            .catch((error) => {
                let errorMessage;
                if (error.response && error.response.data) errorMessage = error.response.data;
                else errorMessage = error.message;
                this.setState({show: true});
                this.setState({error: errorMessage})
            });
    };

    searchChange = (event) => {
        const target = event.target;
        let value = target.value;
        this.setState({
            [target.name]: value,
        });
    };

    cancelSearch = (name) => {
        this.setState({
            [name]: "",
        });
    };

    addList = () => {
        const list = {
            name: this.state.searchList,
            phone: localStorage.userPhone,
            users: []
        };

        this.props.saveList(list)
            .then(() => {
                const list = this.props.listObject.list;
                const error = this.props.listObject.error;
                if (list) {
                    this.setState(state => {
                        const newList = state.lists.concat(list);
                        return {lists: newList}
                    });
                }
                this.setState({show: true});
                if (error) this.setState({error: error});
                else this.setState({message: "Список добавлен!"});
                setTimeout(() => this.setState({show: false}), 3000);
                this.cancelSearch("searchList");
            })
            .catch((error) => {
                this.setState({show: true});
                this.setState({error: error.message})
                setTimeout(() => this.setState({show: false}), 3000);
            });
    };

    deleteList = (listId) => {
        this.props.deleteList(listId)
            .then(() => {
                    const error = this.props.listObject.error;
                    this.setState({show: true});
                    if (error) this.setState({error: error});
                    else this.setState({message: "Список удален!"});
                    setTimeout(() => this.setState({show: false}), 3000);
                    this.findAllLists();
                }
            )
            .catch((error) => {
                this.setState({show: true});
                this.setState({error: error.message})
                setTimeout(() => this.setState({show: false}), 3000);
            });
    };

    render() {
        const {lists, searchList} = this.state;

        return (
            <div align={'center'}>
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
                <Card className={"border border-dark bg-dark text-white"} style={{width: '70%'}}>

                    <Card.Header style={{textAlign: "right", height: "50px"}}>
                        <InputGroup size="sm">
                            <FormControl
                                placeholder="Введите название списка"
                                name="searchList"
                                value={searchList || ''}
                                type='text'
                                className={"info-border bg-dark text-white"}
                                onChange={this.searchChange}
                            />
                            <InputGroup.Append>
                                <Button
                                    size="sm"
                                    variant="outline-success"
                                    type="button"
                                    onClick={this.addList}
                                >
                                    <FontAwesomeIcon icon={faPlus}/>
                                </Button>
                                <Button
                                    size="sm"
                                    variant="outline-warning"
                                    type="button"
                                    onClick={() => this.cancelSearch("searchList")}
                                >
                                    <FontAwesomeIcon icon={faTimes}/>
                                </Button>

                            </InputGroup.Append>
                        </InputGroup>
                    </Card.Header>
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            <tbody>
                            {lists.length === 0 ? (
                                <tr align="center">
                                    <td colSpan="7">Нет доступных списков</td>
                                </tr>
                            ) : (
                                lists.map((list) => (
                                    <tr key={list.id}>
                                        <td style={{width: '90%'}}>
                                            {list.name}
                                        </td>
                                        <td>
                                            <ButtonGroup>
                                                <Link
                                                    to={"/list/edit/" + list.id}
                                                    className="btn btn-sm btn-outline-primary"
                                                >
                                                    <FontAwesomeIcon icon={faEdit}/>
                                                </Link>{" "}
                                                <Button
                                                    size="sm"
                                                    variant="outline-danger"
                                                    onClick={() => this.deleteList(list.id)}
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
                    </Card.Body>
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
        saveList: (list) => dispatch(saveList(list)),
        deleteList: (listId) => dispatch(deleteList(listId)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(ListList);
