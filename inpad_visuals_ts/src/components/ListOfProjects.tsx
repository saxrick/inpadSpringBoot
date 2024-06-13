import React from "react";
import {request, setAuthHeader} from "./axios_helper.ts";
import UpdateProjectForm from "./UpdateProjectForm.tsx";
import OpenedProject from "./OpenedProject.tsx";
import SideProjectList from "./SideProjectList.tsx";
import {UserDataType} from "../types/UserDataType.tsx";
import {ProjectType} from "../types/ProjectType.tsx";
import ForgeViewer from "./ForgeViewer.tsx";

export default class ListOfProjects extends React.Component<{userData: UserDataType}, {userData: UserDataType, currentProject: ProjectType | null, isOpenedUpdateForm: boolean, showForge: boolean}>{
    constructor(props: { userData: UserDataType; }) {
        super(props);
        this.state = {
            userData: this.props.userData,
            currentProject: null,
            isOpenedUpdateForm: false,
            showForge: false,
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
                }
            }
        )
    }

    render() {
        return (
            <>
                {!this.state.showForge && this.state.currentProject && !this.state.isOpenedUpdateForm && <OpenedProject
                    userData={this.state.userData}
                    onChangeUserData={(current: UserDataType) => this.setState({userData: current})}
                    onChangeCurrentProject={(current: ProjectType) => this.setState({currentProject: current})}
                    currentProject={this.state.currentProject}
                    onChange={(current: boolean) => this.setState({isOpenedUpdateForm: current})}
                    onChangeForgeOpen={() => this.setState({showForge: true})}/>}

                {this.state.currentProject && this.state.isOpenedUpdateForm && <div className="sideproj">
                    <UpdateProjectForm onChange={(current: boolean) => this.setState({isOpenedUpdateForm: current})}
                                       // onChangeUserData={(current) => this.setState({userData: current})}
                                       projectId={this.state.currentProject.id}
                                       projectName={this.state.currentProject.projectname}
                                       projectInfo={this.state.currentProject.projectinfo}
                                       displayedUserList={this.state.currentProject.userList}/>
                </div>}

                {this.state.showForge && <ForgeViewer/>}

                {!this.state.showForge && this.state.userData && <SideProjectList userData={this.state.userData}
                                  onChange={(current: ProjectType) => this.setState({currentProject: current})}/>}

            </>
        )
    }

}