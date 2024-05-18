import Project from "./Project/Project.jsx";
import React from "react";
import {request, setAuthHeader} from "./axios_helper.js";
import UpdateProjectForm from "./UpdateProjectForm.jsx";
import OpenedProject from "./OpenedProject.jsx";
import SideProjectList from "./SideProjectList.jsx";

export default class ListOfProjects extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            userData: this.props.userData,
            currentProject: null,
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

    render() {

        return (
            <section>

                {/*<ul>*/}
                {/*    {this.props.userData.projectList.length && this.state.userData.projectList.sort((a, b) => a.id > b.id ? 1 : -1).map((project) => <Project*/}
                {/*        key={project.id} projectData={project}*/}
                {/*        onChange={(current) => this.setState({currentProject: current})}/>)}*/}
                {/*</ul>*/}
                {this.state.currentProject !== null && !this.state.isOpenedUpdateForm && <OpenedProject
                    userData={this.state.userData}
                    onChangeUserData={(current) => this.setState({userData: current})}
                    onChangeCurrentProject={(current) => this.setState({currentProject: current})}
                    currentProject={this.state.currentProject}
                    onChange={(current) => this.setState({isOpenedUpdateForm: current})}/>}

                {this.state.isOpenedUpdateForm && <div className="sideproj">
                    <UpdateProjectForm onChange={(current) => this.setState({isOpenedUpdateForm: current})}
                                       // onChangeUserData={(current) => this.setState({userData: current})}
                                       projectId={this.state.currentProject.id}
                                       projectName={this.state.currentProject.projectname}
                                       projectInfo={this.state.currentProject.projectinfo}
                                       displayedUserList={this.state.currentProject.userList}/>
                </div>}

                <SideProjectList userData={this.state.userData}
                                 onChange={(current) => this.setState({currentProject: current})}/>

            </section>
        )
    }

}