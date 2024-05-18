import React from "react";
import {request, setAuthHeader} from "./axios_helper.js";
import DropDownUserListForUpdate from "./DropDownUserListForUpdate.jsx";

export default class UpdateProjectForm extends React.Component {
    constructor(props) {
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

    onUpdate = (event, projectName, state, projectInfo, userList) => {
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
            (response) => {

            }).catch(
            (error) => {
                setAuthHeader(null);
            }
        )
        this.props.onChange(false);
    }

    onChangeHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name] : value});
    };

    onSubmitProject = (e) => {
        this.onUpdate(e, this.state.projectName, this.state.state, this.state.projectInfo, this.state.displayedUserList)
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
                        <DropDownUserListForUpdate userData={this.props.displayedUserList} onChange={(current) => this.setState({displayedUserList: current, showButton: !this.state.showButton})}/>
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