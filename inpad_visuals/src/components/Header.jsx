import logo from '/Inpad_logo.png'
import Button from "./Button/Button.jsx";

import React from "react";
import nigga from "/nigga.jpg";

export class Header extends React.Component{
    constructor(props) {
        super(props);
    }

    render() {
        return (
            <div className="header">
                <a href="https://inpad.ru/">
                    <img className='tanya' src={logo} alt="Aboba"/>
                </a>

                <div className="headerButtons">
                    {this.props.buttonList && this.props.buttonList
                        .map((button) =>
                            <Button
                                key={button.id}
                                isActive={this.props.active === button.active}
                                onClick={() => this.props.onChange(button.active)}
                            >{button.name}
                            </Button>)}

                    <img onClick={this.props.logout} className="userPic" src={nigga}/>
                </div>
            </div>
        )
    }

}