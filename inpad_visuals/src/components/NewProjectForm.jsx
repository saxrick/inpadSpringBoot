import React from "react";
import DropDownUserList from "./DropDownUserList.jsx";
import {request, setAuthHeader} from "./axios_helper.js";

export default class NewProjectForm extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            projectName: "",
            state: true,
            projectInfo: "",
            displayedUserList: [this.props.userData],
            showButton: true
        }
    }

    onCreate = (event, projectName, state, projectInfo, userList) => {
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
            (error) => {
                setAuthHeader(null);
            }
        );
    };
    onChangeHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name] : value});
    };

    onSubmitProject = (e) => {
        this.onCreate(e, this.state.projectName, this.state.state, this.state.projectInfo, this.state.displayedUserList)
    }

    render() {
        console.log(this.state.showButton)
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
                        <DropDownUserList userData={this.props.userData} onChange={(current) => this.setState({displayedUserList: current, showButton: !this.state.showButton})}/>
                        <div className="middle-button">
                            {this.state.showButton && <button type="submit" className="btn btn-outline-dark btn-block mb-4">Создать</button>}
                        </div>
                    </form>
                </div>
            </div>
        )
    }
}