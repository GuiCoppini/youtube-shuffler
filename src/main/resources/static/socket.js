function startSocket() {
    var sock = new SockJS('http://localhost:8080/my-websocket-endpoint');
    sock.onopen = function() {
     console.log('open');
     sock.send('MENSAGEM PORRAN');
    };

    sock.onmessage = function(e) {
     console.log('message', e.data);
     sock.close();
    };

    sock.onclose = function() {
     console.log('close');
    };
}