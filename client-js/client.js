"use strict";

const express = require('express')
const Proxy = require('braid-client').Proxy;


let corda = new Proxy({
  url: "http://localhost:8080/api/"
}, onOpen, onClose, onError, {strictSSL: false});

const app = express()
app.get('/', (req, res) => {
    corda.myService.echo("hello", result => res.send(result));
});

app.get('/echoflow-callback', (req, res) => {
    corda.flows.echoFlow('hello, lovely callback', result => res.send(result));
});

app.get('/echoflow-promise', (req, res) => {
    corda.flows.echoFlow('hello, lovely promise')
    .then(result => res.send(result))
    .catch(err => res.status(500).send(err));    
});

app.listen(3000, () => console.log('Example app listening on port 3000!'))

function onOpen() {  
    console.log('corda connected');
}

function onClose() {
    console.log('disconnected - write something here to reconnect');
}

function onError(err) {
    console.error(err);
    process.exit();
}