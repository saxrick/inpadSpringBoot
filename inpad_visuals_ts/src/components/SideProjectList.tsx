import React from "react";
import Project from "./Project.tsx"
import {request, setAuthHeader} from "./axios_helper.ts";
import equal from "fast-deep-equal";
import {UserDataType} from "../types/UserDataType.tsx";
import {ProjectType} from "../types/ProjectType.tsx";

export default class SideProjectList extends React.Component<{userData: UserDataType, onChange: (current: ProjectType) => void, }, {userData: UserDataType}>{
    constructor(props: { userData: UserDataType; onChange: (current: ProjectType) => void}) {
        super(props);
        this.state = {
            userData: this.props.userData
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

    componentDidUpdate(prevProps: { userData: UserDataType }) {
        if(!equal(this.props.userData, prevProps.userData)){
            this.setState({userData: this.props.userData})
        }
    }

    render() {
        return (
            <>
                <ul>
                    {this.state.userData.projectList && this.state.userData.projectList.sort((a, b) => a.id > b.id ? 1 : -1)
                        .map((project) => <Project
                            key={project.id} projectData={project}
                            onChange={(current: ProjectType) => this.props.onChange(current)}/>)}
                </ul>
            </>
        )
    }
}