import React, { Component } from "react";

import { PrimaryButton } from '@fluentui/react';

class Greeting extends Component {
    render() {
        return (
            <div id="greeting">
                {/* <em>{ this.props.greeting.content }</em> */}
                <PrimaryButton>Test</PrimaryButton>
            </div>
        )
    }
}

export default Greeting;