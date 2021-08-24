import React, { Component } from "react";

import "../css/main.css";

class Icon extends Component {
    constructor(props) {
        super(props);
        this.state = { isCenter: props.isCenter ? props.isCenter : false };
    }
    render() {
        const divId = this.state.isCenter ? "indexIcon" : "playIcon";
        return (
            <div id={divId}>
                <h1>Battleship</h1>
            </div>
        );
    }
}

export default Icon;
