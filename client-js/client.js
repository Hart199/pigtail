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

app.get('/whoami-flow-callback', (req, res) => {
    corda.flows.whoAmIFlow(
        result => res.send("Hey, you're speaking to " + result + "!"),
        err => res.status(500).send(err));
});

app.get('/whoami-flow-promise', (req, res) => {
    corda.flows.whoAmIFlow()
    .then(result => res.send("Hey, you're speaking to " + result + "!"))
    .catch(err => res.status(500).send(err));    
});

app.get('/whoami-service', (req, res) => {
    corda.myService.whoAmI(
        result => res.send("Hey, you're speaking to " + result + "!"),
        err => res.status(500).send(err));
});

app.listen(3000, () => console.log('Server listening on port 3000!'))