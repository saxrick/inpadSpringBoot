import React from 'react';
import {Helmet} from "react-helmet";

class ForgeViewer extends React.Component {

    render() {
        return (
            <>
                <div id="forgeViewer"></div>
                {/*<div id='map'></div>*/}
                <Helmet>
                    {/*<script type="text/javascript" src="/Extensions/app.js"></script>*/}
                    <script type="text/javascript" src="Extensions/aboba.js"></script>
                </Helmet>

            </>

        );
    }
}

export default ForgeViewer;