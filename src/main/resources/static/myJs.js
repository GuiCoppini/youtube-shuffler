var audio = document.getElementById("audio");

function syncTime() {
    $.get("http://localhost:8080/start-get-time", function(data, status){
        console.log("Current time: "+ data.current_time)
        console.log("Current song: "+ data.song_name)
        audio.currentTime = data.current_time;
        audio.volume = 0.3;
        audio.play();
    });
}

function setSongFromInput() {
    var songName = $('#song-name').val()
    $.ajax({
        url: "http://localhost:8080/set-song",
        type: "POST",
        data: {song_name: songName},
        contentType: "application/json",
        dataType: "json",
        success: function(response, status) {
                         alert(status)
                         if(status == "success") {
                             audio.src="http://localhost:8080/get-song";
                         }
                 }
    })
}

//src="http://localhost:8080/get-song"