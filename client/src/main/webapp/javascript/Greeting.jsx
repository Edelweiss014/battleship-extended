import React, { Component } from "react";

class Greeting extends Component {
    render() {
        if (!this.props.greeting) {
            return <div>Hello...</div>
        }
        return (
            <div id="greeting">
                <em>{ this.props.greeting.content }</em>
            </div>
        )
    }
}

export default Greeting;