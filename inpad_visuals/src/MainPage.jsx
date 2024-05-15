import {Header} from "./components/Header.jsx";
import Body from "./components/Body.jsx";
import ListOfProjects from "./components/ListOfProjects.jsx";

import {buttonList} from "./tempData.js";

import * as React from 'react';
import NewProjectForm from "./components/NewProjectForm.jsx";


export default class MainPage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            data: this.props.data,
            tab: 'main',
            userData: props.userData,
            sendUserList: [],
        }
    }

    render() {
        return (
            <>
                <Header active={this.state.tab} onChange={(current) => this.setState({tab: current})} logout={this.props.logout} buttonList={buttonList}/>
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

