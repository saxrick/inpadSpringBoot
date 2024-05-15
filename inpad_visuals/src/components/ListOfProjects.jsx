import street from '/street.jpg'

import Project from "./Project/Project.jsx";
import React from "react";
import {request, setAuthHeader} from "./axios_helper.js";

export default class ListOfProjects extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userData: this.props.userData,
            currentProject: {}
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

    userListWcomma(userList) {
        return(<p>Пользователи: {userList && userList.map((user) => user.username).join(", ")}</p>)
    }

    render() {

        return (
            <section>
                <ul>
                    {this.props.userData.projectList.length && this.state.userData.projectList.map((project) => <Project
                        key={project.id} projectData={project}
                        onChange={(current) => this.setState({currentProject: current})}/>)}

                </ul>
                <div className="sideproj">
                    <h2>{this.state.currentProject.projectname}</h2>
                    {this.state.currentProject.userList && this.userListWcomma(this.state.currentProject.userList)}

                            {this.state.currentProject.projectinfo && <><p>Описание: {this.state.currentProject.projectinfo}</p>
                                <div className="buttonBlock">
                                    <button className="btn btn-outline-dark btn-block mb-4">Открыть</button>
                                    <br/>
                                    <button className="btn btn-outline-dark btn-block mb-4">Изменить описание</button>
                                    <br/>
                                    <button className="btn btn-outline-dark btn-block mb-4">Удалить</button>
                                </div>
                            </>}
                    <div className="projectPic">
                        {this.state.currentProject.projectname && <img className="street" src={street}/>}
                    </div>
                </div>
            </section>
        )
    }

}