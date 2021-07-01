import React, { Component } from "react";
import ReactDOM from 'react-dom';

import '../css/main.css';

class Main extends Component {

    render() {
        return (
            <div id="main">
                <h1>Demo Component</h1>
            </div>
        );
    }
}

ReactDOM.render(
    <Main />,
    document.getElementById('react-mountpoint')
);