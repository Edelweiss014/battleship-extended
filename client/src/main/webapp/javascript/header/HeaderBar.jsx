import React, { Component } from "react";

import { Pivot, PivotItem, Stack, StackItem } from "@fluentui/react";

import "../../css/header/header.css";
import Icon from "../Icon";

class HeaderBar extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div>
                <Stack>
                    <Icon isCenter={false} />
                    <Pivot>
                        <PivotItem headerText="Game" />
                    </Pivot>
                </Stack>
            </div>
        );
    }
}

export default HeaderBar;
