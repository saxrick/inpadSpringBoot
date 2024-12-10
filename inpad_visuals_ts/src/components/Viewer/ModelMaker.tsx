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


                <button className='buttonForm' id="saveBtn">Сохранить</button>
                <button className='buttonForm' type="button" id="close" onClick={this.handleClose.bind(this)}>Закрыть</button>


                </div>

            </>
        );
    }
}

export default ModelMaker;