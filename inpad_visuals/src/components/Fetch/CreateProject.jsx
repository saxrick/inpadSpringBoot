import React from "react";
import {request, setAuthHeader} from "../axios_helper.js";

export default class CreateProject extends React.Component{
    componentDidMount() {
        request(
            "POST",
            "/projects/",
            {}).then(
            (response) => {
                this.setState({data: response.data})
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                } else {
                    this.setState({data: error.response.code})
                }

            }
        );
    }
}