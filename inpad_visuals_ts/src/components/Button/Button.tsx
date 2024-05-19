import classes from './Button.module.css'
import React, {ReactNode} from "react";
export default class Button extends React.Component<{ children: ReactNode, isActive: boolean, onClick: () => void }> {

    constructor(props: { children: React.ReactNode; isActive: boolean; onClick: () => void }) {
        super(props);
    }
    render() {

        return (
            <button
                {...this.props}
                className={this.props.isActive ? `${classes.button} ${classes.active}` : classes.button}>
                {this.props.children}
            </button>
        )
    }
}