const { Autodesk } = window;
async function init(urn) {

    const options = {
        env: 'AutodeskProduction2',
        api: 'streamingV2',
        getAccessToken: async (onTokenReady) => {
            const token = await (await fetch("https://hd24ouudmhx7ixzla4i6so2atm0fgsex.lambda-url.us-west-2.on.aws")).text();
            onTokenReady(token, 3600);
        }
    };

    Autodesk.Viewing.Initializer(options, () => {
        const div = document.getElementById('forgeViewer');

        const config = { extensions: ["GeoThreeExtension","DrawToolExtension","TransformationExtension", "LoggerExtension","SummaryExtension"] };
        console.log(viewer)
        viewer = new Autodesk.Viewing.Private.GuiViewer3D(div, config);
        console.log(viewer)

        // viewer.addEventListener(Autodesk.Viewing.VIEWER_STATE_CHANGED_EVENT, saveModel); // Save model state on viewer state change
        console.log(viewer)
        viewer.start();
        console.log(viewer)
        viewer.setTheme("light-theme");

        Autodesk.Viewing.Document.load(`urn:${urn}`, (doc) => {
            let viewables = doc.getRoot().search({role:'3d', type: "geometry"})[1];
            viewer.loadDocumentNode(doc, viewables);
            console.log('viewables', viewables);
        });
    });
}


let viewer = null
init('dXJuOmFkc2sub2JqZWN0czpvcy5vYmplY3Q6Y29uc29saWRhdGVkL3JtZV9hZHZhbmNlZF9zYW1wbGVfcHJvamVjdC5ydnQ');
viewer = null
console.log(viewer)

// Function to save model state
// function saveModel() {
//     if (viewer) {
//         const modelState = viewer.getState();
//         console.log('SAVE', modelState);
//         localStorage.setItem('modelState', JSON.stringify(modelState));
//     }
// }
//
//
// // Function to load model state
// function loadModel() {
//     const savedState = localStorage.getItem('modelState');
//     console.log('LOAD', savedState);
//     if (savedState && viewer) {
//         viewer.restoreState(JSON.parse(savedState));
//     }
// }