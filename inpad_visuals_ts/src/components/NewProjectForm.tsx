import React, {ChangeEvent, FormEvent} from "react";
import DropDownUserList from "./DropDownUserList.tsx";
import {request, setAuthHeader} from "./axios_helper.ts";
import {UserDataType} from "../types/UserDataType.tsx";

export default class NewProjectForm extends React.Component<{userData: UserDataType}, {projectName: string, state: boolean, projectInfo: string, displayedUserList: UserDataType[], showButton: boolean}>{
    constructor(props: { userData: UserDataType; }) {
        super(props);
        this.state = {
            projectName: "",
            state: true,
            projectInfo: "",
            displayedUserList: [this.props.userData],
            showButton: true
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
    onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({[name] : value});
    };

    onSubmitProject = (event: FormEvent<HTMLFormElement>) => {
        this.onCreate(event, this.state.projectName, this.state.state, this.state.projectInfo, this.state.displayedUserList)
    }

    render() {
        return(
            <div className="projectFormTab">
                <div className="createpr">
                    <form className="formInput" onSubmit={this.onSubmitProject}>
                        <div className="inpVal">
                            <input type="text" id="projectName" name="projectName" className="form-control" placeholder="Название" onChange={this.onChangeHandler}/>
                        </div>
                        <div className="inpVal">
                            <input type="text" id="projectInfo" name="projectInfo" className="form-control" placeholder="Описание проекта" onChange={this.onChangeHandler}/>
                        </div>
                        <DropDownUserList userData={this.props.userData} onChange={(current: UserDataType[]) => this.setState({displayedUserList: current, showButton: !this.state.showButton})}/>
                        <div className="middle-button">
                            {this.state.showButton && <button type="submit" className="btn btn-outline-dark btn-block mb-4">Создать</button>}
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}