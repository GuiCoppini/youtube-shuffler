function start() {
    alert("RODANDO");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            alert("ae")
            console.log(xhr.responseText);
        }
    }
    xhr.open('GET', 'http://localhost:8080/', true);
    xhr.send(null);
}