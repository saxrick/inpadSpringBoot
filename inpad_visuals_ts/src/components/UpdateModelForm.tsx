import React, {ChangeEvent, FormEvent} from "react";
import {request, setAuthHeader} from "./axios_helper.ts";
import DropDownUserListForUpdate from "./DropDownUserListForUpdate.tsx";
import {UserDataType} from "../types/UserDataType.tsx";

export default class UpdateModelForm extends React.Component<{displayedUserList: UserDataType[], onChange: (current: boolean) => void, modelId: number, modelInfo: string, modelName: string}, {modelName: string, state: boolean, modelInfo: string, displayedUserList: UserDataType[], showButton: boolean, message: string}> {
    constructor(props: { displayedUserList: UserDataType[], onChange: (current: boolean) => void, modelId: number, modelInfo: string, modelName: string }) {
        super(props);
        this.state = {
            modelName: this.props.modelName,
            state: true,
            modelInfo: this.props.modelInfo,
            displayedUserList: this.props.displayedUserList,
            showButton: true,
            message: ""
        }
    }

    handleUpdateUserData(){
        this.props.onChange(false)
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

    onUpdate = (event: FormEvent<HTMLFormElement>, modelName: string, state: boolean, modelInfo: string, userList: UserDataType[]) => {
        event.preventDefault();
        request(
            "PUT",
            `/models/${this.props.modelId}`,
            {
                modelName: modelName,
                state: state,
                modelInfo: modelInfo,
                userList: userList,
            }).then(
            () => {

            }).catch(
            () => {
                setAuthHeader(null);
            }
        )
        this.props.onChange(false);
    }


    onChangeProjectNameHandler = (event: ChangeEvent<HTMLInputElement>) => {
        // const name = event.target.name;
        const value = event.target.value;
        this.setState({modelName : value, message: ""});
    };

    onChangeProjectInfoHandler = (event: ChangeEvent<HTMLInputElement>) => {
        // const name = event.target.name;
        const value = event.target.value;
        this.setState({modelInfo : value, message: ""});
    };

    onSubmitProject = (event: FormEvent<HTMLFormElement>) => {
        this.onUpdate(event, this.state.modelName, this.state.state, this.state.modelInfo, this.state.displayedUserList)
    }

    onClickHandler(){
        if (this.state.modelName === "" && this.state.modelInfo !== ""){
            this.setState({message: "Не заполнено поле: Название"})
        } else if (this.state.modelName !== "" && this.state.modelInfo === ""){
            this.setState({message: "Не заполнено поле: Описание проекта"})
        } else if (this.state.modelName === "" && this.state.modelInfo === "") {
            this.setState({message: "Не заполнены поля: Название и Описание проекта"})
        }
    }

    render() {
        console.log(this.state.modelName)
        return(
            <div>
                <div >
                    <form className="formUpdate" onSubmit={this.onSubmitProject}>
                        <div className="inpVal">
                            <input type="text" id="modelName" name="modelName" className="form-control" value={this.state.modelName} placeholder="Название" onChange={this.onChangeProjectNameHandler}/>
                        </div>
                        <div className="inpVal">
                            <input type="text" id="modelInfo" name="modelInfo" className="form-control" value={this.state.modelInfo} placeholder="Описание модели" onChange={this.onChangeProjectInfoHandler}/>
                        </div>
                        <DropDownUserListForUpdate userData={this.props.displayedUserList} onChange={(current: UserDataType[]) => this.setState({displayedUserList: current, showButton: !this.state.showButton})}/>

                        {(this.state.modelName !== "" && this.state.modelInfo !== "") && <div className="middle-button">
                            {this.state.showButton &&
                                <>
                                    <button type="submit" onClick={this.onClickHandler.bind(this)}
                                            className="btn btn-outline-dark btn-block mb-4">Изменить</button>
                                    <button onClick={this.handleUpdateUserData.bind(this)} type="button"
                                            className="btn btn-outline-dark btn-block mb-4">Назад</button>
                                </>
                            }
                        </div>}
                        {(this.state.modelName === "" || this.state.modelInfo === "") &&
                            <div className="middle-button">
                                {this.state.showButton &&
                                    <>
                                        <button type="button" onClick={this.onClickHandler.bind(this)}
                                                className="btn btn-outline-dark btn-block mb-4">Изменить</button>
                                        <button onClick={this.handleUpdateUserData.bind(this)} type="button"
                                                className="btn btn-outline-dark btn-block mb-4">Назад</button>
                                    </>
                                }
                            </div>
                        }
                        <div className="errorMessage">
                            {this.state.message && <p>{this.state.message}</p>}
                        </div>

                        <div>
                            {/*{this.state.showButton && <button type="submit" className="btn btn-outline-dark btn-block mb-4">Изменить</button>}*/}

                        </div>
                    </form>
                </div>
            </div>
        )
    }

}