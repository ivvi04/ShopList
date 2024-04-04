import React, {Component} from "react";
import {connect} from "react-redux";
import {
    findUser,
    updateUser,
    changePassword,
} from "../../services/index";

import {
    Alert,
    Button,
    Card,
    Row,
    Col,
    Form, Image,
} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faBackward,
    faEdit,
    faPlusSquare,
    faSave
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";

class User extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialUserState;
        this.state = {
            oldPassword: "",
            password: "",
            confirmPassword: ""
        };
    }

    initialUserState = {
        id: "",
        username: "",
        phone: "",
        email: "",
        image: ""
    };

    componentDidMount() {
        this.findUserByPhone(localStorage.userPhone);
    }

    findUserByPhone = (userPhone) => {
        this.props.findUser(userPhone)
            .then(() => {
                const user = this.props.userObject.user;
                if (user) {
                    this.setState({
                        id: user.id,
                        username: user.username,
                        phone: user.phone,
                        email: user.email,
                        image: user.image
                    })
                } else {
                    const error = this.props.userObject.error;
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

    resetUser = () => {
        this.setState(() => this.initialUserState);
    };

    updateUser = (event) => {
        event.preventDefault();

        const user = {
            id: this.state.id,
            username: this.state.username,
            phone: this.state.phone,
            email: this.state.email,
            image: this.state.image
        };

        this.props.updateUser(user)
            .then(() => {
                    const error = this.props.userObject.error;
                    this.setState({show: true});
                    if (error) {
                        this.setState({error: error});
                        this.findUserByPhone(this.state.phone);
                    } else {
                        this.setState({message: "Пользователь обновлен!"});
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

    changePassword = (event) => {
        event.preventDefault();

        this.props.changePassword(this.state.phone,
            this.state.oldPassword, this.state.password, this.state.confirmPassword)
            .then(() => {
                    const error = this.props.userObject.error;
                    this.setState({show: true});
                    if (error) {
                        this.setState({error: error});
                        this.findUserByPhone(this.state.phone);
                    } else {
                        this.setState({
                            oldPassword: "",
                            password: "",
                            confirmPassword: "",
                            message: "Пароль успешно изменен!"});
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

    userChange = (event) => {
        if (event.target.name === "image") {
            const file = event.target.files[0];
            const reader = new FileReader();
            reader.readAsDataURL(file);
            reader.onload = () => {
                this.setState({
                    [event.target.name]: reader.result,
                });
            };
        } else {
            this.setState({
                [event.target.name]: event.target.value,
            });
        }
    };

    toList = () => {
        this.props.history.push("/list");
    };

    render() {
        const {username, phone, email, image, oldPassword, password, confirmPassword} = this.state;

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
                        onSubmit={this.updateUser}
                        onReset={this.resetUser}
                        id="userFormId"
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
                            </div>
                        </Card.Header>
                        <Card.Body>
                            <Row>
                                <Col xs={6} md={4}>
                                    <Form.Group controlId="formGridImage">
                                        {image ?
                                            (<Image alt="preview image" src={image}
                                                    style={{
                                                        width: "300px",
                                                        borderRadius: '50%',
                                                        overflow: 'hidden',
                                                        borderWidth: 3,
                                                        borderColor: 'grey',
                                                    }}
                                            />)
                                            : (
                                                <div>
                                                    <canvas
                                                        // ref={image}
                                                        style={{
                                                            width: "300px",
                                                            height: "300px",
                                                            background: "grey",
                                                            borderRadius: '50%'
                                                        }}
                                                    />
                                                </div>
                                            )}

                                        <Form.Control
                                            autoComplete="off"
                                            type="file"
                                            name="image"
                                            onChange={this.userChange}
                                            className={"bg-dark text-white"}
                                            placeholder="Изображение пользователя"
                                            custom="true"
                                            style={{width: "300px"}}
                                        />
                                    </Form.Group>
                                </Col>
                                <Col>
                                    <Form.Group as={Row} controlId="formGridName">
                                        <Form.Label column sm="3">Имя пользователя</Form.Label>
                                        <Col sm="9">
                                            <Form.Control
                                                required
                                                autoComplete="off"
                                                type="text"
                                                name="username"
                                                value={username || ''}
                                                onChange={this.userChange}
                                                className={"bg-dark text-white"}
                                                placeholder="Имя пользователя"
                                            />
                                        </Col>
                                    </Form.Group>
                                    <Form.Group as={Row} controlId="formGridPrice">
                                        <Form.Label column sm="3">Номер телефона</Form.Label>
                                        <Col sm="6">
                                            <Form.Control
                                                disabled={true}
                                                autoComplete="off"
                                                type="number"
                                                name="phone"
                                                value={phone || ''}
                                                onChange={this.userChange}
                                                className={"bg-dark text-white"}
                                                placeholder="Номер телефона"
                                            />
                                        </Col>
                                    </Form.Group>
                                    <Form.Group as={Row} controlId="formGridUrl">
                                        <Form.Label column sm="3">Email</Form.Label>
                                        <Col sm="9">
                                            <Form.Control
                                                autoComplete="off"
                                                type="text"
                                                name="email"
                                                value={email || ''}
                                                onChange={this.userChange}
                                                className={"bg-dark text-white"}
                                                placeholder="Email"
                                            />
                                        </Col>
                                    </Form.Group>
                                    <hr/>
                                    <Form.Group as={Row} controlId="oldPassword">
                                        <Form.Label column sm="3">Старый пароль</Form.Label>
                                        <Col sm="9">
                                            <Form.Control
                                                type="password"
                                                name="oldPassword"
                                                value={oldPassword}
                                                onChange={this.userChange}
                                                className={"bg-dark text-white"}
                                                placeholder="Старый пароль"
                                            />
                                        </Col>
                                    </Form.Group>
                                    <Form.Group as={Row} controlId="password">
                                        <Form.Label column sm="3">Новый пароль</Form.Label>
                                        <Col sm="9">
                                            <Form.Control
                                                type="password"
                                                name="password"
                                                value={password}
                                                onChange={this.userChange}
                                                className={"bg-dark text-white"}
                                                placeholder="Новый пароль"
                                            />
                                        </Col>
                                    </Form.Group>
                                    <Form.Group as={Row} controlId="confirmPassword">
                                        <Form.Label column sm="3">Подтвердите пароль</Form.Label>
                                        <Col sm="9">
                                            <Form.Control
                                                type="password"
                                                name="confirmPassword"
                                                value={confirmPassword}
                                                onChange={this.userChange}
                                                className={"bg-dark text-white"}
                                                placeholder="Подтвердите пароль"
                                            />
                                        </Col>
                                    </Form.Group>
                                    <Button
                                        block
                                        type="submit"
                                        onClick={this.changePassword}
                                    > Сменить пароль
                                    </Button>
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
        userObject: state.user,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        findUser: (userPhone) => dispatch(findUser(userPhone)),
        updateUser: (user) => dispatch(updateUser(user)),
        changePassword: (userPhone, oldPassword, password, confirmPassword) =>
            dispatch(changePassword(userPhone, oldPassword, password, confirmPassword)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(User);
