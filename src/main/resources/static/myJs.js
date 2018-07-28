var audio = document.getElementById("audio");

function syncTime() {
        console.log("play")
        var oReq = new XMLHttpRequest();
        oReq.onload = function() {
            console.log("Current time: "+ oReq.response)
            audio.currentTime = oReq.response;
            audio.play();
        };
        oReq.open("get", "http://localhost:8080/start-get-time", true);
        oReq.send();
}