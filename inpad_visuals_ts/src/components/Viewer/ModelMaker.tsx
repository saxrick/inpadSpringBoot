import React from 'react';
import {Helmet} from "react-helmet";


class ModelMaker extends React.Component<{onChange:(current: boolean) => void}> {
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
                    <script type="text/javascript" src="../../../Extensions/3dEdit.js"></script>
                </Helmet>
                <div>
                    <div id="myCanvas"></div>
                </div>
                <div className="input-container2">
                    <button className='buttonForm' id="drawModeButton">Режим рисования</button>
                    <button className='buttonForm' id="viewModeButton">Режим камеры</button>
                    <button className='buttonForm' id="fillShapeButton">Закрасить</button>
                    <button className='buttonForm' id="make3DButton">3D</button>
                    <button className='buttonForm' id="saveModelButton">Сохранить</button>
                    <div className="buttonFormClose">
                        <button className='buttonForm' type="button"
                                onClick={this.handleClose.bind(this)}>Закрыть
                        </button>
                    </div>


                </div>

            </>
        );
    }
}

export default ModelMaker;