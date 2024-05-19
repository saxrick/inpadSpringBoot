import React from "react";
import street from "/street.jpg";
import {request, setAuthHeader} from "./axios_helper.ts";
import equal from "fast-deep-equal";
import {ProjectType} from "../types/ProjectType.tsx";
import {UserDataType} from "../types/UserDataType.tsx";

export default class OpenedProject extends React.Component<{currentProject: ProjectType, onChange: (current: boolean) => void, onChangeCurrentProject: (current: ProjectType) => void, onChangeUserData: (current: UserDataType) => void, userData: UserDataType}, {currentProject: ProjectType}> {
    constructor(props: { currentProject: ProjectType; onChange: (current: boolean) => void; onChangeCurrentProject: (current: ProjectType) => void; onChangeUserData: (current: UserDataType) => void; userData: UserDataType; }) {
        super(props);
        this.state = {
            currentProject: this.props.currentProject
        }
    }

    onDelete(id: number){
        request(
            "DELETE",
            `/projects/${id}`,
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
            `/projects/${this.state.currentProject.id}`,
            {}).then(
            (response) => {
                // this.setState({currentProject: response.data})
                this.props.onChangeCurrentProject(response.data)
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
                <h2>{this.props.currentProject.projectname}</h2>
                {this.props.currentProject.userList && this.userListWcomma(this.props.currentProject.userList)}

                {this.props.currentProject.projectinfo && <><p>Описание: {this.props.currentProject.projectinfo}</p>
                    <div className="buttonBlock">
                        <button className="btn btn-outline-dark btn-block mb-4" type="button">Открыть</button>
                        <br/>
                        <button className="btn btn-outline-dark btn-block mb-4"
                                onClick={() => this.props.onChange(true)} type="button">Изменить
                            описание
                        </button>
                        <br/>
                        <button className="btn btn-outline-dark btn-block mb-4"
                                onClick={() => this.onDelete(this.props.currentProject.id)} type="button">Удалить
                        </button>
                    </div>
                </>}
                <div className="projectPic">
                    {this.props.currentProject.projectname && <img className="street" src={street}/>}
                </div>
            </div>
        )
    }
}