import React from "react";

import {request, setAuthHeader} from "./axios_helper.ts";
import Button from "./Button/Button.tsx";
import {ModelType} from "../types/ModelType.tsx";

export default class Model extends React.Component<{modelData: ModelType, onChange: (current: ModelType) => void}>{

    constructor(props: { modelData: ModelType; onChange: (current: ModelType) => void; }) {
        super(props);

    }

    onclick(){
        request(
            "GET",
            `/models/${this.props.modelData.id}`,
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
                        <Button key={this.props.modelData.id}
                                isActive={true}
                                onClick={this.onclick.bind(this)}
                        >{this.props.modelData.modelname}</Button>

                    </p>
                </a>
            </li>
        )
    }
}