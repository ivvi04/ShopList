import React, {useState} from "react";
import {useDispatch} from "react-redux";
import {
    Row,
    Col,
    Card,
    Form,
    InputGroup,
    FormControl,
    Button, Alert,
} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faPhone,
    faEnvelope,
    faLock,
    faUndo,
    faUserPlus,
    faUser,
} from "@fortawesome/free-solid-svg-icons";
import {registerUser} from "../../services/index";
import MyToast from "../MyToast";

const Register = (props) => {
    const [error, setError] = useState();
    const [show, setShow] = useState(false);
    // const [message] = useState(""); //setMessage

    const initialState = {
        username: "",
        password: "",
        phone: "",
        email: "",
    };

    const [user, setUser] = useState(initialState);

    const userChange = (event) => {
        const {name, value} = event.target;
        setUser({...user, [name]: value});
    };

    const dispatch = useDispatch();

    const saveUser = () => {
        dispatch(registerUser(user))
            .then(() => {
                setShow(true);
                setTimeout(() => {
                    return props.history.push("/lists");
                }, 1000);
            })
            .catch((error) => {
                setShow(true);
                if (error.response && error.response.data) setError(error.response.data);
                else setError(error.message);
            });
    };

    const resetRegisterForm = () => {
        setUser(initialState);
    };

    return (
        <div>
            <div style={{display: show ? "block" : "none"}}>
                <MyToast show={show} message={props.message} type={"success"}/>
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
            <Row className="justify-content-md-center">
                <Col xs={5}>
                    <Card className={"border border-dark bg-dark text-white"}>
                        <Card.Header>
                            <FontAwesomeIcon icon={faUserPlus}/> Регистрация
                        </Card.Header>
                        <Card.Body>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text>
                                                <FontAwesomeIcon icon={faUser}/>
                                            </InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl
                                            autoComplete="off"
                                            type="text"
                                            name="username"
                                            value={user.username}
                                            onChange={userChange}
                                            className={"bg-dark text-white"}
                                            placeholder="Введите имя пользователя"
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
                                            onChange={userChange}
                                            className={"bg-dark text-white"}
                                            placeholder="Введите пароль"
                                        />
                                    </InputGroup>
                                </Form.Group>
                            </Form.Row>
                            <Form.Row>
                                <Form.Group as={Col}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text>
                                                <FontAwesomeIcon icon={faPhone}/>
                                            </InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl
                                            autoComplete="off"
                                            type="text"
                                            name="phone"
                                            value={user.phone}
                                            onChange={userChange}
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
                                                <FontAwesomeIcon icon={faEnvelope}/>
                                            </InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <FormControl
                                            required
                                            autoComplete="off"
                                            type="text"
                                            name="email"
                                            value={user.email}
                                            onChange={userChange}
                                            className={"bg-dark text-white"}
                                            placeholder="Введите адрес электронной почты"
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
                                onClick={saveUser}
                                disabled={user.email.length === 0 || user.password.length === 0}
                            >
                                <FontAwesomeIcon icon={faUserPlus}/>
                            </Button>{" "}
                            <Button
                                size="sm"
                                type="button"
                                variant="info"
                                onClick={resetRegisterForm}
                            >
                                <FontAwesomeIcon icon={faUndo}/>
                            </Button>
                        </Card.Footer>
                    </Card>
                </Col>
            </Row>
        </div>
    );
};

export default Register;
