const scene1 = new THREE.Scene();
scene1.background = new THREE.Color( 0xe0e0e0 );
const camera1 = new THREE.PerspectiveCamera(75, window.innerWidth / window.innerHeight, 0.1, 1000);
const renderer1 = new THREE.WebGLRenderer({ div: document.getElementById('myCanvas') });
renderer1.setSize(window.innerWidth, window.innerHeight);
document.body.appendChild(renderer1.domElement);

// Создание куба с разноцветными гранями
const materials = [
    new THREE.MeshBasicMaterial({ color: 0xFF0000 }), // Красная
    new THREE.MeshBasicMaterial({ color: 0x00FF00 }), // Зеленая
    new THREE.MeshBasicMaterial({ color: 0x0000FF }), // Синяя
    new THREE.MeshBasicMaterial({ color: 0xFFFF00 }), // Желтая
    new THREE.MeshBasicMaterial({ color: 0x00FFFF }), // Голубая
    new THREE.MeshBasicMaterial({ color: 0xFF00FF }), // Розовая
];

const geometry1 = new THREE.BoxGeometry(1, 1, 1);
const cube1 = new THREE.Mesh(geometry1, materials);
scene1.add(cube1);

// Установка камеры так, чтобы она смотрела сверху и слева на куб
camera1.position.set(2, 2, 2);  // Камера будет расположена в верхнем левом углу относительно куба
camera1.lookAt(new THREE.Vector3(0, 0, 0));  // Камера будет смотреть на центр сцены (где куб)

// Переменные для отслеживания изменения размера
let mouseDown = false;
let previousMouseX = 0;
let previousMouseY = 0;
let axisToScale = null; // Ось, по которой тянется грань

// Обработчик событий для нажатия на мышь
window.addEventListener('mousedown', (event) => {
    mouseDown = true;
    previousMouseX = event.clientX;
    previousMouseY = event.clientY;

    // Определение, за какую грань тянет пользователь
    if (event.clientY < window.innerHeight / 3) {
        axisToScale = 'y'; // Тянем верхнюю грань (по оси Y)
    } else if (event.clientX > window.innerWidth / 2) {
        axisToScale = 'x'; // Тянем правую грань (по оси X)
    } else {
        axisToScale = 'z'; // Можно добавлять и другие оси
    }
});

// Обработчик событий для движения мыши
window.addEventListener('mousemove', (event) => {
    if (mouseDown && axisToScale) {
        const deltaX = event.clientX - previousMouseX;
        const deltaY = event.clientY - previousMouseY;

        // Увеличиваем размер по оси в зависимости от направления
        if (axisToScale === 'x') {
            cube1.scale.x += deltaX * 0.01; // Увеличиваем по оси X
            cube1.scale.x = Math.max(0.1, cube1.scale.x); // Ограничение минимального размера
        } else if (axisToScale === 'y') {
            cube1.scale.y += deltaY * 0.01; // Увеличиваем по оси Y
            cube1.scale.y = Math.max(0.1, cube1.scale.y); // Ограничение минимального размера
        } else if (axisToScale === 'z') {
            cube1.scale.z += deltaY * 0.01; // Увеличиваем по оси Z
            cube1.scale.z = Math.max(0.1, cube1.scale.z); // Ограничение минимального размера
        }

        // Обновляем предыдущее положение мыши
        previousMouseX = event.clientX;
        previousMouseY = event.clientY;
    }
});

// Обработчик событий для отпускания кнопки мыши
window.addEventListener('mouseup', () => {
    mouseDown = false;
    axisToScale = null; // Прекращаем перетаскивание
});


// Анимация
function animate() {
    requestAnimationFrame(animate);
    renderer1.render(scene1, camera1);
}

animate();

// Адаптация размера канваса при изменении размера окна
window.addEventListener('resize', () => {
    renderer1.setSize(window.innerWidth, window.innerHeight);
    camera1.aspect = window.innerWidth / window.innerHeight;
    camera1.updateProjectionMatrix();
});



document.getElementById('saveBtn').addEventListener('click', function() {
    var exporter = new THREE.GLTFExporter();
    exporter.parse(
        scene1,
        function (result) {
            var output = JSON.stringify(result, null, 2);
            var blob = new Blob([output], { type: 'application/json' });
            var link = document.createElement('a');
            link.href = URL.createObjectURL(blob);
            link.download = 'scene.gltf';
            link.click();
        },
        { binary: false }
    );
});