const canvas = document.getElementById('myCanvas');
const context = canvas.getContext('webgl');


const scene = new THREE.Scene();
const camera = new THREE.PerspectiveCamera(75, canvas.width / canvas.height, 0.1, 1000);
const renderer = new THREE.WebGLRenderer({ canvas, context });

const listener = new THREE.AudioListener();
camera.add( listener );

//
// const sound = new THREE.Audio( listener );
// const audioLoader = new THREE.AudioLoader();
// audioLoader.load( 'БАХМУТ III.wav', function( buffer ) {
//     sound.setBuffer( buffer );
//     sound.setLoop( true );
//     sound.setVolume( 0.5 );
//     sound.play();
// });
//
renderer.setSize(canvas.width, canvas.height);

const geometry = new THREE.BoxGeometry();
const material = new THREE.MeshBasicMaterial({ color: 0x00ff00 });
const cube = new THREE.Mesh(geometry, material);
scene.add(cube);

camera.position.z = 5;

// Анимация
function animate() {
    requestAnimationFrame(animate);
    cube.rotation.x += 0.01;
    cube.rotation.y += 0.01;
    renderer.render(scene, camera);
}
animate();

function render() {
    requestAnimationFrame(render);
    renderer.render(scene, camera);
}
render();

function saveAsGLTF() {
    var exporter = new THREE.GLTFExporter();
    exporter.parse(
        scene,
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
}