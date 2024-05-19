import React from "react";

import {request, setAuthHeader} from "./axios_helper.ts";
import Button from "./Button/Button.tsx";
import {ProjectType} from "../types/ProjectType.tsx";

export default class Project extends React.Component<{projectData: ProjectType, onChange: (current: ProjectType) => void}>{

    constructor(props: { projectData: ProjectType; onChange: (current: ProjectType) => void; }) {
        super(props);

    }

    onclick(){
        request(
            "GET",
            `/projects/${this.props.projectData.id}`,
            {}).then(
            (response) => {
                this.props.onChange(response.data)
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                }
            }
        );
    }

    render() {
        return (
            <li>
                <a>
                    <p>
                        <Button key={this.props.projectData.id}
                                isActive={true}
                                onClick={this.onclick.bind(this)}
                        >{this.props.projectData.projectname}</Button>

                    </p>
                </a>
            </li>
        )
    }
}