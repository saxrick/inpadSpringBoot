import {ProjectType} from "./ProjectType.tsx";

export type UserDataType = {
    id: number,
    login: string,
    projectList: ProjectType[] | null,
    role: string | null,
    state: boolean,
    token: string,
    username: string
}