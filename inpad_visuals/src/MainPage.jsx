import {Header} from "./components/Header.jsx";
import Body from "./components/Body.jsx";
import ListOfProjects from "./components/ListOfProjects.jsx";

import * as React from 'react';
import NewProjectForm from "./components/NewProjectForm.jsx";


export default class MainPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: this.props.data,
            tab: 'main',
            userData: props.userData,
        }
    }

    render() {
        return (
            <>
                <Header active={this.state.tab} onChange={(current) => this.setState({tab: current})}
                        logout={this.props.logout}
                        profile={this.props.profile}
                        buttonList={[
                            {id: 0, name: 'Главная', active: 'main', path: ""},
                            {id: 1, name: 'Список проектов', active: 'open', path: ""},
                            {id: 2, name: 'Создать новый', active: 'create', path: ""}]}
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
                        <NewProjectForm userData={this.state.userData} onCreate={this.onCreate}/>
                    </>
                </>)}

            </>
        )
    }
}

