//function start() {
//    var xhr = new XMLHttpRequest();
//    xhr.onreadystatechange = function() {
//        if (xhr.readyState == XMLHttpRequest.DONE) {
//            alert("Downloaded full audio");
//            console.log(xhr.responseText);
//        }
//    }
//    xhr.open('GET', 'http://localhost:8080/', true);
//    xhr.send(null);
//}

try {
    // Create a instance of AudioContext interface
    window.AudioContext = window.AudioContext||window.webkitAudioContext||window.mozAudioContext;
    var context = new AudioContext();

    var source = context.createBufferSource(); //this represents the audio source. We need to now populate it with binary data.

    //now retrieve some binary audio data from <audio>, ajax, input file or microphone and put it into a audio source object.
    //here we will retrieve audio binary data via AJAX
    var request = new XMLHttpRequest();
    request.open('GET', 'localhost:8080/', true);
    request.responseType = 'arraybuffer'; //This asks the browser to populate the retrieved binary data in a array buffer
    request.onload = function(){
        //populate audio source from the retrieved binary data. This can be done using decodeAudioData function.
        //first parameter of decodeAudioData needs to be array buffer type. So from wherever you retrieve binary data make sure you get in form of array buffer type.
        context.decodeAudioData(request.response, function(buffer) {
            source.buffer = buffer;
        }, null);
    }
    request.send();

    //now we got context, audio source.
    //now lets connect the audio source to a destination(hardware to play sound).
    source.connect(context.destination);//destination property is reference the default audio device

    /*
        If we wanted to add any audio nodes then we need to add them in between audio source and destionation anytime dynamically.
    */

    //now play the sound.
    source.start(0);
}
catch(e)
{
    alert("Web Audio API not supported");
}