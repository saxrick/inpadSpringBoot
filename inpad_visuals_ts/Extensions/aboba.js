mapboxgl.accessToken = 'pk.eyJ1Ijoid2FsbGFieXdheSIsImEiOiJjazBuaDQ5OGgxaHFwM2NvMm8wN2Ewb2xpIn0.1XKDCgUA5YKI_U9NGh4fqg';
const map = new mapboxgl.Map({
    container: 'map',
    style: 'mapbox://styles/mapbox/light-v11',
    center: [60.605525, 56.827463],
    zoom: 15.3,
    pitch: 46.9,
    bearing: 15,
    antialias: true
});

var isDeletingMode = false;
var isCreatingMode = true;
var isEditingMode = false;
var isDeletingModelMode = false;
var areaMode = false;
var nameObj = '../../../Extensions/scene_1.gltf';

//вкл.откл. камеры
function toggleMapInteractions(enable) {
    if (enable) {
        map.dragPan.enable();
        map.scrollZoom.enable();
    } else {
        map.dragPan.disable();
        map.scrollZoom.disable();
    }
}

//смена режима
document.getElementById('deleteBtn').addEventListener('click', function() {
    isCreatingMode = false;
    isDeletingMode = true;
    isEditingMode = false;
    toggleMapInteractions(true);
});

document.getElementById('setCoordsBtn').addEventListener('click', function() {
    isDeletingMode = false;
    isCreatingMode = true;
    isEditingMode = false;
    toggleMapInteractions(true);
});

document.getElementById('editBtn').addEventListener('click', function() {
    isCreatingMode = false;
    isDeletingMode = false;
    isEditingMode = true;
    toggleMapInteractions(false);
});

document.getElementById('contrBtn').addEventListener('click', function() {
    areaMode = !areaMode;
    if (areaMode) {
        this.textContent = 'Назад';
        this.style.backgroundColor = '#000000';
        this.style.color = '#ffffff'
    } else {
        this.textContent = 'Выделить';
        this.style.backgroundColor = '#ffffff';
        this.style.color = '#000000'
    }
});

//смена модели
document.getElementById('button1').addEventListener('click', () => {
    nameObj = '../../../Extensions/scene_five.gltf';
});

document.getElementById('button2').addEventListener('click', () => {
    nameObj = '../../../Extensions/scene_parl.gltf';
});

document.getElementById('button3').addEventListener('click', () => {
    nameObj = '../../../Extensions/scene_ringle.gltf';
});


document.getElementById('deleteModelBtn').addEventListener('click', () => {
    const lastModel = models[models.length - 1];

    if (lastModel) {
        deleteModel(lastModel.layerId);
    }
});

document.getElementById('returnModelBtn').addEventListener('click', () => {
    restoreLastDeletedModel();
});

const deletedModels = [];

//удаление моделей
function deleteModel(modelLayerId) {
    const modelIndex = models.findIndex(model => model.layerId === modelLayerId);
    if (modelIndex !== -1) {
        const model = models.splice(modelIndex, 1)[0];
        const modelParams = {
            modelLayerId: model.layerId,
            obj: model.obj,
            scale: model.scale,
            units: model.units,
            rotation: model.rotation,
            coords: model.coords
        };
        deletedModels.push(modelParams);

        if (map.getLayer(modelLayerId)) {
            map.removeLayer(modelLayerId);
        }

        tb.remove(model);
    }
}

var modelHeight = 0;
var modelCoordinates = null;
var currentModelHeight = 0;
var lastModelYValue = 0;
var scaltTemp = 1;
var models = [];
var modelCoords = [];
var isDragging = false;
var draggingModel = null;
var startCoords = null;

map.on('load', function () {
    document.getElementById('addModelButton').addEventListener('click', addModelToMap);
    document.getElementById('increaseFloorButton').addEventListener('click', increaseFloor);
    document.getElementById('rotationSlider').addEventListener('input', updateModelRotation);

    //задача координат для модели
    map.on('click', function(e) {
        if (isCreatingMode) {
            modelCoordinates = e.lngLat;
            console.log(modelCoordinates);
        }
    });
    //логика перемещения модели
    map.on('mousedown', function(e) {
        if (isEditingMode && e.originalEvent.button === 0) {
            console.log('MDown');

            var modelLayerId = 'custom-threebox-model-' + 0;
            console.log('Checking layer:', modelLayerId);
            console.log(map.getLayer(modelLayerId));
            if (map.getLayer(modelLayerId)) {
                console.log('MDown123');
                var features = map.queryRenderedFeatures(e.point, { layers: [modelLayerId] });
                console.log(features);

                console.log('MDown567123');
                isDragging = true;
                draggingModel = models[models.length - 1];
                startCoords = modelCoords[modelCoords.length - 1];
            }
        }
    });

    map.on('mousemove', function(e) {
        if (isDragging && draggingModel) {
            console.log('MMove');
            var newCoords = [e.lngLat.lng, e.lngLat.lat, startCoords[2]];
            draggingModel.setCoords(newCoords);
            modelCoords[modelCoords.length - 1] = newCoords;
        }
    });

    map.on('mouseup', function(e) {
        if (isEditingMode && e.originalEvent.button === 0) {
            console.log('MUp');
            isDragging = false;
            draggingModel = null;
            startCoords = null;
        }
    });
});

var tempOptions =[];
//добавление модели
function addModelToMap() {
    var modelLayerId = 'custom-threebox-model-' + modelHeight;
    console.log('Adding model layer:', modelLayerId);

    var xValue = parseFloat(document.getElementById('X').value) || 1;
    var zValue = parseFloat(document.getElementById('Z').value) || 1;
    var yValue = parseFloat(document.getElementById('Y').value) || 1;

    currentModelHeight = 0;

    map.addLayer({
        id: modelLayerId,
        type: 'custom',
        renderingMode: '3d',
        paint: {
            'model-type': 'location-indicator'
        },
        onAdd: function () {
            const scale = 10;
            scaltTemp = scale;
            const options = {
                obj: nameObj,
                type: 'gltf',
                scale: { x: scale * xValue, y: scale * yValue, z: scale * zValue },
                units: 'meters',
                rotation: { x: 90, y: 0 , z: 0 },
                anchor: 'center'
            };
            console.log('при создании', options);
            tempOptions.push(options);
            tb.loadObj(options, (loadedModel) => {
                var model = loadedModel;
                model.layerId = modelLayerId;
                if (modelCoordinates) {
                    model.setCoords([modelCoordinates.lng, modelCoordinates.lat, currentModelHeight]);
                    modelCoords.push([modelCoordinates.lng, modelCoordinates.lat, currentModelHeight]);
                }
                console.log([modelCoordinates.lng, modelCoordinates.lat, currentModelHeight]);
                tb.add(model);
                models.push(model);
            });
        },
        render: function () {
            tb.update();
        }
    });

    lastModelYValue = yValue;
    modelHeight += 50;
}

//добавление этажа
function increaseFloor() {
    if (modelCoords.length > 0) {
        var lastModelCoords = modelCoords[modelCoords.length - 1];

        var xValue = parseFloat(document.getElementById('X').value) || 1;
        var zValue = parseFloat(document.getElementById('Z').value) || 1;
        var yValue = parseFloat(document.getElementById('Y').value) || 1;

        currentModelHeight = lastModelCoords[2] + lastModelYValue * scaltTemp;

        var modelLayerId = 'custom-threebox-model-' + modelHeight;
        console.log('Increasing floor, adding model layer:', modelLayerId);

        map.addLayer({
            id: modelLayerId,
            type: 'custom',
            renderingMode: '3d',
            onAdd: function () {
                const scale = 10;
                scaltTemp = scale;
                const options = {
                    obj: nameObj,
                    type: 'gltf',
                    scale: { x: scale * xValue, y: scale * yValue, z: scale * zValue },
                    units: 'meters',
                    rotation: { x: 90, y: 0 , z: 0 }
                };

                tb.loadObj(options, (loadedModel) => {
                    var model = loadedModel;
                    model.layerId = modelLayerId; // Добавляем идентификатор слоя к модели
                    model.setCoords([modelCoordinates.lng, modelCoordinates.lat, currentModelHeight]);
                    modelCoords.push([modelCoordinates.lng, modelCoordinates.lat, currentModelHeight]);
                    tb.add(model);
                    models.push(model);
                });
            },
            render: function () {
                tb.update();
            }
        });

        lastModelYValue = yValue;
        modelHeight += 50;
    }
}

//поворот модели
function updateModelRotation() {
    var rotationValue = document.getElementById('rotationSlider').value;
    models.forEach(model => {
        model.setRotation({ x: 0, y: 0, z: rotationValue });
    });
}

//канвас для 3д моделей
const tb = (window.tb = new Threebox(
    map,
    map.getCanvas().getContext('webgl'),
    {
        defaultLights: true
    }
));

//подругзка 3д зданий
map.on('style.load', () => {
    map.addLayer({
        'id': '3d-buildings',
        'source': 'composite',
        'source-layer': 'building',
        'filter': ['==', 'extrude', 'true'],
        'type': 'fill-extrusion',
        'minzoom': 15,
        'paint': {
            'fill-extrusion-color': '#979797',
            'fill-extrusion-height': [
                'interpolate',
                ['linear'],
                ['zoom'],
                15,
                0,
                15.05,
                ['*', ['get', 'height'], 1]
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
            'fill-extrusion-opacity': 1.0,
            'fill-extrusion-cast-shadows': false
        }
    });
});

let coordinates = [
    [-180, 90],
    [180, 90],
    [180, -90],
    [-180, -90],
    [-180, 90]
];

let currentLine = null;
let currentLineSource = null;

//отрисовка линии
map.on('click', (e) => {
    if (areaMode) {
        const { lng, lat } = e.lngLat;
        coordinates.push([lng, lat]);
        console.log('Coordinates:', coordinates);

        if (currentLine) {
            if (map.getLayer(currentLine)) {
                map.removeLayer(currentLine);
            }
            if (map.getSource(currentLineSource)) {
                map.removeSource(currentLineSource);
            }
        }

        if (coordinates.length > 1) {
            currentLine = 'temp-line';
            currentLineSource = 'temp-line-source';

            const source = map.getSource(currentLineSource);
            if (source) {
                source.setData({
                    'type': 'Feature',
                    'geometry': {
                        'type': 'LineString',
                        'coordinates': coordinates.slice(5)
                    }
                });
            } else {
                map.addSource(currentLineSource, {
                    'type': 'geojson',
                    'data': {
                        'type': 'Feature',
                        'geometry': {
                            'type': 'LineString',
                            'coordinates': coordinates.slice(5)
                        }
                    }
                });

                map.addLayer({
                    'id': currentLine,
                    'type': 'line',
                    'source': currentLineSource,
                    'layout': {
                        'line-join': 'round',
                        'line-cap': 'round'
                    },
                    'paint': {
                        'line-color': 'red',
                        'line-width': 2
                    }
                });
            }
        }
    }
});

map.on('mousemove', (e) => {
    if (areaMode && coordinates.length > 0) {
        const tempCoordinates = [...coordinates, [e.lngLat.lng, e.lngLat.lat]];

        const source = map.getSource(currentLineSource);
        if (source) {
            source.setData({
                'type': 'Feature',
                'geometry': {
                    'type': 'LineString',
                    'coordinates': tempCoordinates
                }
            });
        }
    }
});

//выделение области
document.getElementById('areaBtn').addEventListener('click', () => {
    const clipLayer = map.getLayer('eraser');
    const whiteAreaLayer = map.getLayer('white-area');
    const debugLayer = map.getLayer('eraser-debug');

    if (clipLayer && whiteAreaLayer && debugLayer) {
        map.removeLayer('eraser');
        map.removeLayer('white-area');
        map.removeLayer('eraser-debug');
    } else {
        if (coordinates.length < 3) {
            alert('Please click at least 3 points on the map to create a polygon.');
            return;
        }

        coordinates.push(coordinates[0]);

        const source = map.getSource('eraser');
        if (source) {
            source.setData({
                'type': 'FeatureCollection',
                'features': [
                    {
                        'type': 'Feature',
                        'properties': {},
                        'geometry': {
                            'type': 'Polygon',
                            'coordinates': [coordinates]
                        }
                    }
                ]
            });
        } else {
            map.addSource('eraser', {
                'type': 'geojson',
                'data': {
                    'type': 'FeatureCollection',
                    'features': [
                        {
                            'type': 'Feature',
                            'properties': {},
                            'geometry': {
                                'type': 'Polygon',
                                'coordinates': [coordinates.slice(5,-1)]
                            }
                        }
                    ]
                }
            });
        }

        map.addLayer({
            'id': 'eraser',
            'type': 'clip',
            'source': 'eraser',
            'layout': {
                'clip-layer-types': ['symbol', 'model']
            }
        });
        map.addLayer({
            'id': 'white-area',
            'type': 'fill',
            'source': 'eraser',
            'paint': {
                'fill-color': 'white'
            }
        });

        if (currentLine) {
            if (map.getLayer(currentLine)) {
                map.removeLayer(currentLine);
            }
            if (map.getSource(currentLineSource)) {
                map.removeSource(currentLineSource);
            }
        }
    }
});

//удаление зданий карты
map.on('style.load', function() {
    map.addSource('eraser', {
        'type': 'geojson',
        'data': {
            'type': 'FeatureCollection',
            'features': []
        }
    });

    map.addLayer({
        'id': 'eraser',
        'type': 'clip',
        'source': 'eraser',
        'layout': {
            'clip-layer-types': ['symbol', 'model']
        }
    });

    map.on('click', function(e) {
        var features = map.queryRenderedFeatures(e.point, {
            layers: ['3d-buildings']
        });

        if (!features.length) {
            return;
        }

        var feature = features[0];

        if (isDeletingMode) {
            console.log(feature.id, feature.properties.height);

            map.setFeatureState(
                { source: 'composite', sourceLayer: 'building', id: feature.id },
                { height: 0 }
            );

            map.setPaintProperty('3d-buildings', 'fill-extrusion-height', [
                'case',
                ['==', ['feature-state', 'height'], 0],
                -10,
                ['get', 'height']
            ]);

            var eraserData = {
                'type': 'Feature',
                'properties': {},
                'geometry': {
                    'type': 'Polygon',
                    'coordinates': [feature.geometry.coordinates[0]]
                }
            };

            var source = map.getSource('eraser');
            var data = source._data;
            data.features.push(eraserData);
            source.setData(data);
        }
    });
});