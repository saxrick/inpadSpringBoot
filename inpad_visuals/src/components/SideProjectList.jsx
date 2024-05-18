import React from "react";
import Project from "./Project/Project.jsx";
import {request, setAuthHeader} from "./axios_helper.js";
import equal from "fast-deep-equal";

export default class SideProjectList extends React.Component {
    constructor(props) {
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
                } else {
                    this.setState({data: error.response.code})
                }
            }
        )
    }

    componentDidUpdate(prevProps) {
        if(!equal(this.props.userData, prevProps.userData)){
            this.setState({userData: this.props.userData})
        }
    }

    render() {
        return (
            <>
                <ul>
                    {this.state.userData.projectList.length && this.state.userData.projectList.sort((a, b) => a.id > b.id ? 1 : -1)
                        .map((project) => <Project
                            key={project.id} projectData={project}
                            onChange={(current) => this.props.onChange(current)}/>)}
                </ul>
            </>
        )
    }
}