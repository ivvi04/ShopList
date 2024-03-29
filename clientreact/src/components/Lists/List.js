import React, { Component } from "react";

import { connect } from "react-redux";
import {
  saveList,
  fetchList,
  updateList,
  fetchLanguages,
  fetchGenres,
} from "../../services/index";

import { Card, Form, Button, Col
  // , InputGroup, Image
} from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faSave,
  faPlusSquare,
  faUndo,
  faList,
  faEdit,
} from "@fortawesome/free-solid-svg-icons";
import MyToast from "../MyToast";

class List extends Component {
  constructor(props) {
    super(props);
    this.state = this.initialState;
    this.state = {
      // genres: [],
      // languages: [],
      show: false,
    };
  }

  initialState = {
    id: "",
    name: "",
    status: ""
    // coverPhotoURL: "",
    // isbnNumber: "",
    // price: "",
    // language: "",
    // genre: "",
  };

  componentDidMount() {
    const listId = +this.props.match.params.id;
    if (listId) this.findListById(listId);
  }

  // findAllLanguages = () => {
  //   this.props.fetchLanguages();
  //   setTimeout(() => {
  //     let listLanguages = this.props.listObject.languages;
  //     if (listLanguages) {
  //       this.setState({
  //         languages: [{ value: "", display: "Select Language" }].concat(
  //           listLanguages.map((language) => {
  //             return { value: language, display: language };
  //           })
  //         ),
  //       });
  //       this.findAllGenres();
  //     }
  //   }, 100);
  // };
  //
  // findAllGenres = () => {
  //   this.props.fetchGenres();
  //   setTimeout(() => {
  //     let listGenres = this.props.listObject.genres;
  //     if (listGenres) {
  //       this.setState({
  //         genres: [{ value: "", display: "Select Genre" }].concat(
  //           listGenres.map((genre) => {
  //             return { value: genre, display: genre };
  //           })
  //         ),
  //       });
  //     }
  //   }, 100);
  // };

  findListById = (listId) => {
    this.props.fetchList(listId)
        // .then((response) => {
        //   console(response.data);
        // })
    ;
    setTimeout(() => {
      console.log(this.props.list);
      let list = this.props.listObject.list;
      if (list != null) {
        this.setState({
          id: list.id,
          name: list.name,
          status: list.status,
          // coverPhotoURL: list.coverPhotoURL,
          // isbnNumber: list.isbnNumber,
          // price: list.price,
          // language: list.language,
          // genre: list.genre,
        });
      }
    }, 1000);
  };

  resetList = () => {
    this.setState(() => this.initialState);
  };

  submitList = (event) => {
    event.preventDefault();

    const list = {
      name: this.state.name,
      status: this.state.status,
      // coverPhotoURL: this.state.coverPhotoURL,
      // isbnNumber: this.state.isbnNumber,
      // price: this.state.price,
      // language: this.state.language,
      // genre: this.state.genre,
    };

    this.props.saveList(list);
    setTimeout(() => {
      if (this.props.listObject.list != null) {
        this.setState({ show: true, method: "post" });
        setTimeout(() => this.setState({ show: false }), 3000);
      } else {
        this.setState({ show: false });
      }
    }, 2000);
    this.setState(this.initialState);
  };

  updateList = (event) => {
    event.preventDefault();

    const list = {
      id: this.state.id,
      name: this.state.name,
      status: this.state.status,
      // coverPhotoURL: this.state.coverPhotoURL,
      // isbnNumber: this.state.isbnNumber,
      // price: this.state.price,
      // language: this.state.language,
      // genre: this.state.genre,
    };
    this.props.updateList(list);
    setTimeout(() => {
      if (this.props.listObject.list != null) {
        this.setState({ show: true, method: "put" });
        setTimeout(() => this.setState({ show: false }), 3000);
      } else {
        this.setState({ show: false });
      }
    }, 2000);
    this.setState(this.initialState);
  };

  listChange = (event) => {
    this.setState({
      [event.target.name]: event.target.value,
    });
  };

  listList = () => {
    return this.props.history.push("/list");
  };

  render() {
    const { name, status
      // , coverPhotoURL, isbnNumber, price, language, genre
    } =
      this.state;

    return (
      <div>
        <div style={{ display: this.state.show ? "block" : "none" }}>
          <MyToast
            show={this.state.show}
            message={
              this.state.method === "put"
                ? "List Updated Successfully."
                : "List Saved Successfully."
            }
            type={"success"}
          />
        </div>
        <Card className={"border border-dark bg-dark text-white"}>
          <Card.Header>
            <FontAwesomeIcon icon={this.state.id ? faEdit : faPlusSquare} />{" "}
            {this.state.id ? "Update List" : "Add New List"}
          </Card.Header>
          <Form
            onReset={this.resetList}
            onSubmit={this.state.id ? this.updateList : this.submitList}
            id="listFormId"
          >
            <Card.Body>
              <Form.Row>
                <Form.Group as={Col} controlId="formGridName">
                  <Form.Label>Name</Form.Label>
                  <Form.Control
                    required
                    autoComplete="off"
                    type="test"
                    name="name"
                    value={name}
                    onChange={this.listChange}
                    className={"bg-dark text-white"}
                    placeholder="Enter List Name"
                  />
                </Form.Group>
                <Form.Group as={Col} controlId="formGridAuthor">
                  <Form.Label>Status</Form.Label>
                  <Form.Control
                    required
                    autoComplete="off"
                    type="test"
                    name="status"
                    value={status}
                    onChange={this.listChange}
                    className={"bg-dark text-white"}
                    placeholder="Enter List Author"
                  />
                </Form.Group>
              </Form.Row>
              {/*<Form.Row>*/}
              {/*  <Form.Group as={Col} controlId="formGridCoverPhotoURL">*/}
              {/*    <Form.Label>Cover Photo URL</Form.Label>*/}
              {/*    <InputGroup>*/}
              {/*      <Form.Control*/}
              {/*        required*/}
              {/*        autoComplete="off"*/}
              {/*        type="test"*/}
              {/*        name="coverPhotoURL"*/}
              {/*        value={coverPhotoURL}*/}
              {/*        onChange={this.listChange}*/}
              {/*        className={"bg-dark text-white"}*/}
              {/*        placeholder="Enter List Cover Photo URL"*/}
              {/*      />*/}
              {/*      <InputGroup.Append>*/}
              {/*        {this.state.coverPhotoURL !== "" && (*/}
              {/*          <Image*/}
              {/*            src={this.state.coverPhotoURL}*/}
              {/*            roundedRight*/}
              {/*            width="40"*/}
              {/*            height="38"*/}
              {/*          />*/}
              {/*        )}*/}
              {/*      </InputGroup.Append>*/}
              {/*    </InputGroup>*/}
              {/*  </Form.Group>*/}
              {/*  <Form.Group as={Col} controlId="formGridISBNNumber">*/}
              {/*    <Form.Label>ISBN Number</Form.Label>*/}
              {/*    <Form.Control*/}
              {/*      required*/}
              {/*      autoComplete="off"*/}
              {/*      type="test"*/}
              {/*      name="isbnNumber"*/}
              {/*      value={isbnNumber}*/}
              {/*      onChange={this.listChange}*/}
              {/*      className={"bg-dark text-white"}*/}
              {/*      placeholder="Enter List ISBN Number"*/}
              {/*    />*/}
              {/*  </Form.Group>*/}
              {/*</Form.Row>*/}
              {/*<Form.Row>*/}
              {/*  <Form.Group as={Col} controlId="formGridPrice">*/}
              {/*    <Form.Label>Price</Form.Label>*/}
              {/*    <Form.Control*/}
              {/*      required*/}
              {/*      autoComplete="off"*/}
              {/*      type="test"*/}
              {/*      name="price"*/}
              {/*      value={price}*/}
              {/*      onChange={this.listChange}*/}
              {/*      className={"bg-dark text-white"}*/}
              {/*      placeholder="Enter List Price"*/}
              {/*    />*/}
              {/*  </Form.Group>*/}
              {/*  <Form.Group as={Col} controlId="formGridLanguage">*/}
              {/*    <Form.Label>Language</Form.Label>*/}
              {/*    <Form.Control*/}
              {/*      required*/}
              {/*      as="select"*/}
              {/*      custom*/}
              {/*      onChange={this.listChange}*/}
              {/*      name="language"*/}
              {/*      value={language}*/}
              {/*      className={"bg-dark text-white"}*/}
              {/*    >*/}
              {/*      {this.state.languages.map((language) => (*/}
              {/*        <option key={language.value} value={language.value}>*/}
              {/*          {language.display}*/}
              {/*        </option>*/}
              {/*      ))}*/}
              {/*    </Form.Control>*/}
              {/*  </Form.Group>*/}
              {/*  <Form.Group as={Col} controlId="formGridGenre">*/}
              {/*    <Form.Label>Genre</Form.Label>*/}
              {/*    <Form.Control*/}
              {/*      required*/}
              {/*      as="select"*/}
              {/*      custom*/}
              {/*      onChange={this.listChange}*/}
              {/*      name="genre"*/}
              {/*      value={genre}*/}
              {/*      className={"bg-dark text-white"}*/}
              {/*    >*/}
              {/*      {this.state.genres.map((genre) => (*/}
              {/*        <option key={genre.value} value={genre.value}>*/}
              {/*          {genre.display}*/}
              {/*        </option>*/}
              {/*      ))}*/}
              {/*    </Form.Control>*/}
              {/*  </Form.Group>*/}
              {/*</Form.Row>*/}
            </Card.Body>
            <Card.Footer style={{ textAlign: "right" }}>
              <Button size="sm" variant="success" type="submit">
                <FontAwesomeIcon icon={faSave} />{" "}
                {this.state.id ? "Update" : "Save"}
              </Button>{" "}
              <Button size="sm" variant="info" type="reset">
                <FontAwesomeIcon icon={faUndo} /> Reset
              </Button>{" "}
              <Button
                size="sm"
                variant="info"
                type="button"
                onClick={() => this.listList()}
              >
                <FontAwesomeIcon icon={faList} /> List
              </Button>
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
    saveList: (list) => dispatch(saveList(list)),
    fetchList: (listId) => dispatch(fetchList(listId)),
    updateList: (list) => dispatch(updateList(list)),
  };
};

export default connect(mapStateToProps, mapDispatchToProps)(List);
