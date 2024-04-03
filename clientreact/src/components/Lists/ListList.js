import React, {Component} from "react";

import {connect} from "react-redux";
import {deleteList} from "../../services/index";

import "./../../assets/css/Style.css";
import {
    Card,
    Table,
    // Image,
    ButtonGroup,
    Button,
    InputGroup,
    FormControl, Alert,
} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {
    faList,
    faEdit,
    faTrash,
    faStepBackward,
    faFastBackward,
    faStepForward,
    faFastForward,
    faSearch,
    faTimes,
} from "@fortawesome/free-solid-svg-icons";
import {Link} from "react-router-dom";
import MyToast from "../MyToast";
import axios from "axios";
import * as authUser from "../../utils/authUser";

class ListList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            lists: []
        };
    }

    componentDidMount() {
        this.findAllLists();
    }

    findAllLists() {
        authUser.makeAPIRequest({
            method: 'get',
            url: "http://localhost:8765/list/phone/" + localStorage.userPhone
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

    deleteList = (listId) => {
        this.props.deleteList(listId)
            .then(() => {
                    this.setState({show: true});
                    this.setState({message: "Список удален!"});
                    setTimeout(() => this.setState({show: false}), 3000);
                }
            )
            .catch((error) => {
                let errorMessage;
                if (error.response && error.response.data) errorMessage = error.response.data;
                else errorMessage = error.message;
                this.setState({show: true});
                this.setState({error: errorMessage})
            });
        this.findAllLists();
    };

    render() {
        const {lists} = this.state;

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
                    {/*<Card.Header>*/}
                    {/*  <div style={{ float: "left" }}>*/}
                    {/*    <FontAwesomeIcon icon={faList} /> List*/}
                    {/*  </div>*/}
                    {/*  <div style={{ float: "right" }}>*/}
                    {/*    <InputGroup size="sm">*/}
                    {/*      <FormControl*/}
                    {/*        placeholder="Search"*/}
                    {/*        name="search"*/}
                    {/*        value={search}*/}
                    {/*        className={"info-border bg-dark text-white"}*/}
                    {/*        onChange={this.searchChange}*/}
                    {/*      />*/}
                    {/*      <InputGroup.Append>*/}
                    {/*        <Button*/}
                    {/*          size="sm"*/}
                    {/*          variant="outline-info"*/}
                    {/*          type="button"*/}
                    {/*          onClick={this.searchData}*/}
                    {/*        >*/}
                    {/*          <FontAwesomeIcon icon={faSearch} />*/}
                    {/*        </Button>*/}
                    {/*        <Button*/}
                    {/*          size="sm"*/}
                    {/*          variant="outline-danger"*/}
                    {/*          type="button"*/}
                    {/*          onClick={this.cancelSearch}*/}
                    {/*        >*/}
                    {/*          <FontAwesomeIcon icon={faTimes} />*/}
                    {/*        </Button>*/}
                    {/*      </InputGroup.Append>*/}
                    {/*    </InputGroup>*/}
                    {/*  </div>*/}
                    {/*</Card.Header>*/}
                    <Card.Body>
                        <Table bordered hover striped variant="dark">
                            {/*<thead>*/}
                            {/*  <tr>*/}
                            {/*<th>Name</th>*/}
                            {/*<th>Author</th>*/}
                            {/*<th>ISBN Number</th>*/}
                            {/*<th onClick={this.sortData}>*/}
                            {/*  Price{" "}*/}
                            {/*  <div*/}
                            {/*    className={*/}
                            {/*      this.state.sortDir === "asc"*/}
                            {/*        ? "arrow arrow-up"*/}
                            {/*        : "arrow arrow-down"*/}
                            {/*    }*/}
                            {/*  >*/}
                            {/*    {" "}*/}
                            {/*  </div>*/}
                            {/*</th>*/}
                            {/*<th>Language</th>*/}
                            {/*<th>Genre</th>*/}
                            {/*<th>Actions</th>*/}
                            {/*  </tr>*/}
                            {/*</thead>*/}
                            <tbody>
                            {lists.length === 0 ? (
                                <tr align="center">
                                    <td colSpan="7">Нет доступных списков</td>
                                </tr>
                            ) : (
                                lists.map((list) => (
                                    <tr key={list.id}>
                                        {/*<td>*/}
                                        {/*  <Image*/}
                                        {/*    src={book.coverPhotoURL}*/}
                                        {/*    roundedCircle*/}
                                        {/*    width="25"*/}
                                        {/*    height="25"*/}
                                        {/*  />{" "}*/}
                                        {/*  {book.title}*/}
                                        {/*</td>*/}
                                        <td style={{width: '90%'}}>
                                            {/*<InputGroup size="sm">*/}
                                            {list.name}
                                            {/*</InputGroup.Append>*/}
                                            {/*</InputGroup>*/}
                                        </td>
                                        {/*<td>{book.isbnNumber}</td>*/}
                                        {/*<td>{book.price}</td>*/}
                                        {/*<td>{book.language}</td>*/}
                                        {/*<td>{book.genre}</td>*/}
                                        <td>
                                            <ButtonGroup>
                                                <Link
                                                    to={"edit/" + list.id}
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
                    {/*{lists.length > 0 ? (*/}
                    {/*  <Card.Footer>*/}
                    {/*    <div style={{ float: "left" }}>*/}
                    {/*      Showing Page {currentPage} of {totalPages}*/}
                    {/*    </div>*/}
                    {/*    <div style={{ float: "right" }}>*/}
                    {/*      <InputGroup size="sm">*/}
                    {/*        <InputGroup.Prepend>*/}
                    {/*          <Button*/}
                    {/*            type="button"*/}
                    {/*            variant="outline-info"*/}
                    {/*            disabled={currentPage === 1 ? true : false}*/}
                    {/*            onClick={this.firstPage}*/}
                    {/*          >*/}
                    {/*            <FontAwesomeIcon icon={faFastBackward} /> First*/}
                    {/*          </Button>*/}
                    {/*          <Button*/}
                    {/*            type="button"*/}
                    {/*            variant="outline-info"*/}
                    {/*            disabled={currentPage === 1 ? true : false}*/}
                    {/*            onClick={this.prevPage}*/}
                    {/*          >*/}
                    {/*            <FontAwesomeIcon icon={faStepBackward} /> Prev*/}
                    {/*          </Button>*/}
                    {/*        </InputGroup.Prepend>*/}
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
                    {/*) : null}*/}
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
        deleteList: (listId) => dispatch(deleteList(listId)),
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(ListList);
