const scene1 = new THREE.Scene();
scene1.background = new THREE.Color(0xffffff);
const camera1 = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
const renderer1 = new THREE.WebGLRenderer();
renderer1.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer1.domElement);

const drawModeButton = document.getElementById('drawModeButton');
const viewModeButton = document.getElementById('viewModeButton');
const fillShapeButton = document.getElementById('fillShapeButton');
const make3DButton = document.getElementById('make3DButton');
const saveModelButton = document.getElementById('saveModelButton');
// const closedButton = document.getElementById('closedButton');

let isDrawing = false;
let isDrawMode = true;
let vectors = [];
let currentVector = null;
let shape = null;
let filledShape = null;
let modelGroup = new THREE.Group(); // Группа для хранения всех объектов модели

// Добавляем OrbitControls для вращения камеры
const controls = new THREE.OrbitControls(camera1, renderer1.domElement);
controls.enableDamping = true; // Плавное вращение
controls.dampingFactor = 0.25;
controls.enableZoom = true;
controls.enabled = false; // Начинаем с отключенного состояния

// Добавляем сетку
const gridHelper = new THREE.GridHelper(10, 10);
scene1.add(gridHelper);

renderer1.domElement.addEventListener('mousedown', startDrawing);
renderer1.domElement.addEventListener('mousemove', draw);
renderer1.domElement.addEventListener('mouseup', endDrawing);
// closedButton.addEventListener('click', closed);
drawModeButton.addEventListener('click', () => setMode(true));
viewModeButton.addEventListener('click', () => setMode(false));
fillShapeButton.addEventListener('click', fillShape);
make3DButton.addEventListener('click', make3D);
saveModelButton.addEventListener('click', saveModel);

camera1.position.set(5, 5, 5);
camera1.lookAt(scene1.position);



function setMode(drawMode) {
    isDrawMode = drawMode;
    controls.enabled = !drawMode;
    if (drawMode) {
        drawModeButton.style.backgroundColor = '#90EE90';
        viewModeButton.style.backgroundColor = '';
    } else {
        drawModeButton.style.backgroundColor = '';
        viewModeButton.style.backgroundColor = '#90EE90';
    }
}

function startDrawing(event) {
    if (!isDrawMode) return;
    isDrawing = true;
    const [x, z] = getMousePos(event);
    currentVector = { start: { x, z }, end: { x, z } };
    vectors.push(currentVector);
}

function draw(event) {
    if (!isDrawMode || !isDrawing) return;
    let [x, z] = getMousePos(event);

    // Примагничивание к началу первой линии
    if (vectors.length > 1) {
        const start = vectors[0].start;
        const distance = Math.hypot(x - start.x, z - start.z);
        const threshold = 0.05; // Порог для примагничивания
        if (distance < threshold) {
            x = start.x;
            z = start.z;
        }
    }

    currentVector.end = { x, z };
    redraw();
}

function endDrawing() {
    if (!isDrawMode) return;
    isDrawing = false;
    currentVector = null;
}

function getMousePos(event) {
    const rect = renderer1.domElement.getBoundingClientRect();
    const mouse = new THREE.Vector2();
    mouse.x = ((event.clientX - rect.left) / rect.width) * 2 - 1;
    mouse.y = -((event.clientY - rect.top) / rect.height) * 2 + 1;

    const raycaster = new THREE.Raycaster();
    raycaster.setFromCamera(mouse, camera1);

    const plane = new THREE.Plane(new THREE.Vector3(0, 1, 0), 0);
    const intersection = new THREE.Vector3();
    raycaster.ray.intersectPlane(plane, intersection);

    return [intersection.x, intersection.z];
}

function redraw() {
    while (modelGroup.children.length > 0) { // Очищаем группу модели
        modelGroup.remove(modelGroup.children[0]);
    }
    vectors.forEach(vector => {
        const material = new THREE.LineBasicMaterial({ color: 0xFF0000 });
        const geometry = new THREE.BufferGeometry().setFromPoints([
            new THREE.Vector3(vector.start.x, 0, vector.start.z),
            new THREE.Vector3(vector.end.x, 0, vector.end.z)
        ]);
        const line = new THREE.Line(geometry, material);
        modelGroup.add(line);
    });
    if (filledShape) {
        modelGroup.add(filledShape);
    }
    scene1.add(modelGroup);
    renderer1.render(scene1, camera1);
}

// function closed() {    cancelAnimationFrame(animate);
//     while (scene1.children.length > 0) {        scene1.remove(scene1.children[0]);
//     }    geometry.dispose();
//     material.dispose();
//     document.body.removeChild(renderer1.domElement);
//     renderer1.dispose();
//
//     document.body.removeChild(scene1.domElement);
//     scene1.dispose();
//     document.body.removeChild(camera1.domElement);
//     camera1.dispose();}


function fillShape() {
    if (vectors.length < 3 || !isShapeClosed()) {
        alert('Shape is not closed or has less than 3 points.');
        return;
    }
    shape = new THREE.Shape();
    shape.moveTo(vectors[0].start.x, -vectors[0].start.z); // Используем отрицательную z-координату
    vectors.forEach(vector => {
        shape.lineTo(vector.end.x, -vector.end.z); // Используем отрицательную z-координату
    });
    shape.lineTo(vectors[0].start.x, -vectors[0].start.z); // Используем отрицательную z-координату

    const geometry = new THREE.ShapeGeometry(shape);
    const material = new THREE.MeshBasicMaterial({ color: 0x90EE90, side: THREE.DoubleSide });
    filledShape = new THREE.Mesh(geometry, material);
    filledShape.rotation.x = -Math.PI / 2; // Поворачиваем фигуру на 90 градусов вокруг оси X
    modelGroup.add(filledShape);
    scene1.add(modelGroup);
}

function make3D() {
    if (shape) {
        const extrudeSettings = { depth: 1, bevelEnabled: false };
        const geometry = new THREE.ExtrudeGeometry(shape, extrudeSettings);
        const material = new THREE.MeshBasicMaterial({ color: 0x90EE90 });
        const mesh = new THREE.Mesh(geometry, material);
        mesh.rotation.x = -Math.PI / 2; // Поворачиваем фигуру на 90 градусов вокруг оси X
        modelGroup.add(mesh);

        // Добавляем обводку для всех ребер
        const edges = new THREE.EdgesGeometry(geometry);
        const lineMaterial = new THREE.LineBasicMaterial({ color: 0x000000 });
        const lineMesh = new THREE.LineSegments(edges, lineMaterial);
        lineMesh.rotation.x = -Math.PI / 2; // Поворачиваем обводку на 90 градусов вокруг оси X
        modelGroup.add(lineMesh);

        scene1.add(modelGroup);
    }
}

function isShapeClosed() {
    const start = vectors[0].start;
    const end = vectors[vectors.length - 1].end;
    return Math.abs(start.x - end.x) < 0.01 && Math.abs(start.z - end.z) < 0.01;
}

function saveModel() {
    const exporter = new THREE.GLTFExporter();
    exporter.parse(modelGroup, (result) => {
        const output = JSON.stringify(result, null, 2);
        saveString(output, 'scene1.gltf');
    }, (error) => {
        console.error(error);
    });
}

function saveString(text, filename) {
    const blob = new Blob([text], { type: 'text/plain' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = filename;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}

function animate() {
    requestAnimationFrame(animate);
    controls.update(); // Обновляем контролы камеры
    renderer1.render(scene1, camera1);
}

animate();
