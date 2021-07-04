import React, { Component } from "react";
import ReactDOM from 'react-dom';

import Greeting from './Greeting'

import '../css/main.css';

class Main extends Component {
    constructor(props) {
        super(props)
        this.state = {
            greeting: null
        }
    }

    componentDidMount() {
        fetch("api/greeting")
            .then(res => res.json())
            .then(
                (response) => {
                    this.setState({
                        greeting: response
                    });
                },
                (error) => {
                    alert(error);
                }
            )
    }

    render() {
        return (
            <div id="main">
                <h1>Demo Component</h1>
                <Greeting greeting={ this.state.greeting } />
            </div>
        );
    }
}

ReactDOM.render(
    <Main />,
    document.getElementById('react-mountpoint')
);