import MainPage from "./MainPage.jsx";
import React from "react";
import {getAuthToken, request, setAuthHeader} from "./components/axios_helper.js";
import LoginForm from "./components/LoginForm.jsx";
import Profile from "./Profile.jsx";

export default class App extends React.Component{
    constructor(props) {
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
        console.log("profile")
    }


    logout = () => {
        this.setState({componentToShow: "login"})
        setAuthHeader(null);
        console.log("logout")
    }

    onLogin = (e, login, password) => {
        e.preventDefault()
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
                this.setState({componentToShow: "login"})

        })
    }

    onRegister = (event, username, login, password, projectList, state) => {
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

        if (getAuthToken()){
            this.setState({componentToShow: "logged"})
        }
        return (
            <>
                {this.state.componentToShow === "login" && <LoginForm onLogin={this.onLogin} onRegister={this.onRegister}/>}
                {this.state.componentToShow === "logged" && <MainPage logout={this.logout} profile={this.profile} userData={this.state.userData} />}
                {this.state.componentToShow === "profile" && <Profile logout={this.logout} logged={this.login} />}

                {/*<BrowserRouter>*/}
                {/*    <Routes>*/}
                {/*        <Route path="" element={<MainPage/>} />*/}
                {/*        <Route path="Profile" element={<Profile/>} />*/}
                {/*        <Route path="login" element={<LoginForm/>}/>*/}
                {/*    </Routes>*/}
                {/*</BrowserRouter>*/}
            </>
        )
    }



}

