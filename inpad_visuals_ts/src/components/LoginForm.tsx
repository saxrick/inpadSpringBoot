import * as React from 'react';
import classNames from 'classnames';
import {ChangeEvent, FormEvent} from "react";

export default class LoginForm extends React.Component<{onLogin: (event: FormEvent<HTMLFormElement>, login: string, password: string) => void, onRegister: (event: FormEvent<HTMLFormElement>, userName: string, login: string, password: string, projectList: object, state: boolean) => void, message: string, onChange: (current: string) => void}, {
    active: string,
    userName: string,
    login: string,
    password: string,
    checkPassword: string,
    state: boolean,
    projectList: object,
    onLogin: (event: FormEvent<HTMLFormElement>, login: string, password: string) => void,
    onRegister: (event: FormEvent<HTMLFormElement>, userName: string, login: string, password: string, projectList: object, state: boolean) => void,}> {

    constructor(props: {onLogin: (event: FormEvent<HTMLFormElement>, login: string, password: string) => void, onRegister: (event: FormEvent<HTMLFormElement>, userName: string, login: string, password: string, projectList: object, state: boolean) => void, message: string, onChange: (current: string) => void}) {
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

    onChangeUserNameHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const value: string = event.target.value;
        this.props.onChange("")
        this.setState({userName : value});
    };

    onChangeLoginHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const value: string = event.target.value;
        this.props.onChange("")
        this.setState({login : value});
    };

    onChangePasswordHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const value: string = event.target.value;
        this.props.onChange("")
        this.setState({password : value});
    };

    onChangeCheckPasswordHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const value: string = event.target.value;
        this.props.onChange("")
        this.setState({checkPassword : value});
    };


    onSubmitLogin = (event : FormEvent<HTMLFormElement>) => {
        this.state.onLogin(event, this.state.login, this.state.password);
    };

    onSubmitRegister = (event: FormEvent<HTMLFormElement>) => {
        this.state.onRegister(event, this.state.userName, this.state.login, this.state.password, this.state.projectList, this.state.state);
    };

    onClickLoginPassword(action: string){
        this.props.onChange("")
        this.setState({active: action})
    }

    onClickHandler(){
        if (this.state.login === "" && this.state.password !== ""){
            this.props.onChange("Не заполнено поле: Логин")
        } else if (this.state.login !== "" && this.state.password === ""){
            this.props.onChange("Не заполнено поле: Пароль")
        } else if (this.state.login === "" && this.state.password === "") {
            this.props.onChange("Не заполнены поля: Логин и Пароль")
        }
    }

    onClickHandlerRegister(){
        if (this.state.login === "" && this.state.password !== "" && this.state.userName !== ""){
            this.props.onChange("Не заполнено поле: Логин")
        } else if (this.state.login !== "" && this.state.password === "" && this.state.userName !== ""){
            this.props.onChange("Не заполнено поле: Пароль")
        } else if (this.state.login !== "" && this.state.password !== "" && this.state.userName === ""){
            this.props.onChange("Не заполнено поле: Имя пользователя")
        } else if (this.state.login !== "" && this.state.password === "" && this.state.userName === "") {
            this.props.onChange("Не заполнены поля: Имя пользователя и Пароль")
        } else if (this.state.login === "" && this.state.password === "" && this.state.userName !== "") {
            this.props.onChange("Не заполнены поля: Логин и Пароль")
        } else if (this.state.login === "" && this.state.password !== "" && this.state.userName === "") {
            this.props.onChange("Не заполнены поля: Имя пользователя и Логин")
        } else if (this.state.login === "" && this.state.password === "" && this.state.userName === "") {
            this.props.onChange("Не заполнены поля: Логин, Пароль и Имя пользователя")
        }

        if (this.state.password !== this.state.checkPassword){
            this.props.onChange("Пароли не совпадают")
        }
    }

    render() {
        return (
            <div className="row justify-content-center">
                <div className="login-text">
                    <h1>Добро пожаловать!</h1>
                </div>

                <div className="col-4">

                    <ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "login" ? "active" : "")} id="tab-login"
                                    onClick={() => this.onClickLoginPassword("login")}>Вход</button>
                        </li>
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", this.state.active === "register" ? "active" : "")} id="tab-register"
                                    onClick={() => this.onClickLoginPassword("register")}>Регистрация</button>
                        </li>
                    </ul>

                    <div className="tab-content">
                        <div className={classNames("tab-pane", "fade", this.state.active === "login" ? "show active" : "")} id="pills-login" >
                            <form onSubmit={this.onSubmitLogin}>

                                <div className="form-outline mb-4">
                                    <input type="login" id="loginName" name="login" placeholder="Логин"
                                           className="form-control"
                                           onChange={this.onChangeLoginHandler}/>

                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="loginPassword" name="password" placeholder="Пароль"
                                           className="form-control"
                                           onChange={this.onChangePasswordHandler}/>

                                </div>

                                <div className="errorMessage">
                                    {this.props.message && <p>{this.props.message}</p>}
                                </div>

                                {(this.state.login !== "" && this.state.password !== "") && <div className="middle-button">
                                    <button type="submit" onClick={this.onClickHandler.bind(this)}
                                            className="btn btn-outline-dark btn-block mb-4">Войти</button>
                                </div>}
                                {(this.state.login === "" || this.state.password === "") && <div className="middle-button">
                                    <button type="button" onClick={this.onClickHandler.bind(this)}
                                            className="btn btn-outline-dark btn-block mb-4">Войти</button>
                                </div>}


                            </form>
                        </div>
                        <div className={classNames("tab-pane", "fade", this.state.active === "register" ? "show active" : "")} id="pills-register" >
                            <form onSubmit={this.onSubmitRegister}>

                                <div className="form-outline mb-4">
                                    <input type="text" id="userName" name="userName" placeholder="Имя пользователя"
                                           className="form-control" onChange={this.onChangeUserNameHandler}/>

                                </div>

                                <div className="form-outline mb-4">
                                    <input type="text" id="login" name="login" placeholder="Логин (Эл. почта)"
                                           className="form-control" onChange={this.onChangeLoginHandler}/>

                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="registerPassword" name="password" placeholder="Пароль"
                                           className="form-control" onChange={this.onChangePasswordHandler}/>

                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="checkPassword" name="password"
                                           placeholder="Повторите пароль"
                                           className="form-control" onChange={this.onChangeCheckPasswordHandler}/>
                                </div>

                                <div className="errorMessage">
                                    {this.props.message && <p>{this.props.message}</p>}
                                </div>

                                {(this.state.userName !== "" && this.state.login !== "" && this.state.password !== "" && this.state.password === this.state.checkPassword) && <div className="middle-button">
                                    <button type="submit" onClick={this.onClickHandlerRegister.bind(this)}
                                            className="btn btn-outline-dark btn-block mb-3">Зарегистрироваться</button>
                                </div>}
                                {(this.state.userName === "" || this.state.login === "" || this.state.password === "" || this.state.password !== this.state.checkPassword) && <div className="middle-button">
                                    <button type="button" onClick={this.onClickHandlerRegister.bind(this)}
                                            className="btn btn-outline-dark btn-block mb-3">Зарегистрироваться</button>
                                </div>}

                                {/*<div className="middle-button">*/}
                                {/*    <button type="submit"*/}
                                {/*            className="btn btn-outline-dark btn-block mb-3">Зарегистрироваться*/}
                                {/*    </button>*/}
                                {/*</div>*/}
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }

}
