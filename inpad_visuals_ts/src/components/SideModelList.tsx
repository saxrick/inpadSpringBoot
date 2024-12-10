import React from "react";

import {request, setAuthHeader} from "./axios_helper.ts";
import equal from "fast-deep-equal";
import {UserDataType} from "../types/UserDataType.tsx";

import {ModelType} from "../types/ModelType.tsx";
import Model from "./Model.tsx";

export default class SideModelList extends React.Component<{userData: UserDataType, onChange: (current: ModelType) => void}, {userData: UserDataType}>{
    constructor(props: { userData: UserDataType; onChange: (current: ModelType) => void}) {
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
            <section>
                <ul>
                    {this.state.userData.modelList && this.state.userData.modelList.sort((a, b) => a.id > b.id ? 1 : -1)
                        .map((project) => <Model
                            key={project.id} modelData={project}
                            onChange={(current: ModelType) => this.props.onChange(current)}/>)}
                </ul>
            </section>
        )
    }
}