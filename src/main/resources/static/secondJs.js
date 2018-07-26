function secondFetch() {
  var context = new (window.AudioContext || window.webkitAudioContext)();
  var audioStack = [];
  var nextTime = 0;

  fetch("http://localhost:8080").then(function(response) {
    var reader = response.body.getReader();
    function read() {
      return reader.read().then(({ value, done })=> {
        context.decodeAudioData(value.buffer, function(buffer) {
          audioStack.push(buffer);
          if (audioStack.length > 5) {
              scheduleBuffers();
          }
        }, function(err) {
          console.log("err(decodeAudioData): "+err);
        });
        if (done) {
          console.log('done');
          return;
        }
        read()
      });
    }
    read();
  })

  function scheduleBuffers() {
      console.log("scheduling")
      while (audioStack.length > 5) {
          var buffer    = audioStack.shift();
          var source    = context.createBufferSource();
          source.buffer = buffer;
          source.connect(context.destination);
          if (nextTime == 0)
              nextTime = context.currentTime +1;  /// add 50ms latency to work well across systems - tune this if you like
          source.start(nextTime);
          nextTime += source.buffer.duration; // Make the next buffer wait the length of the last buffer before being played
      };
  }
}