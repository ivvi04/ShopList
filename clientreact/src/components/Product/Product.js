import React, {Component} from "react";
import {connect} from "react-redux";
import {
    findProduct,
    saveProduct,
    updateProduct,
    deleteProduct,
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
    faSave,
    faTrash,
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";

class Product extends Component {
    constructor(props) {
        super(props);
        this.state = this.initialProductState;
    }

    initialProductState = {
        id: "",
        name: "",
        price: "",
        url: "",
        listId: "",
        image: "",
        purchased: false
    };

    componentDidMount() {
        const productId = this.props.match.params.id;
        if (productId) this.findProductById(productId);
    }

    findProductById = (productId) => {
        this.props.findProduct(productId)
            .then(() => {
                const product = this.props.productObject.product;
                if (product) {
                    this.setState({
                        id: product.id,
                        name: product.name,
                        price: product.price,
                        url: product.url,
                        listId: product.listId,
                        image: product.image,
                        purchased: product.purchased
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
    };

    resetProduct = () => {
        this.setState(() => this.initialProductState);
    };

    submitProduct = (event) => {
        event.preventDefault();

        const product = {
            id: this.state.id,
            name: this.state.name,
            price: this.state.price,
            url: this.state.url,
            listId: this.state.listId,
            image: this.state.image,
            purchased: this.state.purchased
        };

        this.props.saveProduct(product)
            .then(() => {
                const product = this.props.productObject.product;
                const error = this.props.productObject.error;
                if (product) {
                    this.setState({show: true});
                    this.setState({message: "Продукт добавлен!"});
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

    updateProduct = (event) => {
        event.preventDefault();

        const product = {
            id: this.state.id,
            name: this.state.name,
            price: this.state.price,
            url: this.state.url,
            listId: this.state.listId,
            image: this.state.image,
            purchased: this.state.purchased
        };

        this.props.updateProduct(product)
            .then(() => {
                    const error = this.props.productObject.error;
                    this.setState({show: true});
                    if (error) {
                        this.setState({error: error});
                        this.findProductById(this.state.id);
                    } else {
                        this.setState({message: "Продукт обновлен!"});
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

    deleteProduct = (productId) => {
        this.props.deleteProduct(productId)
            .then(() => {
                    const error = this.props.productObject.error;
                    this.setState({show: true});
                    if (error) this.setState({error: error});
                    else {
                        this.setState({message: "Продукт удален!"});
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

    productChange = (event) => {
        if (event.target.name === "purchased") {
            this.setState({
                [event.target.name]: event.target.checked,
            });
        } else if (event.target.name === "image") {
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
        this.props.history.push("/list/edit/" + this.state.listId);
    };

    render() {
        const {name, price, url, image, purchased} = this.state;

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
                        onSubmit={this.state.id ? this.updateProduct : this.submitProduct}
                        onReset={this.resetProduct}
                        id="productFormId"
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
                                        onClick={() => this.deleteProduct(this.state.id)}>
                                    <FontAwesomeIcon icon={faTrash}/>
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
                                                        maxWidth: "100%",
                                                        borderRadius: '10%',
                                                        overflow: 'hidden',
                                                        borderWidth: 3,
                                                        borderColor: 'grey',
                                                    }}
                                            />)
                                            : (
                                                <div>
                                                    <canvas
                                                        style={{
                                                            width: "300px",
                                                            height: "300px",
                                                            maxWidth: "100%",
                                                            maxHeight: "100%",
                                                            background: "grey",
                                                            borderRadius: '10%'
                                                        }}
                                                    />
                                                </div>
                                            )}
                                        <Form.Control
                                            autoComplete="off"
                                            type="file"
                                            name="image"
                                            onChange={this.productChange}
                                            className={"bg-dark text-white"}
                                            placeholder="Изображение продукта"
                                            custom="true"
                                            style={{width: "300px"}}
                                        />
                                    </Form.Group>
                                </Col>
                                <Col>
                                    <Form.Group as={Row} controlId="formGridName">
                                        <Form.Label column sm="3">Название продукта</Form.Label>
                                        <Col sm="9">
                                            <Form.Control
                                                required
                                                autoComplete="off"
                                                type="text"
                                                name="name"
                                                value={name || ''}
                                                onChange={this.productChange}
                                                className={"bg-dark text-white"}
                                                placeholder="Название продукта"
                                            />
                                        </Col>
                                    </Form.Group>
                                    <Form.Group as={Row} controlId="formGridPrice">
                                        <Form.Label column sm="3">Стоимость продукта</Form.Label>
                                        <Col sm="6">
                                            <Form.Control
                                                autoComplete="off"
                                                type="number"
                                                name="price"
                                                value={price || ''}
                                                onChange={this.productChange}
                                                className={"bg-dark text-white"}
                                                placeholder="Стоимость продукта"
                                            />
                                        </Col>
                                    </Form.Group>
                                    <Form.Group as={Row} controlId="formGridUrl">
                                        <Form.Label column sm="3">Ссылка</Form.Label>
                                        <Col sm="9">
                                            <Form.Control
                                                autoComplete="off"
                                                type="text"
                                                name="url"
                                                value={url || ''}
                                                onChange={this.productChange}
                                                className={"bg-dark text-white"}
                                                placeholder="Ссылка"
                                            />
                                        </Col>
                                    </Form.Group>
                                    <Form.Group as={Row} controlId="formGridPurchased">
                                        <Col sm="9">
                                            <Form.Check
                                                type="checkbox"
                                                name="purchased"
                                                custom={true}
                                                checked={purchased}
                                                onChange={this.productChange}
                                                className={"bg-dark text-white"}
                                                label="Куплен"
                                            />
                                        </Col>
                                    </Form.Group>
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
        productObject: state.product,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        findProduct: (productId) => dispatch(findProduct(productId)),
        saveProduct: (product) => dispatch(saveProduct(product)),
        updateProduct: (product) => dispatch(updateProduct(product)),
        deleteProduct: (productId) => dispatch(deleteProduct(productId)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(Product);
