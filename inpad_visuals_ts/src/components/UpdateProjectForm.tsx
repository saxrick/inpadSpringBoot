import React, {ChangeEvent, FormEvent} from "react";
import {request, setAuthHeader} from "./axios_helper.ts";
import DropDownUserListForUpdate from "./DropDownUserListForUpdate.tsx";
import {UserDataType} from "../types/UserDataType.tsx";

export default class UpdateProjectForm extends React.Component<{displayedUserList: UserDataType[], onChange: (current: boolean) => void, projectId: number, projectInfo: string, projectName: string}, {projectName: string, state: boolean, projectInfo: string, displayedUserList: UserDataType[], showButton: boolean, message: string}> {
    constructor(props: { displayedUserList: UserDataType[]; onChange: (current: boolean) => void; projectId: number; projectInfo: string; projectName: string; }) {
        super(props);
        this.state = {
            projectName: this.props.projectName,
            state: true,
            projectInfo: this.props.projectInfo,
            displayedUserList: this.props.displayedUserList,
            showButton: true,
            message: ""
        }
    }

    handleUpdateUserData(){
        this.props.onChange(false)
        // request(
        //     "GET",
        //     `/users/${this.props.userData.id}`,
        //     {}).then(
        //     (response) => {
        //         this.props.onChangeUserData(response.data)
        //     }).catch(
        //     (error) => {
        //         if (error.response.status === 401) {
        //             setAuthHeader(null);
        //         } else {
        //             this.setState({data: error.response.code})
        //         }
        //     }
        // )
    }

    onUpdate = (event: FormEvent<HTMLFormElement>, projectName: string, state: boolean, projectInfo: string, userList: UserDataType[]) => {
        event.preventDefault();
        request(
            "PUT",
            `/projects/${this.props.projectId}`,
            {
                projectName: projectName,
                state: state,
                projectInfo: projectInfo,
                userList: userList,
            }).then(
            () => {

            }).catch(
            () => {
                setAuthHeader(null);
            }
        )
        this.props.onChange(false);
    }


    onChangeProjectNameHandler = (event: ChangeEvent<HTMLInputElement>) => {
        // const name = event.target.name;
        const value = event.target.value;
        this.setState({projectName : value, message: ""});
    };

    onChangeProjectInfoHandler = (event: ChangeEvent<HTMLInputElement>) => {
        // const name = event.target.name;
        const value = event.target.value;
        this.setState({projectInfo : value, message: ""});
    };

    onSubmitProject = (event: FormEvent<HTMLFormElement>) => {
        this.onUpdate(event, this.state.projectName, this.state.state, this.state.projectInfo, this.state.displayedUserList)
    }

    onClickHandler(){
        if (this.state.projectName === "" && this.state.projectInfo !== ""){
            this.setState({message: "Не заполнено поле: Название"})
        } else if (this.state.projectName !== "" && this.state.projectInfo === ""){
            this.setState({message: "Не заполнено поле: Описание проекта"})
        } else if (this.state.projectName === "" && this.state.projectInfo === "") {
            this.setState({message: "Не заполнены поля: Название и Описание проекта"})
        }
    }

    render() {
        console.log(this.state.projectName)
        return(
            <div>
                <div >
                    <form className="formUpdate" onSubmit={this.onSubmitProject}>
                        <div className="inpVal">
                            <input type="text" id="projectName" name="projectName" className="form-control" value={this.state.projectName} placeholder="Название" onChange={this.onChangeProjectNameHandler}/>
                        </div>
                        <div className="inpVal">
                            <input type="text" id="projectInfo" name="projectInfo" className="form-control" value={this.state.projectInfo} placeholder="Описание проекта" onChange={this.onChangeProjectInfoHandler}/>
                        </div>
                        <DropDownUserListForUpdate userData={this.props.displayedUserList} onChange={(current: UserDataType[]) => this.setState({displayedUserList: current, showButton: !this.state.showButton})}/>

                        {(this.state.projectName !== "" && this.state.projectInfo !== "") && <div className="middle-button">
                            {this.state.showButton &&
                                <>
                                    <button type="submit" onClick={this.onClickHandler.bind(this)}
                                            className="btn btn-outline-dark btn-block mb-4">Изменить</button>
                                    <button onClick={this.handleUpdateUserData.bind(this)} type="button"
                                            className="btn btn-outline-dark btn-block mb-4">Назад</button>
                                </>
                            }
                        </div>}
                        {(this.state.projectName === "" || this.state.projectInfo === "") &&
                            <div className="middle-button">
                                {this.state.showButton &&
                                    <>
                                        <button type="button" onClick={this.onClickHandler.bind(this)}
                                                className="btn btn-outline-dark btn-block mb-4">Изменить</button>
                                        <button onClick={this.handleUpdateUserData.bind(this)} type="button"
                                                className="btn btn-outline-dark btn-block mb-4">Назад</button>
                                    </>
                        }
                </div>
                }
                <div className="errorMessage">
                    {this.state.message && <p>{this.state.message}</p>}
                        </div>

                        <div>
                            {/*{this.state.showButton && <button type="submit" className="btn btn-outline-dark btn-block mb-4">Изменить</button>}*/}

                        </div>
                    </form>
                </div>
            </div>
        )
    }

}