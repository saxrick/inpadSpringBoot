import MainPage from "./MainPage.tsx";
import React, {FormEvent} from "react";
import {request, setAuthHeader} from "./components/axios_helper.ts";
import LoginForm from "./components/LoginForm.tsx";
import Profile from "./Profile.tsx";
import {UserDataType} from "./types/UserDataType.tsx";

export default class App extends React.Component<object, {componentToShow: string, userData: UserDataType | null, message: string}>{
    constructor(props: object) {
        super(props);
        this.state = {
            componentToShow: "login",
            userData: null,
            message: ""
        }
    }

    login = () => {
        this.setState({componentToShow: "logged"})
    }

    profile = () => {
        this.setState({componentToShow: "profile"})
    }

    logout = () => {
        this.setState({componentToShow: "login"})
        setAuthHeader(null);
    }

    onLogin = (event: FormEvent<HTMLFormElement>, login: string, password: string) => {
        event.preventDefault()
        request("POST",
            "/login",
            {
                login: login,
                password: password
            })
            .then((response) => {
                setAuthHeader(response.data.token)
                this.setState({componentToShow: "logged", userData: response.data})

            }).catch((error) => {
            setAuthHeader(null);
            this.setState({componentToShow: "login", message: error.response.data.message})

        })
    }

    onRegister = (event: FormEvent<HTMLFormElement>, username: string, login: string, password: string, projectList: object, state: boolean) => {
        event.preventDefault();
        request(
            "POST",
            "/register",
            {
                username: username,
                login: login,
                password: password,
                state: state,
                projectList: projectList
            }).then(
            (response) => {
                setAuthHeader(response.data.token);
                this.setState({componentToShow: "logged", userData: response.data});

            }).catch(
            (error) => {
                setAuthHeader(null);
                this.setState({componentToShow: "login", message: error.response.data.message})
            }
        );
    };

    // onLogged = (e) => {
    //     e.preventDefault()
    //     request("GET",
    //         "/logged",
    //         {
    //             data: []
    //         })
    //         .then((response) => {
    //             setAuthHeader(response.data.token)
    //             this.setState({componentToShow: "logged", userData: response.data})
    //
    //         }).catch((error) => {
    //         setAuthHeader(null);
    //         this.setState({componentToShow: "login"})
    //
    //     })
    //     console.log(data)
    // }



    render() {
        return (
            <>
                {this.state.componentToShow === "login" && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister} message={this.state.message} onChange={(current: string) => this.setState({message: current})}/>}
                {this.state.userData && this.state.componentToShow === "logged" && <MainPage logout={this.logout} profile={this.profile} userData={this.state.userData} />}
                {this.state.userData && this.state.componentToShow === "profile" && <Profile logout={this.logout} logged={this.login} userData={this.state.userData}/>}
            </>
        )
    }
}

