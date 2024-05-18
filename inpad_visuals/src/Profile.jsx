import {Header} from "./components/Header.jsx";
import React from "react";
import nigga from "/nigga.jpg";
import {request, setAuthHeader} from "./components/axios_helper.js";


export default class Profile extends React.Component {
    constructor(props) {
        super(props);
        this.state ={
            active: "profile",
            userData: this.props.userData
        }
    }
    componentDidMount(){
        request(
            "GET",
            `/users/${this.props.userData.id}`,
            {}).then(
            (response) => {
                this.setState({userData: response.data})
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                } else {
                    this.setState({data: error.response.code})
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
                    active={this.state.tab} onChange={(current) => this.setState({tab: current})}
                    buttonList={[
                        {id: 0, name: 'На главную', active: 'main', path: "logged"},
                        {id: 1, name: 'Выход', active: "logout", path: "logout"}]}/>
                <img className="userPicBig" src={nigga}/>
                <div className="profileUserData">
                    <p>Имя пользователя: {this.state.userData.username}</p>
                    <p>Почта: {this.state.userData.login}</p>
                </div>
            </>
        )
    }


}