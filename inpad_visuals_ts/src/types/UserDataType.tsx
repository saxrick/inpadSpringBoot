import {ProjectType} from "./ProjectType.tsx";
import {ModelType} from "./ModelType.tsx";

export type UserDataType = {
    id: number,
    login: string,
    projectList: ProjectType[] | null,
    modelList: ModelType[] | null,
    role: string | null,
    state: boolean,
    token: string,
    username: string
}