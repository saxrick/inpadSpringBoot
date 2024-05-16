import street from '/street.jpg'

import Project from "./Project/Project.jsx";
import React from "react";
import {request, setAuthHeader} from "./axios_helper.js";
import UpdateProjectForm from "./UpdateProjectForm.jsx";

export default class ListOfProjects extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userData: this.props.userData,
            currentProject: {},
            isOpenedUpdateForm: false
        }
    }

    componentDidMount(){
        request(
            "GET",
            `/users/${this.props.userData.id}`,
            {}).then(
            (response) => {
                this.setState({userData: response.data})
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                } else {
                    this.setState({data: error.response.code})
                }
            }
        )
    }

    onDelete(id){
        request(
            "DELETE",
            `/projects/${id}`,
            {}).then(
            (response) => {
                console.log(response)
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                } else {
                    this.setState({data: error.response.code})
                }
            }
        )
    }

    userListWcomma(userList) {
        return(<p>Пользователи: {userList && userList.map((user) => user.username).join(", ")}</p>)
    }

    render() {
        return (
            <section>
                <ul>
                    {this.props.userData.projectList.length && this.state.userData.projectList.sort((a, b) => a.id > b.id ? 1 : -1).map((project) => <Project
                        key={project.id} projectData={project}
                        onChange={(current) => this.setState({currentProject: current})}/>)}

                </ul>
                {!this.state.isOpenedUpdateForm && <div className="sideproj">
                    <h2>{this.state.currentProject.projectname}</h2>
                    {this.state.currentProject.userList && this.userListWcomma(this.state.currentProject.userList)}

                    {this.state.currentProject.projectinfo && <><p>Описание: {this.state.currentProject.projectinfo}</p>
                        <div className="buttonBlock">
                            <button className="btn btn-outline-dark btn-block mb-4" type="button">Открыть</button>
                            <br/>
                            <button className="btn btn-outline-dark btn-block mb-4"
                                    onClick={() => this.setState({isOpenedUpdateForm: true})} type="button">Изменить описание
                            </button>
                            <br/>
                            <button className="btn btn-outline-dark btn-block mb-4"
                                    onClick={() => this.onDelete(this.state.currentProject.id)} type="button">Удалить
                            </button>
                        </div>
                    </>}
                    <div className="projectPic">
                        {this.state.currentProject.projectname && <img className="street" src={street}/>}
                    </div>
                </div>}

                {this.state.isOpenedUpdateForm && <div className="sideproj">
                    <UpdateProjectForm onChange={(current) => this.setState({isOpenedUpdateForm: current})}
                                                                     projectId={this.state.currentProject.id}
                                                                     projectName={this.state.currentProject.projectname}
                                                                     projectInfo={this.state.currentProject.projectinfo}
                                                                     displayedUserList={this.state.currentProject.userList}/>
                </div>}

            </section>
        )
    }

}