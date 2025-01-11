import {Header} from "./components/Header.tsx";
import Body from "./components/Body.tsx";
import ListOfProjects from "./components/ListOfProjects.tsx";

import * as React from 'react';
import NewProjectForm from "./components/NewProjectForm.tsx";
import {UserDataType} from "./types/UserDataType.tsx";
import ModelRedactor from "./components/ModelRedactor.tsx";
import NewModelForm from "./components/NewModelForm.tsx";
import Help from "./components/Help.tsx";


export default class MainPage extends React.Component<{userData: UserDataType, logout: () => void, profile: () => void}, {tab: string, userData: UserDataType}> {
    constructor(props: {userData: UserDataType, logout: () => void, profile: () => void}) {
        super(props);
        this.state = {
            tab: 'main',
            userData: props.userData,
        }
    }

    render() {
        return (
            <>
                <Header active={this.state.tab} onChange={(current: string) => this.setState({tab: current})}
                        logged={() => console.log("")}
                        logout={this.props.logout}
                        profile={this.props.profile}
                        buttonList={[
                            {id: 0, name: 'Главная', active: 'main', path: ""},
                            {id: 1, name: 'Список проектов', active: 'open', path: ""},
                            {id: 2, name: 'Создать новый', active: 'create', path: ""},
                            {id: 3, name: 'Список моделей', active: 'modelRed', path: ""},
                            {id: 4, name: 'Создать модель', active: 'modelCreate', path: ""},
                            {id: 5, name: 'Помощь', active: 'help', path: ""},]}
                        pathList={[
                            {id: 1, pathname: "Профиль", path: this.props.profile},
                            {id: 2, pathname: "Выйти", path: this.props.logout}]}
                />
                {this.state.tab === 'main' && (
                    <Body/>
                )}

                {this.state.tab === 'open' && (<>
                    <ListOfProjects userData={this.state.userData}/>
                </>)}
                {this.state.tab === 'create' && (<>
                    <>
                        <NewProjectForm userData={this.state.userData}/>
                    </>
                </>)}
                {this.state.tab === 'modelRed' && (<>
                    <>
                        <ModelRedactor userData={this.state.userData}/>
                    </>
                </>)}
                {this.state.tab === 'modelCreate' && (<>
                    <>
                        <NewModelForm userData={this.state.userData}/>
                    </>
                </>)}
                {this.state.tab === 'help' && (<>
                    <>
                        <Help/>
                    </>
                </>)}

            </>
        )
    }
}

