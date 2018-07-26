function fetchBytes() {
    fetch("http://localhost:8080/").then(response => {
        var reader = response.body.getReader();
        var bytesReceived = 0;

        // read() returns a promise that resolves
        // when a value has been received
        return reader.read().then(function processResult(result) {
            if (result.done) {
                console.log("Fetch complete");
                return;
            }

            playByteArray(result.value)
            bytesReceived += result.value.length;
            console.log('Received', bytesReceived, 'bytes of data so far');

            // Read some more, and call this function again
            return reader.read().then(processResult);
        });
    });
}

function playByteArray(byteArray) {

    var arrayBuffer = new ArrayBuffer(byteArray.length);
    var bufferView = new Uint8Array(arrayBuffer);
    for (i = 0; i < byteArray.length; i++) {
      bufferView[i] = byteArray[i];
    }

    context.decodeAudioData(arrayBuffer, function(buffer) {
        buf = buffer;
        play();
    });
}
