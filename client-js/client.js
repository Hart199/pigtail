"use strict";

const express = require('express')
const Proxy = require('braid-client').Proxy;

let corda = new Proxy({
  url: "http://localhost:8080/api/"
}, onOpen, onClose, onError, { strictSSL: false });

function onOpen() { console.log('Connected to node.'); }
function onClose() { console.log('Disconnected from node.'); }
function onError(err) { console.error(err); process.exit(); }

const app = express()

app.get('/get-transaction-flow-callback', (req, res) => {
    corda.flows.echoFlow(
        'hello, lovely callback', 
        result => res.send(result),
        err => res.status(500).send(err));
});

app.get('/get-transaction-flow-promise', (req, res) => {
    corda.flows.echoFlow('hello, lovely promise')
    .then(result => res.send(result))
    .catch(err => res.status(500).send(err));    
});

app.get('/get-transaction-service', (req, res) => {
    corda.myService.getTransaction("hello", result => res.send(result));
});

app.listen(3000, () => console.log('Example app listening on port 3000!'))