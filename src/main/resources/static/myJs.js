var audio = document.getElementById("audio");
var isPlaying = false;
//var context = new (window.AudioContext || window.webkitAudioContext)();

function getSong() {
    var oReq = new XMLHttpRequest();
    oReq.onload = function() {
        console.log("Source set as "+ oReq.response)
        audio.setAttribute("src", oReq.response);
    };
    oReq.open("get", "http://localhost:8080/get-song", true);
    oReq.send();
}

function syncTime() {
        var oReq = new XMLHttpRequest();
        oReq.onload = function() {
            console.log("Current time: "+ oReq.response)
            audio.currentTime = oReq.response;
            audio.play();
        };
        oReq.open("get", "http://localhost:8080/start-get-time", true);
        oReq.send();
}