import React from "react";
import Button from "../Button/Button.jsx";
import {request, setAuthHeader} from "../axios_helper.js";

export default class Project extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            projectData: this.props.projectData,
            fullData: {},
        }
    }

    render() {
        return (
            <li>
                <a>
                    <p>
                        <Button key={this.state.projectData.id}
                                isActive={this.props.active}
                                onClick={() => {
                                    request(
                                        "GET",
                                        `/projects/${this.state.projectData.id}`,
                                        {}).then(
                                        (response) => {
                                            this.props.onChange(response.data)
                                        }).catch(
                                        (error) => {
                                            if (error.response.status === 401) {
                                                setAuthHeader(null);
                                            } else {
                                                this.setState({data: error.response.code})
                                            }

                                        }
                                    );
                                }}
                        >{this.props.projectData.projectname}</Button>

                    </p>
                </a>
            </li>
        )
    }
}