import React from "react";
import nigga from "/nigga.jpg";

export default class DropDownMenu extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false,
        }
    }

    handleOpen(){
        this.setState({isOpen: !this.state.isOpen})
        console.log("абоба")
    }


    render() {
        return (
            <>


            </>
        )
    }
}