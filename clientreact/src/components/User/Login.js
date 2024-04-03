import React, {useState} from "react";
import {useDispatch} from "react-redux";
import {
    Row,
    Col,
    Card,
    Form,
    InputGroup,
    FormControl,
    Button,
    Alert,
} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faSignInAlt,
    faLock,
    faUndo,
    faPhone,
} from "@fortawesome/free-solid-svg-icons";
import {authenticateUser} from "../../services/index";
import MyToast from "../MyToast";

const Login = (props) => {
    const [error, setError] = useState();
    const [show, setShow] = useState(false);

    const initialState = {
        phone: "",
        password: "",
    };

    const [user, setUser] = useState(initialState);

    const credentialChange = (event) => {
        const {name, value} = event.target;
        setUser({...user, [name]: value});
    };

    const dispatch = useDispatch();

    const validateUser = () => {
        dispatch(authenticateUser(user.phone, user.password))
            .then(() => {
                setShow(true);
                setTimeout(() => {
                    props.history.push("/lists");
                }, 1000);
            })
            .catch((error) => {
                console.log(error.message);
                setShow(true);
                resetLoginForm();
                setError("Неверный логин и пароль!");
            });
    };

    const resetLoginForm = () => {
        setUser(initialState);
    };

    return (
        <Row className="justify-content-md-center">
            <Col xs={5}>
                {/*<div style={{display: show ? "block" : "none"}}>*/}
                {/*    <MyToast show={show} message={props.message} type={"success"}/>*/}
                {/*</div>*/}
                <div style={{display: show && !error ? "block" : "none"}}>
                    <MyToast
                        show={show}
                        message={props.message}
                        type={"success"}
                    />
                </div>
                {/*{show && props.message && (*/}
                {/*    <Alert variant="success" onClose={() => setShow(false)} dismissible>*/}
                {/*        {props.message}*/}
                {/*    </Alert>*/}
                {/*)}*/}
                {show && error && (
                    <Alert variant="danger" onClose={() => setShow(false)} dismissible>
                        {error}
                    </Alert>
                )}
                {/*{this.state.show && this.state.error && (*/}
                {/*    <Alert variant="danger" onClose={() => this.setState({show: false})} dismissible>*/}
                {/*        {this.state.error}*/}
                {/*    </Alert>*/}
                {/*)}*/}
                <Card className={"border border-dark bg-dark text-white"}>
                    <Card.Header>
                        <FontAwesomeIcon icon={faSignInAlt}/> Авторизация
                    </Card.Header>
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <InputGroup>
                                    <InputGroup.Prepend>
                                        <InputGroup.Text>
                                            <FontAwesomeIcon icon={faPhone}/>
                                        </InputGroup.Text>
                                    </InputGroup.Prepend>
                                    <FormControl
                                        required
                                        autoComplete="off"
                                        type="text"
                                        name="phone"
                                        value={user.phone}
                                        onChange={credentialChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Введите номер телефона"
                                    />
                                </InputGroup>
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col}>
                                <InputGroup>
                                    <InputGroup.Prepend>
                                        <InputGroup.Text>
                                            <FontAwesomeIcon icon={faLock}/>
                                        </InputGroup.Text>
                                    </InputGroup.Prepend>
                                    <FormControl
                                        required
                                        autoComplete="off"
                                        type="password"
                                        name="password"
                                        value={user.password}
                                        onChange={credentialChange}
                                        className={"bg-dark text-white"}
                                        placeholder="Введите пароль"
                                    />
                                </InputGroup>
                            </Form.Group>
                        </Form.Row>
                    </Card.Body>
                    <Card.Footer style={{textAlign: "right"}}>
                        <Button
                            size="sm"
                            type="button"
                            variant="success"
                            onClick={validateUser}
                            disabled={user.phone.length === 0 || user.password.length === 0}
                        >
                            <FontAwesomeIcon icon={faSignInAlt}/>
                        </Button>{" "}
                        <Button
                            size="sm"
                            type="button"
                            variant="info"
                            onClick={resetLoginForm}
                            disabled={user.phone.length === 0 && user.password.length === 0}
                        >
                            <FontAwesomeIcon icon={faUndo}/>
                        </Button>
                    </Card.Footer>
                </Card>
            </Col>
        </Row>
    );
};

export default Login;
