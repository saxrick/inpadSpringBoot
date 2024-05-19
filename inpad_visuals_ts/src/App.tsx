import MainPage from "./MainPage.tsx";
import React, {FormEvent} from "react";
import {request, setAuthHeader} from "./components/axios_helper.ts";
import LoginForm from "./components/LoginForm.tsx";
import Profile from "./Profile.tsx";

export default class App extends React.Component<never, {componentToShow: string, userData: object}>{
    constructor(props: never) {
        super(props);
        this.state = {
            componentToShow: "login",
            userData: {},
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
            console.log(error)
            setAuthHeader(null);
            this.setState({componentToShow: "login"})

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
                console.log(error)
                setAuthHeader(null);
                this.setState({componentToShow: "login"})
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
                {this.state.componentToShow === "login" && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister}/>}
                {this.state.componentToShow === "logged" && <MainPage logout={this.logout} profile={this.profile} userData={this.state.userData} />}
                {this.state.componentToShow === "profile" && <Profile logout={this.logout} logged={this.login} userData={this.state.userData}/>}
            </>
        )
    }
}

