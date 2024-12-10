import {UserDataType} from "./UserDataType.tsx";

export type ModelType = {
    id: number,
    modelinfo: string,
    modelname: string,
    state: boolean,
    userList: UserDataType[]
}