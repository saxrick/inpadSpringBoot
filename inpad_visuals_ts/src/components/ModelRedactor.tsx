import React from "react";
import {UserDataType} from "../types/UserDataType.tsx";
import {request, setAuthHeader} from "./axios_helper.ts";

import {ModelType} from "../types/ModelType.tsx";
import SideModelList from "./SideModelList.tsx";
import UpdateModelForm from "./UpdateModelForm.tsx";
import OpenedModel from "./OpenedModel.tsx";
import ModelMaker from "./Viewer/ModelMaker.tsx";

export default class ModelRedactor extends React.Component<{userData: UserDataType}, {userData: UserDataType, currentModel: ModelType | null, isOpenedUpdateForm: boolean, showModelMaker: boolean}>{
    constructor(props: { userData: UserDataType; }) {
        super(props);
        this.state = {
            userData: this.props.userData,
            currentModel: null,
            isOpenedUpdateForm: false,
            showModelMaker: false,
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
        console.log(this.state.userData)
    }

    render() {
        return (
            <>
                {!this.state.showModelMaker && this.state.currentModel && !this.state.isOpenedUpdateForm && <OpenedModel
                    userData={this.state.userData}
                    onChangeUserData={(current: UserDataType) => this.setState({userData: current})}
                    onChangeCurrentModel={(current: ModelType) => this.setState({currentModel: current})}
                    currentModel={this.state.currentModel}
                    onChange={(current: boolean) => this.setState({isOpenedUpdateForm: current})}
                    onChangeForgeOpen={() => this.setState({showModelMaker: true})}/>}

                {this.state.currentModel && this.state.isOpenedUpdateForm && <div className="sideproj">
                    <UpdateModelForm onChange={(current: boolean) => this.setState({isOpenedUpdateForm: current})}
                        // onChangeUserData={(current) => this.setState({userData: current})}
                                         modelId={this.state.currentModel.id}
                                         modelName={this.state.currentModel.modelname}
                                         modelInfo={this.state.currentModel.modelinfo}
                                         displayedUserList={this.state.currentModel.userList}/>
                </div>}

                {this.state.showModelMaker && <ModelMaker onChange={(current: boolean) => this.setState({showModelMaker: current})}/>}

                {!this.state.showModelMaker && this.state.userData && <SideModelList userData={this.state.userData}
                                                                                  onChange={(current: ModelType) => this.setState({currentModel: current})}/>}

            </>
        )
    }

}