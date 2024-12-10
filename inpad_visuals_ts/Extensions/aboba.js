mapboxgl.accessToken = 'pk.eyJ1Ijoid2FsbGFieXdheSIsImEiOiJjazBuaDQ5OGgxaHFwM2NvMm8wN2Ewb2xpIn0.1XKDCgUA5YKI_U9NGh4fqg';
const map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/light-v11',
    center: [60.605525, 56.827463],
    zoom: 3,
    pitch: 46.9,
    bearing: 15,
    antialias: true
});

function smoothZoom(newZoom) {
    map.flyTo({
        zoom: newZoom,
        speed: 0.3,
        curve: 5,
        essential: true
    });
}

smoothZoom(18);


document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('goButton').addEventListener('click', function() {
        const input = document.getElementById('coordinatesInput').value;
        const coords = input.split(',').map(Number);

        if (coords.length === 2 && !isNaN(coords[0]) && !isNaN(coords[1])) {
            map.flyTo({
                center: coords,
                zoom: 15.4,
                essential: true
            });
            const inputContainer = document.querySelector('.input-container');
            if (inputContainer) {
                inputContainer.style.display = 'none';
            }
        } else {
            alert('Пожалуйста, введите корректные координаты (lng, lat)');
        }
    });
});

var isDeletingMode = false;
var isCreatingMode = true;


var nameObj = '../../../Extensions/scene_1.gltf';

document.getElementById('deleteBtn').addEventListener('click', function() {
    isCreatingMode = false;
    isDeletingMode = true;
});

document.getElementById('setCoordsBtn').addEventListener('click', function() {
    isDeletingMode = false;
    isCreatingMode = true;
});


document.getElementById('button1').addEventListener('click', () => {
    nameObj ='../../../Extensions/scene_1.gltf';
});

document.getElementById('button2').addEventListener('click', () => {
    nameObj = '../../../Extensions/scene_2.gltf';
});

document.getElementById('button3').addEventListener('click', () => {
    nameObj = '../../../Extensions/scene_3.gltf';
});

var modelHeight = 10;
var modelCoordinates = null;

map.on('load', function () {
    document.getElementById('addModelButton').addEventListener('click', addModelToMap);

    map.on('click', function(e) {
        if(isCreatingMode){
            modelCoordinates = e.lngLat;
        }
    });
});

let model;

function addModelToMap() {
    var modelLayerId = 'custom-threebox-model-' + modelHeight;

    var xValue = parseFloat(document.getElementById('X').value) || 1;
    var yValue = parseFloat(document.getElementById('Y').value) || 1;
    var zValue = parseFloat(document.getElementById('Z').value) || 1;

    map.addLayer({
        id: modelLayerId,
        type: 'custom',
        renderingMode: '3d',
        onAdd: function () {
            const scale = 10;
            const options = {
                obj: nameObj,
                type: 'gltf',
                scale: { x: scale * xValue, y: modelHeight / 10 * yValue, z: scale * zValue },
                units: 'meters',
                rotation: { x: 0, y: 0 , z: 0 }
            };

            tb.loadObj(options, (loadedModel) => {
                model = loadedModel;
                if (modelCoordinates) {
                    model.setCoords([modelCoordinates.lng, modelCoordinates.lat]);
                }
                tb.add(model);
            });
        },
        render: function () {
            tb.update();
        }
    });

    modelHeight += 50;
}

const tb = (window.tb = new Threebox(
    map,
    map.getCanvas().getContext('webgl'),
    {
        defaultLights: true
    }
));

map.on('style.load', () => {

    map.addLayer({
        'id': '3d-buildings',
        'source': 'composite',
        'source-layer': 'building',
        'filter': ['==', 'extrude', 'true'],
        'type': 'fill-extrusion',
        'minzoom': 15,
        'paint': {
            'fill-extrusion-color': '#aaa',
            'fill-extrusion-height': [
                'interpolate',
                ['linear'],
                ['zoom'],
                15,
                0,
                15.05,
                ['get', 'height']
            ],
            'fill-extrusion-base': [
                'interpolate',
                ['linear'],
                ['zoom'],
                15,
                0,
                15.05,
                ['get', 'min_height']
            ],
            'fill-extrusion-opacity': 0.6
        }
    });
});
map.on('style.load', function() {
    map.on('click', function(e) {

        var features = map.queryRenderedFeatures(e.point, {
            layers: ['3d-buildings']
        });

        if (!features.length) {
            return;
        }

        var feature = features[0];

        if(isDeletingMode){
            console.log(feature.id, feature.properties.height);

            map.setFeatureState(
                { source: 'composite', sourceLayer: 'building', id: feature.id },
                { height: 0 }
            );

            map.setPaintProperty('3d-buildings', 'fill-extrusion-height', [
                'case',
                ['==', ['feature-state', 'height'], 0],
                0,
                ['get', 'height']
            ]);
        }
    });
});