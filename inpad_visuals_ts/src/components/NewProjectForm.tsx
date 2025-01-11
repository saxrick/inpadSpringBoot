import React, {ChangeEvent, FormEvent} from "react";
import DropDownUserList from "./DropDownUserList.tsx";
import {request, setAuthHeader} from "./axios_helper.ts";
import {UserDataType} from "../types/UserDataType.tsx";
import ProjectViewer from "./Viewer/ProjectViewer.tsx";

export default class NewProjectForm extends React.Component<{userData: UserDataType}, {projectName: string, state: boolean, projectInfo: string, displayedUserList: UserDataType[], showButton: boolean, showForge: boolean, accesstoken: string, message: string}>{
    constructor(props: { userData: UserDataType; }) {
        super(props);
        this.state = {
            projectName: "",
            state: true,
            projectInfo: "",
            displayedUserList: [this.props.userData],
            showButton: true,
            showForge: false,
            accesstoken: "",
            message: "",
        }
    }

    onCreate = (event: FormEvent<HTMLFormElement>, projectName: string, state: boolean, projectInfo: string, userList: UserDataType[]) => {
        event.preventDefault();
        request(
            "POST",
            "/projects/",
            {
                projectName: projectName,
                state: state,
                projectInfo: projectInfo,
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
    onChangeProjectNameHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        this.setState({projectName : value, message: ""});
    };

    onChangeProjectInfoHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const value = event.target.value;
        this.setState({projectInfo : value, message: ""});
    };


    onSubmitProject = (event: FormEvent<HTMLFormElement>) => {
        this.onCreate(event, this.state.projectName, this.state.state, this.state.projectInfo, this.state.displayedUserList)
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
        if (this.state.projectName === "" && this.state.projectInfo !== ""){
            this.setState({message: "Не заполнено поле: Название"})
        } else if (this.state.projectName !== "" && this.state.projectInfo === ""){
            this.setState({message: "Не заполнено поле: Описание проекта"})
        } else if (this.state.projectName === "" && this.state.projectInfo === "") {
            this.setState({message: "Не заполнены поля: Название и Описание проекта"})
        } else {
            this.setState({showForge: true})
        }
    }



    render() {
        // console.log(this.state.accesstoken)
        return(
            <>
            {/*<ProjectViewer urn={'dXJuOmFkc2sub2JqZWN0czpvcy5vYmplY3Q6Y29uc29saWRhdGVkL3JtZV9hZHZhbmNlZF9zYW1wbGVfcHJvamVjdC5ydnQ'} accessToken={this.getAccessToken.bind(this)} />*/}
                <div className="projectFormTab">
                    <div className="projectText">
                        <h1>Новый проект</h1>
                        <h3>Создавайте, как вам нравится, мы только за!</h3>
                    </div>
                    <div className="createpr">
                        <form className="formInput" onSubmit={this.onSubmitProject}>
                            <div className="inpVal">
                                <input type="text" id="projectName" name="projectName" className="form-control"
                                       placeholder="Название" onChange={this.onChangeProjectNameHandler}/>
                            </div>
                            <div className="inpVal">
                                <input type="text" id="projectInfo" name="projectInfo" className="form-control"
                                       placeholder="Описание проекта" onChange={this.onChangeProjectInfoHandler}/>
                            </div>
                            <div className="inpVal">
                                <input type="text" id="projectInfo" name="projectInfo" className="form-control"
                                       placeholder="Координаты"/>
                            </div>
                            <DropDownUserList userData={this.props.userData}
                                              onChange={(current: UserDataType[]) => this.setState({
                                                  displayedUserList: current,
                                                  showButton: !this.state.showButton
                                              })}/>

                            {(this.state.projectName !== "" && this.state.projectInfo !== "") &&
                                <div className="middle-button">
                                    {this.state.showButton &&
                                        <button type="submit" onClick={this.onClickHandler.bind(this)}
                                                className="btn btn-outline-dark btn-block mb-4">Создать</button>}
                                </div>}
                            {(this.state.projectName === "" || this.state.projectInfo === "") &&
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
                </div>
                {this.state.showForge &&
                    <ProjectViewer onChange={(current: boolean) => this.setState({showForge: current})}/>}
            </>
        )
    }
}