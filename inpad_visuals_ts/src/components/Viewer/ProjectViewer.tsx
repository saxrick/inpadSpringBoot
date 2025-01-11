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
                    <button className='buttonForm' id="addModelButton">Модель</button>
                    <button className='buttonForm' id="increaseFloorButton">Этаж</button>
                    <button className='buttonForm' id="moveModelButton">Двигать</button>
                    <button className='buttonForm' id="deleteModelBtn">Удалить</button>
                    <button className='buttonForm' id="returnModelBtn">Вернуть</button>
                    <button className='buttonForm' id="areaBtn">Область</button>
                    <button className='buttonForm' id="contrBtn">Выделить</button>
                    <div className="container">
                        <input type="text" id="X" className='Z' placeholder="X"/>
                        <input type="text" id="Z" className='Z' placeholder="Y"/>
                        <input type="text" id="Y" className='Z' placeholder="Z"/>
                    </div>
                    <div className="container">
                        <input type="range" id="rotationSlider" className="slider" min="0" max="360" value="0"/>
                    </div>
                    <div className="container">
                        <button id="button1" className='buttonForm'>М1</button>
                        <button id="button2" className='buttonForm'>М2</button>
                        <button id="button3" className='buttonForm'>М3</button>
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
                    <button className='buttonForm' id="editBtn"><img src="/edit.png" className="icon"/></button>
                </div>

                <div className="container2">
                    <h3>ТЭП</h3>
                    <input type="text" placeholder="Площадь"/> <input type="text" placeholder="Этажность"/>
                    <input type="text" placeholder="Назначение"/></div>
                <div id="main">
                    <div id='map'></div>
                </div>
            </>

        );
    }
}

export default ProjectViewer;