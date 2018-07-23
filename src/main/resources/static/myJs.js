function start() {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            alert("Downloaded full audio");
            console.log(xhr.responseText);
        }
    }
    xhr.open('GET', 'http://localhost:8080/', true);
    xhr.send(null);
}