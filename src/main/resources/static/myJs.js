function fetchBytes() {
    fetch("http://localhost:8080/").then(response => {
        var reader = response.body.getReader();
        var bytesReceived = 0;

        return reader.read().then(function processResult(result) {
            if (result.done) {
                console.log("Fetch complete");
                return;
            }

            playByteArray(result.value)
            bytesReceived += result.value.length;
            console.log('Received', bytesReceived, 'bytes of data so far');

            return reader.read().then(processResult);
        });
    });
}


window.onload = init;
var context;    // Audio context
var buf;        // Audio buffer

function init() {
if (!window.AudioContext) {
    if (!window.webkitAudioContext) {
        alert("Your browser does not support any AudioContext and cannot play back this audio.");
        return;
    }
        window.AudioContext = window.webkitAudioContext;
    }

    context = new AudioContext();
}

function playByteArray(byteArray) {

    var arrayBuffer = new ArrayBuffer(byteArray.length);
    var bufferView = new Uint8Array(arrayBuffer);
    for (i = 0; i < byteArray.length; i++) {
      bufferView[i] = byteArray[i];
    }

    context.decodeAudioData(arrayBuffer, function(buffer) {
        buf = buffer;
        volume = 0.3;
        play();
    });
}

// Play the loaded file
function play() {
    // Create a source node from the buffer
    var source = context.createBufferSource();
    source.buffer = buf;
    // Connect to the final output node (the speakers)
    source.connect(context.destination);
    // Play immediately
    source.start(0);
}