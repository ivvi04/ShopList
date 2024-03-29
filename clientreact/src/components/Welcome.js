// import axios from "axios";
import React /*, {useEffect, useState} */ from "react";
// import {Card} from "react-bootstrap";
import * as authToken from "../utils/authUser";

const Welcome = (props) => {
    // const [quotes, setQuotes] = useState("");

    console.log(localStorage.jwtToken);
    if (localStorage.jwtToken) {
        authToken.setTokenToStorage(localStorage.jwtToken);
        // props.dispatch(setCurrentUser(jwt.decode(localStorage.jwtToken)))
        return props.history.push("/");
    }
    // else{
    //     return (<div>Login Please</div>);
    // }

    // useEffect(() => {
    //     if (quotes === "") {
    //         axios.get("https://type.fit/api/quotes").then((response) => {
    //             setQuotes(response.data);
    //         });
    //     }
    // }, [quotes]);

    // return (
    //     <Card bg="dark" text="light">
    //         <Card.Header>Quotes</Card.Header>
    //         <Card.Body style={{overflowY: "scroll", height: "570px"}}>
    //             {/*{quotes &&*/}
    //             {/*    quotes.map((quote, id) => (*/}
    //             {/*        <blockquote className="blockquote mb-0" key={id}>*/}
    //             {/*            <p>{quote.text}</p>*/}
    //             {/*            <footer className="blockquote-footer">{quote.author}</footer>*/}
    //             {/*        </blockquote>*/}
    //             {/*    ))}*/}
    //         </Card.Body>
    //     </Card>
    // );
};

export default Welcome;
