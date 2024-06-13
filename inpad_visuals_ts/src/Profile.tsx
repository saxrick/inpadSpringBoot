import {Header} from "./components/Header.tsx";
import React from "react";
import nigga from "/nigga.jpg";
import {request, setAuthHeader} from "./components/axios_helper.ts";
import {UserDataType} from "./types/UserDataType.tsx";


export default class Profile extends React.Component<{userData: UserDataType, logout: () => void, logged: () => void}, { active: string, userData: UserDataType, tab: string }> {
    constructor(props: { userData: UserDataType; logout: () => void; logged: () => void; }) {
        super(props);
        this.state = {
            active: "profile",
            userData: this.props.userData,
            tab: ""
        }
    }

    componentDidMount() {
        request(
            "GET",
            `http://localhost:8080/users/${this.props.userData.id}`,
            {}).then(
            (response) => {
                this.setState({userData: response.data})
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                }
            }
        )
    }

    render() {
        return (
            <>
                <Header
                    logged={this.props.logged}
                    logout={this.props.logout}
                    active={this.state.tab} onChange={(current: string) => this.setState({tab: current})}
                    buttonList={[
                        {id: 0, name: 'На главную', active: 'main', path: "logged"},
                        {id: 1, name: 'Выход', active: "logout", path: "logout"}]} pathList={undefined} profile={() => undefined}/>
                <img className="userPicBig" src={nigga}/>
                <div className="profileUserData">
                    <p>Имя пользователя: {this.state.userData.username}</p>
                    <p>Почта: {this.state.userData.login}</p>
                </div>
            </>
        )
    }


}