import React, {Component} from "react";
import {connect} from "react-redux";
import {
    findList,
    saveList,
    updateList,
    deleteList,
    addUser,
    deleteUser,
    saveProduct,
    updateProduct,
    deleteProduct, deleteAllPurchasedProducts
} from "../../services/index";

import {Alert, Button, ButtonGroup, Card, Row, Col, Form, FormControl, InputGroup, Table} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faBackward,
    faEdit,
    faList,
    faPlus,
    faPlusSquare,
    faSave,
    faTimes,
    faTrash,
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";
import * as authUser from "../../utils/authUser";
import {Link} from "react-router-dom";

class List extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialListState;
        this.state = {
            searchProduct: "",
            products: [],
            searchUser: "",
            users: []
        };
    }

    initialListState = {
        id: "",
        name: "",
        phone: localStorage.userPhone,
        products: [],
        users: []
    };

    componentDidMount() {
        const listId = this.props.match.params.id;
        if (listId) this.findListById(listId);
    }

    findListById = (listId) => {
        this.props.findList(listId)
            .then(() => {
                const list = this.props.listObject.list;
                if (list) {
                    this.setState({
                        id: list.id,
                        name: list.name,
                        phone: list.phone,
                        users: list.users
                    });

                    authUser.makeAPIRequest({
                        method: 'get',
                        url: localStorage.addressIp + "/product/" + listId + "/products"
                    })
                        .then((response) => {
                            this.setState({
                                products: response.data
                            })
                        })
                } else {
                    const error = this.props.listObject.error;
                    if (error) {
                        this.setState({show: true});
                        this.setState({error: error});
                        setTimeout(() => this.setState({show: false}), 3000);
                    }
                }
            })
            .catch((error) => {
                this.setState({show: true});
                this.setState({error: error.message})
                setTimeout(() => this.setState({show: false}), 3000);
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
                const list = this.props.listObject.list;
                const error = this.props.listObject.error;
                if (list) {
                    this.setState({show: true});
                    this.setState({message: "Список добавлен!"});
                    this.toList();
                }
                if (error) {
                    this.setState({show: true});
                    this.setState({error: error});
                }
                setTimeout(() => this.setState({show: false}), 3000);
            })
            .catch((error) => {
                this.setState({show: true});
                this.setState({error: error.message})
                setTimeout(() => this.setState({show: false}), 3000);
            })
    };

    updateList = (event) => {
        event.preventDefault();

        const list = {
            id: this.state.id,
            name: this.state.name,
            phone: this.state.phone,
            users: this.state.users,
        };
        this.props.updateList(list)
            .then(() => {
                    const error = this.props.listObject.error;
                    this.setState({show: true});
                    if (error) this.setState({error: error});
                    else this.setState({message: "Список обновлен!"});
                    this.findListById(this.state.id);
                    setTimeout(() => this.setState({show: false}), 3000);
                }
            )
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
                    else {
                        this.setState({message: "Список удален!"});
                        this.toList();
                    }
                    setTimeout(() => this.setState({show: false}), 3000);
                }
            )
            .catch((error) => {
                this.setState({show: true});
                this.setState({error: error.message})
                setTimeout(() => this.setState({show: false}), 3000);
            });
    };

    listChange = (event) => {
        this.setState({
            [event.target.name]: event.target.value,
        });
    };

    toList = () => {
        this.props.history.push("/list");
    };

    searchChange = (event) => {
        const target = event.target;
        let value = target.value;
        if (target.name === "searchUser") value = target.value.slice(0, 11);

        this.setState({
            [target.name]: value,
        });
    };

    cancelSearch = (name) => {
        this.setState({
            [name]: "",
        });
    };

    addUser = () => {
        authUser.makeAPIRequest({
            method: 'get',
            url: localStorage.addressIp + "/user/" + this.state.searchUser
        })
            .then((response) => {
                const filterList = this.state.users.filter(item => item.phone === response.data.phone);
                if (filterList.length === 0 && response.data.phone !== localStorage.userPhone) {
                    this.props.addUser(this.state.id, response.data.phone)
                        .then(() => {
                            const error = this.props.listObject.error;
                            if (error) {
                                this.setState({show: true});
                                this.setState({error: error});
                                setTimeout(() => this.setState({show: false}), 3000);
                            } else {
                                this.setState(state => {
                                    const list = state.users.concat(response.data);
                                    return {users: list}
                                })
                            }
                        })
                        .catch((error) => {
                            this.setState({show: true});
                            this.setState({error: error.message})
                            setTimeout(() => this.setState({show: false}), 3000);
                        })
                }
                this.cancelSearch("searchUser");
            })
            .catch((error) => {
                let errorMessage;
                if (error.response && error.response.data) errorMessage = error.response.data;
                else errorMessage = error.message;
                this.setState({show: true});
                this.setState({error: errorMessage});
                this.cancelSearch("searchUser");
                setTimeout(() => this.setState({show: false}), 3000);
            });
    };

    deleteUser = (userPhone) => {
        if (this.state.id) this.props.deleteUser(this.state.id, userPhone)
            .then(() => {
                const error = this.props.listObject.error;
                if (error) {
                    this.setState({show: true});
                    this.setState({error: error});
                    setTimeout(() => this.setState({show: false}), 3000);
                } else {
                    this.setState(state => {
                        const filterList = state.users.filter(item => item.phone !== userPhone);
                        return {users: filterList}
                    })
                    const filterList = this.state.users.filter(item => item.phone === userPhone);
                    if (filterList.length === 0 && this.state.phone !== localStorage.userPhone) this.toList();
                }
            })
            .catch((error) => {
                this.setState({show: true});
                this.setState({error: error.message})
                setTimeout(() => this.setState({show: false}), 3000);
            })
    };

    addProduct = (event) => {
        event.preventDefault();
        const product = {
            name: this.state.searchProduct,
            price: null,
            url: null,
            listId: this.state.id,
            image: null,
            purchased: false
        };
        authUser.makeAPIRequest({
            method: 'get',
            url: localStorage.addressIp + "/product/" + this.state.id + "/products"
        })
            .then((response) => {
                const filterList = response.data.filter(item => item.name === this.state.searchProduct);
                if (filterList.length === 0) {
                    this.props.saveProduct(product)
                        .then(() => {
                            const newProduct = this.props.productObject.product;
                            if (newProduct) {
                                this.setState(state => {
                                    const list = state.products.concat(newProduct);
                                    return {products: list}
                                })
                            } else {
                                const error = this.props.productObject.error;
                                if (error) {
                                    this.setState({show: true});
                                    this.setState({error: error});
                                    setTimeout(() => this.setState({show: false}), 3000);
                                }
                            }
                        })
                        .catch((error) => {
                            this.setState({show: true});
                            this.setState({error: error.message})
                            setTimeout(() => this.setState({show: false}), 3000);
                        })
                }
                this.cancelSearch("searchProduct");
            })
            .catch((error) => {
                let errorMessage;
                if (error.response && error.response.data) errorMessage = error.response.data;
                else errorMessage = error.message;
                this.setState({show: true});
                this.setState({error: errorMessage})
                this.cancelSearch("searchProduct");
                setTimeout(() => this.setState({show: false}), 3000);
            })
    }

    deleteProduct = (productId) => {
        if (this.state.id) this.props.deleteProduct(productId)
            .then(() => {
                const error = this.props.productObject.error;
                if (error) {
                    this.setState({show: true});
                    this.setState({error: error});
                    setTimeout(() => this.setState({show: false}), 3000);
                } else {
                    this.setState(state => {
                        const filterList = state.products.filter(item => item.id !== productId);
                        return {products: filterList}
                    })
                }
            })
            .catch((error) => {
                this.setState({show: true});
                this.setState({error: error.message})
                setTimeout(() => this.setState({show: false}), 3000);
            })
    };

    deleteAllPurchasedProducts = () => {
        if (this.state.id) this.props.deleteAllPurchasedProducts(this.state.id)
            .then(() => {
                const error = this.props.productObject.error;
                if (error) {
                    this.setState({show: true});
                    this.setState({error: error});
                    setTimeout(() => this.setState({show: false}), 3000);
                } else {
                    this.setState(state => {
                        const filterList = state.products.filter(item => !item.purchased);
                        return {products: filterList}
                    })
                }
            })
            .catch((error) => {
                this.setState({show: true});
                this.setState({error: error.message})
                setTimeout(() => this.setState({show: false}), 3000);
            })
    };

    productPurchased = (productId, purchased) => {
        this.setState(state => {
            const list = state.products.map(product => {
                if (product.id === productId) {
                    product.purchased = purchased;
                    this.props.updateProduct(product);
                    return product;
                } else return product;
            })
            return {products: list}
        })
    };

    render() {
        const {name, searchProduct, products, searchUser, users} = this.state;

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

                <Card className={"border border-dark bg-dark text-white"}>
                    <Form
                        onSubmit={this.state.id ? this.updateList : this.submitList}
                        onReset={this.resetList}
                        id="listFormId"
                    >
                        <Card.Header style={{height: "50px"}}>
                            <div style={{float: "left"}}>
                                <FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare}/>{" "}
                                {this.state.id ? "Редактирование" : "Добавление"}

                            </div>
                            <div style={{float: "right"}}>
                                <Button size="sm" variant="outline-info" onClick={this.toList}>
                                    <FontAwesomeIcon icon={faBackward}/>
                                </Button>{" "}
                                <Button size="sm" variant="success" type="submit">
                                    <FontAwesomeIcon icon={faSave}/>{" "}
                                </Button>{" "}
                                <Button size="sm" variant="outline-danger"
                                        onClick={() => this.deleteList(this.state.id)}>
                                    <FontAwesomeIcon icon={faTrash}/>
                                </Button>{" "}
                            </div>
                        </Card.Header>
                        <Card.Body>
                            <Row>
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
                            </Row>
                            <Row>
                                <Col>
                                    {/*<div style={{float: "left"}}>*/}
                                    <FontAwesomeIcon icon={faList}/> Список продуктов
                                    <InputGroup size="sm">
                                        <FormControl
                                            placeholder="Добавить продукт"
                                            name="searchProduct"
                                            value={searchProduct || ''}
                                            type='text'
                                            className={"info-border bg-dark text-white"}
                                            onChange={this.searchChange}
                                        />
                                        <InputGroup.Append>
                                            <Button
                                                size="sm"
                                                variant="outline-success"
                                                type="button"
                                                onClick={this.addProduct}
                                            >
                                                <FontAwesomeIcon icon={faPlus}/>
                                            </Button>
                                            <Button
                                                size="sm"
                                                variant="outline-warning"
                                                type="button"
                                                onClick={() => this.cancelSearch("searchProduct")}
                                            >
                                                <FontAwesomeIcon icon={faTimes}/>
                                            </Button>
                                            <Button
                                                size="sm"
                                                variant="outline-danger"
                                                type="button"
                                                onClick={() => this.deleteAllPurchasedProducts()}
                                            >
                                                <FontAwesomeIcon icon={faTrash}/>
                                            </Button>
                                        </InputGroup.Append>
                                    </InputGroup>

                                    <Table bordered hover striped variant="dark">
                                        <tbody>
                                        {!products || products.length === 0 ? (
                                            <tr align="center">
                                                <td colSpan="7">Список продуктов пуст</td>
                                            </tr>
                                        ) : (
                                            products.map((product) => (
                                                <tr key={product.id}>
                                                    <td style={{width: '10%'}}>
                                                        <input type="checkbox"
                                                               checked={product.purchased}
                                                               onChange={() => this.productPurchased(product.id, !(product.purchased))}
                                                    /></td>
                                                    <td style={{width: '70%'}}>{product.name}</td>
                                                    <td style={{width: '10%'}}>{product.url ? (
                                                        <a href={product.url} target="noopener noreferrer">Ссылка</a>) : ("")}</td>
                                                    <td style={{width: '10%'}}>
                                                        <ButtonGroup>
                                                            <Link
                                                                to={"/product/edit/" + product.id}
                                                                className="btn btn-sm btn-outline-primary"
                                                            >
                                                                <FontAwesomeIcon icon={faEdit}/>
                                                            </Link>{" "}
                                                            <Button
                                                                size="sm"
                                                                variant="outline-danger"
                                                                onClick={() => this.deleteProduct(product.id)}
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
                                </Col>
                                <Col>
                                    <FontAwesomeIcon icon={faList}/> Список пользователей
                                    <InputGroup size="sm">
                                        <FormControl
                                            placeholder="Добавить пользователя по номеру телефона"
                                            name="searchUser"
                                            value={searchUser || ''}
                                            type='number'
                                            className={"info-border bg-dark text-white"}
                                            onChange={this.searchChange}
                                            maxLength={10}
                                        />
                                        <InputGroup.Append>
                                            <Button
                                                size="sm"
                                                variant="outline-success"
                                                type="button"
                                                onClick={this.addUser}
                                            >
                                                <FontAwesomeIcon icon={faPlus}/>
                                            </Button>
                                            <Button
                                                size="sm"
                                                variant="outline-warning"
                                                type="button"
                                                onClick={() => this.cancelSearch("searchUser")}
                                            >
                                                <FontAwesomeIcon icon={faTimes}/>
                                            </Button>
                                        </InputGroup.Append>
                                    </InputGroup>

                                    <Table bordered hover striped variant="dark">
                                        <tbody>
                                        {!users || users.length === 0 ? (
                                            <tr align="center">
                                                <td colSpan="7">Список пользователей пуст</td>
                                            </tr>
                                        ) : (
                                            users.map((user) => (
                                                <tr key={user.id}>
                                                    <td style={{width: '40%'}}>{user.username}</td>
                                                    <td style={{width: '60%'}}>{user.phone}</td>
                                                    <td style={{width: '50px'}}>
                                                        <ButtonGroup>
                                                            <Button
                                                                size="sm"
                                                                variant="outline-danger"
                                                                onClick={() => this.deleteUser(user.phone)}
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
                                </Col>
                            </Row>
                        </Card.Body>
                        <Card.Footer style={{textAlign: "right"}}>
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
        productObject: state.product,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        findList: (listId) => dispatch(findList(listId)),
        saveList: (list) => dispatch(saveList(list)),
        updateList: (list) => dispatch(updateList(list)),
        deleteList: (listId) => dispatch(deleteList(listId)),
        addUser: (listId, userPhone) => dispatch(addUser(listId, userPhone)),
        deleteUser: (listId, userPhone) => dispatch(deleteUser(listId, userPhone)),
        saveProduct: (product) => dispatch(saveProduct(product)),
        updateProduct: (product) => dispatch(updateProduct(product)),
        deleteProduct: (productId) => dispatch(deleteProduct(productId)),
        deleteAllPurchasedProducts: (listId) => dispatch(deleteAllPurchasedProducts(listId))
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(List);
