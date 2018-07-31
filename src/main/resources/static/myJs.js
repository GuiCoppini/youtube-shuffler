var audio = document.getElementById("audio");

var initialPlay = true;
audio.oncanplaythrough = function() {
    if(initialPlay) {
        initialPlay = false;
        console.log("pode tocar hein")
        syncTime();
    }
}

function syncTime() {
        console.log("play")
        var oReq = new XMLHttpRequest();
        oReq.onload = function() {
            console.log("Current time: "+ oReq.response)
            audio.currentTime = oReq.response;
            audio.volume = 0.3;
            audio.play();
        };
        oReq.open("get", "http://localhost:8080/start-get-time", true);
        oReq.send();
}