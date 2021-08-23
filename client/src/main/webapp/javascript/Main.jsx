import React, { Component } from "react";
import ReactDOM from "react-dom";
import { Route, Switch } from "react-router-dom";

import Icon from "./Icon";

import "../css/main.css";
import { PrimaryButton } from "@fluentui/react";

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
                <Icon />
                {currentPage == page.Index && <div>Index</div>}
                {currentPage == page.CreateRoom && <div>CreateRoom</div>}
                {currentPage == page.JoinRoom && <div>JoinRoom</div>}
                {currentPage == page.GamePlay && <div>GamePlay</div>}
                <PrimaryButton
                    text="Create a Room"
                    onClick={() => this.setPage(page.CreateRoom)}
                />
                <PrimaryButton
                    text="Join a Room"
                    onClick={() => this.setPage(page.JoinRoom)}
                    className="indexButton"
                />
            </div>
        );
    }

    setPage(page) {
        this.setState({ currentPage: page });
    }
}

ReactDOM.render(<Main />, document.getElementById("react-mountpoint"));
