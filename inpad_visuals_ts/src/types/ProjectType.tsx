import {UserDataType} from "./UserDataType.tsx";

export type ProjectType = {
    id: number,
    projectinfo: string,
    projectname: string,
    state: boolean,
    userList: UserDataType[]
}