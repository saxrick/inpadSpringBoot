import React from "react";
import {request, setAuthHeader} from "./axios_helper.ts";
import {UserDataType} from "../types/UserDataType.tsx";


export default class DropDownUserListForUpdate extends React.Component<{onChange: (current: UserDataType[]) => void, userData: UserDataType[]}, {userList: UserDataType[], isOpen: boolean, displayedUserList: UserDataType[]}> {
    constructor(props: { onChange: (current: UserDataType[]) => void; userData: UserDataType[]; }) {
        super(props);
        this.state = {
            userList: [],
            isOpen: false,
            displayedUserList: []
        }

    }

    handleOpen(){
        this.setState({isOpen: !this.state.isOpen})
        this.props.onChange(this.state.displayedUserList)
    }

    addUserToDisplay(user: UserDataType){
        this.setState({displayedUserList: [...this.state.displayedUserList, user],
            userList: [...this.state.userList.filter((userToDelete: UserDataType) => userToDelete !== user)]})
    }

    removeUserFromDisplay(user: UserDataType){
        this.setState({displayedUserList: [...this.state.displayedUserList.filter((userToDelete: UserDataType) => userToDelete !== user)],
            userList: [...this.state.userList, user]})
    }

    userListWcomma(userList: UserDataType[]) {
        return(
            <ul className="userlistWcomma">Участники: {userList && userList.map((user) => <li
                onClick={() => this.removeUserFromDisplay(user)} className="userinprWcomma"
                key={user.id}>{user.username}</li>)}</ul>
        )
    }

    componentDidMount(){
        request(
            "GET",
            `/users/all`,
            {}).then(
            (response) => {
                this.setState({userList: response.data.filter((userToDelete: UserDataType) => (this.props.userData.findIndex((user) => user.login === userToDelete.login) === -1)),
                    displayedUserList: this.props.userData})
            })
            .catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                }
            }
        );
    }

    render(){
        return(
            <>
                <div className="inpVal">
                    <div className="form-control" >
                        {this.userListWcomma(this.state.displayedUserList)}
                        {/*{<ul className="userList">Участники: {this.state.displayedUserList && this.state.displayedUserList.map((user) =>*/}
                        {/*    <li onClick={() => this.removeUserFromDisplay(user)} className="userinpr"*/}
                        {/*        key={user.id}>{user.username}</li>)}</ul>}*/}
                        <div className="openDropDown" onClick={this.handleOpen.bind(this)}>
                            <div className="arr">
                                &#9660;
                            </div>
                        </div>
                    </div>
                    {this.state.isOpen && <div className="form-control1">
                        {<ul className="userListDrop">{this.state.userList && this.state.userList.map((user) =>
                            <li onClick={() => this.addUserToDisplay(user)} className="userinpr"
                                key={user.id}>{user.username}</li>)}</ul>}
                    </div>}
                </div>
            </>
        )
    }
}