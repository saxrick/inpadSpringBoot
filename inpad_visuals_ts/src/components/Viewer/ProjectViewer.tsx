import React from 'react';
import {Helmet} from "react-helmet";


class ProjectViewer extends React.Component<{onChange:(current: boolean) => void}> {
    constructor(props: {onChange:(current: boolean) => void}) {
        super(props);
        this.state = {

        }
    }

    handleClose() {
        this.props.onChange(false)
    }

    render() {
        return (
            <>
                <Helmet>
                    {/*<script type="text/javascript" src="/Extensions/app.js"></script>*/}
                    <script type="text/javascript" src="../../../Extensions/aboba.js"></script>
                    <script type="text/javascript" src="../../../Extensions/map.js"></script>
                </Helmet>


                <div className="input-container">
                    <button className='buttonForm' id="addModelButton">Создать</button>
                    <div className="container">
                        <input type="text" id="X" className="Z" placeholder="X"/>
                        <input type="text" id="Z" className="Z" placeholder="Y"/>
                        <input type="text" id="Y" className="Z" placeholder="Z"/>
                    </div>


                    <button className='buttonForm' id="button1">Модель-1</button>
                    <button className='buttonForm' id="button2">Модель-2</button>
                    <button className='buttonForm' id="button3">Модель-3</button>

                    <div className="container2">
                        <input type="text" className="Z" placeholder="Площадь"/>
                        <input type="text" className="Z" placeholder="Этажность"/>
                        <input type="text" className="Z" placeholder="Назначение"/>
                    </div>

                    <div className='CloseButton'>
                        <button className='buttonForm' type="button">Сохранить</button>
                        <button className='buttonForm' type="button" id="close"
                                onClick={this.handleClose.bind(this)}>Закрыть
                        </button>
                    </div>

                </div>

                <div className="input-container_1">
                    <button className='buttonForm' id="deleteBtn"><img src="/delete.png" className="icon"/></button>
                    <button className='buttonForm' id="setCoordsBtn"><img src="/create.png" className="icon"/></button>
                </div>

                <div id="main">
                    <div id='map'></div>
                </div>
                {/*<div id='map'></div>*/}

            </>

        );
    }
}

export default ProjectViewer;