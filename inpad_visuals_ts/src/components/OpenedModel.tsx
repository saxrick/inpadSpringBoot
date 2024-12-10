import React from "react";
// import street from "/street.jpg";
import {request, setAuthHeader} from "./axios_helper.ts";
import equal from "fast-deep-equal";
import {UserDataType} from "../types/UserDataType.tsx";
import {ModelType} from "../types/ModelType.tsx";

export default class OpenedModel extends React.Component<{currentModel: ModelType, onChange: (current: boolean) => void, onChangeCurrentModel: (current: ModelType) => void, onChangeUserData: (current: UserDataType) => void, userData: UserDataType, onChangeForgeOpen: () => void}, {currentModel: ModelType}> {
    constructor(props: { currentModel: ModelType, onChange: (current: boolean) => void, onChangeCurrentModel: (current: ModelType) => void, onChangeUserData: (current: UserDataType) => void, userData: UserDataType, onChangeForgeOpen: () => void }) {
        super(props);
        this.state = {
            currentModel: this.props.currentModel,
        }
    }

    onDelete(id: number){
        request(
            "DELETE",
            `/models/${id}`,
            {}).then(
            (response) => {
                console.log(response)
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                }
            }
        )
    }

    componentDidMount() {
        request(
            "GET",
            `/users/${this.props.userData.id}`,
            {}).then(
            (response) => {
                this.props.onChangeUserData(response.data)
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                }
            }
        )

        request(
            "GET",
            `/models/${this.state.currentModel.id}`,
            {}).then(
            (response) => {
                // this.setState({currentProject: response.data})
                this.props.onChangeCurrentModel(response.data)
            }).catch(
            (error) => {
                if (error.response.status === 401) {
                    setAuthHeader(null);
                }
            }
        )
        // request(
        //     "GET",
        //     `/users/${this.props.userData.id}`,
        //     {}).then(
        //     (response) => {
        //         this.props.onChangeUserData(response.data)
        //     }).catch(
        //     (error) => {
        //         if (error.response.status === 401) {
        //             setAuthHeader(null);
        //         } else {
        //             this.setState({data: error.response.code})
        //         }
        //     }
        // )
    }

    componentDidUpdate(prevProps: { userData: UserDataType }) {
        if(!equal(this.props.userData, prevProps.userData)){
            this.forceUpdate()
        }
    }


    userListWcomma(userList: UserDataType[]) {
        return(<p>Пользователи: {userList && userList.map((user) => user.username).join(", ")}</p>)
    }

    render() {
        return (
            <div className="sideproj">
                <h2>{this.props.currentModel.modelname}</h2>
                {this.props.currentModel.userList && this.userListWcomma(this.props.currentModel.userList)}

                {this.props.currentModel.modelinfo && <><p>Описание: {this.props.currentModel.modelinfo}</p>
                    <div className="buttonBlock">
                        <button className="btn btn-outline-dark btn-block mb-4" type="button" onClick={this.props.onChangeForgeOpen}>Открыть модель</button>
                        <br/>
                        <button className="btn btn-outline-dark btn-block mb-4"
                                onClick={() => this.props.onChange(true)} type="button">Изменить
                            описание
                        </button>
                        <br/>
                        <button className="btn btn-outline-dark btn-block mb-4"
                                onClick={() => this.onDelete(this.props.currentModel.id)} type="button">Удалить модель
                        </button>
                    </div>
                </>}
                {/*<div className="projectPic">*/}
                {/*    {this.props.currentProject.projectname && <img className="street" src={street}/>}*/}
                {/*</div>*/}
            </div>
        )
    }
}