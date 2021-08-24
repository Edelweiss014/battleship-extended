import React, { Component } from "react";
import ReactDOM from "react-dom";

import "../css/main.css";

import { PrimaryButton } from "@fluentui/react";
import HeaderBar from "./header/HeaderBar";
import Icon from "./Icon";

const page = {
    Index: "Index",
    CreateRoom: "CreateRoom",
    JoinRoom: "JoinRoom",
    GamePlay: "GamePlay",
};

class Main extends Component {
    constructor(props) {
        super(props);
        this.state = {
            currentPage: page.Index,
        };
    }

    render() {
        const { currentPage } = this.state;
        return (
            <div>
                {currentPage != page.Index && <HeaderBar />}
                {currentPage == page.CreateRoom && <div>CreateRoom</div>}
                {currentPage == page.JoinRoom && <div>JoinRoom</div>}
                {currentPage == page.GamePlay && <div>GamePlay</div>}
                {currentPage == page.Index && (
                    <div id="indexDiv">
                        <Icon isCenter />
                        <PrimaryButton
                            text="Create a Room"
                            onClick={() => this.createRoom()}
                            className="indexButton"
                        />
                        <PrimaryButton
                            text="Join a Room"
                            onClick={() => this.setPage(page.JoinRoom)}
                            className="indexButton"
                        />
                    </div>
                )}
            </div>
        );
    }

    setPage(page) {
        this.setState({ currentPage: page });
    }

    createRoom() {
        this.setPage(page.CreateRoom);
    }
}

ReactDOM.render(<Main />, document.getElementById("react-mountpoint"));
