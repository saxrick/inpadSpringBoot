import React, {ChangeEvent, FormEvent} from "react";
import {request, setAuthHeader} from "./axios_helper.ts";
import DropDownUserListForUpdate from "./DropDownUserListForUpdate.tsx";
import {UserDataType} from "../types/UserDataType.tsx";

export default class UpdateProjectForm extends React.Component<{displayedUserList: UserDataType[], onChange: (current: boolean) => void, projectId: number, projectInfo: string, projectName: string}, {projectName: string, state: boolean, projectInfo: string, displayedUserList: UserDataType[], showButton: boolean}> {
    constructor(props: { displayedUserList: UserDataType[]; onChange: (current: boolean) => void; projectId: number; projectInfo: string; projectName: string; }) {
        super(props);
        this.state = {
            projectName: this.props.projectName,
            state: true,
            projectInfo: this.props.projectInfo,
            displayedUserList: this.props.displayedUserList,
            showButton: true
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

    onChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        const name = event.target.name;
        const value = event.target.value;
        this.setState({[name]: value});
    };

    onSubmitProject = (event: FormEvent<HTMLFormElement>) => {
        this.onUpdate(event, this.state.projectName, this.state.state, this.state.projectInfo, this.state.displayedUserList)
    }

    render() {
        return(
            <div>
                <div >
                    <form className="formUpdate" onSubmit={this.onSubmitProject}>
                        <div className="inpVal">
                            <input type="text" id="projectName" name="projectName" className="form-control" placeholder="Название" onChange={this.onChangeHandler}/>
                        </div>
                        <div className="inpVal">
                            <input type="text" id="projectInfo" name="projectInfo" className="form-control" placeholder="Описание проекта" onChange={this.onChangeHandler}/>
                        </div>
                        <DropDownUserListForUpdate userData={this.props.displayedUserList} onChange={(current: UserDataType[]) => this.setState({displayedUserList: current, showButton: !this.state.showButton})}/>
                        <div>
                            {this.state.showButton && <button type="submit" className="btn btn-outline-dark btn-block mb-4">Изменить</button>}
                            <button onClick={this.handleUpdateUserData.bind(this)} type="button" className="btn btn-outline-dark btn-block mb-4">Назад</button>
                        </div>
                    </form>
                </div>
            </div>
        )
    }

}