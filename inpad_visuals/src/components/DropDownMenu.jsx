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
    }


    render() {
        return (
            <>
                <img onClick={this.handleOpen.bind(this)} className="userPic" src={nigga}/>
                <div className="dropdownList">
                    {this.props.pathList && this.state.isOpen && <ul className="dropdownlist">
                        {this.props.pathList.map((setpath) => <li className="dropdownElem" onClick={setpath.path} key={setpath.id}>{setpath.pathname}</li>)}
                    </ul>}
                </div>
            </>
        )
    }
}