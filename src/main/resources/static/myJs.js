window.AudioContext = window.AudioContext || window.webkitAudioContext;
var context = new AudioContext();

function firstFetch() {
    fetch("http://localhost:8080/").then(response => {

      var reader = response.body.getReader();
      var bytesReceived = 0;

      return reader.read().then(function processResult(result) {
        if (result.done) {
          console.log("Fetch complete");
          return;
        }
        context.decodeAudioData(result.value.buffer, function(buffer) {
              var source = context.createBufferSource();
              source.buffer = buffer;
              source.connect(context.destination);
              source.start(0);
        });

        bytesReceived += result.value.length;
        console.log('Received', bytesReceived, 'bytes of data so far');

        return reader.read().then(processResult);
      });
    });
}