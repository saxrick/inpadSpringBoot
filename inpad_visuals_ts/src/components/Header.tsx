import logo from '/Inpad_logo.png'
import Button from "./Button/Button.tsx";
import React from "react";
import {ButtonType} from "../types/ButtonType.tsx";
import {PathType} from "../types/PathType.tsx";
import nigga from "/nigga.jpg";

export class Header extends React.Component<{active: string, buttonList: ButtonType[], logout: () => void, logged: () => void, onChange: (current: string) => void, pathList: PathType[] | undefined, profile: () => void | undefined}>{
    constructor(props: { active: string; buttonList: ButtonType[]; logout: () => void; logged: () => void; onChange: (current: string) => void; pathList: PathType[] | undefined; profile: () => void | undefined; }) {
        super(props);
    }

    handleClick(button: ButtonType){
        this.props.onChange(button.active)
        if (button.path && button.path === "logout"){
            this.props.logout()
        }
        else if (button.path &&  button.path === "logged"){
            this.props.logged()
        }
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
                                onClick={() => this.handleClick(button)}
                            >{button.name}
                            </Button>)}

                    {/*{this.props.pathList && <DropDownMenu pathList={this.props.pathList}/>}*/}
                    <img onClick={this.props.profile} className="userPic" src={nigga}/>
                </div>
            </div>
        )
    }

}