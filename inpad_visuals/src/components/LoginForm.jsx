import * as React from 'react';
import classNames from 'classnames';
import {getAuthToken} from "./axios_helper.js";

export default class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            active: "login",
            userName: "",
            login: "",
            password: "",
            checkPassword: "",
            state: true,
            projectList: [],
            onLogin: props.onLogin,
            onRegister: props.onRegister,
        };
    }

    onChangeHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name] : value});
    };

    onSubmitLogin = (e) => {
        this.state.onLogin(e, this.state.login, this.state.password);
    };

    onSubmitRegister = (e) => {
        this.state.onRegister(e, this.state.userName, this.state.login, this.state.password, this.state.projectList, this.state.state);
    };

    render() {
        console.log(getAuthToken())
        return (
            <div className="row justify-content-center">
                <div className="col-4">
                    <ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "login" ? "active" : "")} id="tab-login"
                                    onClick={() => this.setState({active: "login"})}>Вход</button>
                        </li>
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "register" ? "active" : "")} id="tab-register"
                                    onClick={() => this.setState({active: "register"})}>Регистрация</button>
                        </li>
                    </ul>

                    <div className="tab-content">
                        <div className={classNames("tab-pane", "fade", this.state.active === "login" ? "show active" : "")} id="pills-login" >
                            <form onSubmit={this.onSubmitLogin}>

                                <div className="form-outline mb-4">
                                    <input type="login" id="loginName" name="login" placeholder="Логин"
                                           className="form-control"
                                           onChange={this.onChangeHandler}/>

                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="loginPassword" name="password" placeholder="Пароль"
                                           className="form-control"
                                           onChange={this.onChangeHandler}/>

                                </div>

                                <div className="middle-button">
                                    <button type="submit" className="btn btn-outline-dark btn-block mb-4">Войти</button>
                                </div>
                                <h2>{this.state.message}</h2>

                            </form>
                        </div>
                        <div className={classNames("tab-pane", "fade", this.state.active === "register" ? "show active" : "")} id="pills-register" >
                            <form onSubmit={this.onSubmitRegister}>

                                <div className="form-outline mb-4">
                                    <input type="text" id="userName" name="userName" placeholder="Имя пользователя"
                                           className="form-control" onChange={this.onChangeHandler}/>

                                </div>

                                <div className="form-outline mb-4">
                                    <input type="text" id="login" name="login" placeholder="Логин"
                                           className="form-control" onChange={this.onChangeHandler}/>

                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="registerPassword" name="password" placeholder="Пароль"
                                           className="form-control" onChange={this.onChangeHandler}/>

                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="checkPassword" name="password"
                                           placeholder="Повторите пароль"
                                           className="form-control" onChange={this.onChangeHandler}/>
                                </div>
                                <div className="middle-button">
                                    <button type="submit"
                                            className="btn btn-outline-dark btn-block mb-3">Зарегистрироваться
                                    </button>
                                </div>
                                    <h2>{this.state.message}</h2>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}
