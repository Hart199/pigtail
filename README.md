![Braid](https://gitlab.com/bluebank/braid/raw/master/art/logo-small.png)

# Pigtail

A simple Braid service and JS client example.

MORE DETAILS REQUIRED.

# Pre-requisites:
  
See https://docs.corda.net/getting-set-up.html.

# Usage

## Running the node:

See https://docs.corda.net/tutorial-cordapp.html#running-the-example-cordapp.

## Running the client:

* Install `npm` and `Node.js` by following the instructions here: 
  https://www.npmjs.com/get-npm
* Open a command-line terminal window
* Move into the `client-js` folder
* Install the dependencies using `npm install`
* Run the client using `node client.js`

## Interacting with the node:

The client exposes three endpoints:

* `localhost:3000/whoami-flow-callback`, which uses Braid to call the `WhoAmI`
  flow on the node, and handles the response using callbacks
* `localhost:3000/whoami-flow-promise`, which uses Braid to call the `WhoAmI`
  flow on the node, and handles the response using promises
* `localhost:3000/whoami-service`, which uses Braid to call the `BraidService` 
  on the node, and handles the response using callbacks