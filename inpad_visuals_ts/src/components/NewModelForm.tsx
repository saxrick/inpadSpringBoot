import React, {ChangeEvent, FormEvent} from "react";
import DropDownUserList from "./DropDownUserList.tsx";
import {request, setAuthHeader} from "./axios_helper.ts";
import {UserDataType} from "../types/UserDataType.tsx";

import ModelMaker from "./Viewer/ModelMaker.tsx";

export default class NewModelForm extends React.Component<{userData: UserDataType}, {modelName: string, state: boolean, modelInfo: string, displayedUserList: UserDataType[], showButton: boolean, showForge: boolean, accesstoken: string, message: string}>{
    constructor(props: { userData: UserDataType; }) {
        super(props);
        this.state = {
            modelName: "",
            state: true,
            modelInfo: "",
            displayedUserList: [this.props.userData],
            showButton: true,
            showForge: false,
            accesstoken: "",
            message: "",
        }
    }

    onCreate = (modelName: string, state: boolean, modelInfo: string, userList: UserDataType[]) => {
        request(
            "POST",
            "/models/",
            {
                modelName: modelName,
                state: state,
                modelInfo: modelInfo,
                userList: userList,
            }).then(
            (response) => {
                console.log(response.data)
            }).catch(
            () => {
                setAuthHeader(null);
            }
        );
    };
    onChangeModelNameHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        this.setState({modelName : value, message: ""});
    };

    onChangeModelInfoHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        this.setState({modelInfo : value, message: ""});
    };


    onSubmitModel = () => {
        this.onCreate(this.state.modelName, this.state.state, this.state.modelInfo, this.state.displayedUserList)
    }

    async getAccessToken(){
        const token = await fetch("https://hd24ouudmhx7ixzla4i6so2atm0fgsex.lambda-url.us-west-2.on.aws").then((token) => token.text());
        this.setState({accesstoken: token})
    }

    async componentDidMount() {
        const token = await fetch("https://hd24ouudmhx7ixzla4i6so2atm0fgsex.lambda-url.us-west-2.on.aws").then((token) => token.text());
        this.setState({accesstoken: token})
    }


    onClickHandler(){
        if (this.state.modelName === "" && this.state.modelInfo !== ""){
            this.setState({message: "Не заполнено поле: Название"})
        } else if (this.state.modelName !== "" && this.state.modelInfo === ""){
            this.setState({message: "Не заполнено поле: Описание модели"})
        } else if (this.state.modelName === "" && this.state.modelInfo === "") {
            this.setState({message: "Не заполнены поля: Название и Описание модели"})
        } else {
            this.onSubmitModel()
            this.setState({showForge: true})
        }
    }



    render() {
        // console.log(this.state.accesstoken)
        return(
            <>
                {/*<ProjectViewer urn={'dXJuOmFkc2sub2JqZWN0czpvcy5vYmplY3Q6Y29uc29saWRhdGVkL3JtZV9hZHZhbmNlZF9zYW1wbGVfcHJvamVjdC5ydnQ'} accessToken={this.getAccessToken.bind(this)} />*/}

                {!this.state.showForge &&
                    <div className="projectFormTab">
                        <div className="projectText">
                            <h1>Новый проект</h1>
                            <h3>Создавайте, как вам нравится, мы только за!</h3>
                        </div>

                        <div className="createpr">
                            <form className="formInput" onSubmit={this.onSubmitModel}>
                                <div className="inpVal">
                                    <input type="text" id="projectName" name="projectName" className="form-control"
                                           placeholder="Название" onChange={this.onChangeModelNameHandler}/>
                                </div>
                                <div className="inpVal">
                                    <input type="text" id="projectInfo" name="projectInfo" className="form-control"
                                           placeholder="Описание модели" onChange={this.onChangeModelInfoHandler}/>
                                </div>

                                <DropDownUserList userData={this.props.userData}
                                                  onChange={(current: UserDataType[]) => this.setState({
                                                      displayedUserList: current,
                                                      showButton: !this.state.showButton
                                                  })}/>

                                {(this.state.modelName !== "" && this.state.modelInfo !== "") &&
                                    <div className="middle-button">
                                        {this.state.showButton &&
                                            <button type="submit" onClick={this.onClickHandler.bind(this)}
                                                    className="btn btn-outline-dark btn-block mb-4">Создать</button>}
                                    </div>}
                                {(this.state.modelName === "" || this.state.modelInfo === "") &&
                                    <div className="middle-button">
                                        {this.state.showButton &&
                                            <button type="button" onClick={this.onClickHandler.bind(this)}
                                                    className="btn btn-outline-dark btn-block mb-4">Создать</button>}
                                    </div>}
                                <div className="errorMessage">
                                    {this.state.message && <p>{this.state.message}</p>}
                                </div>
                            </form>
                        </div>
                    </div>}
                {this.state.showForge &&
                    <ModelMaker onChange={(current: boolean) => this.setState({showForge: current})}/>}
            </>
        )
    }
}