/**
 * Experimentation & Evaluation - Project 2
 *
 * Model (implemented with MongoDB)
 *
 */
const mongodb = require('mongodb');

/* Optional Task */

const local_mongodb_uri = 'mongodb://127.0.0.1:27017/';
const cloud_mongodb_uri = 'mongodb+srv://db_user:mF14Cac1Jfvg2wZz@cluster0.x6zd3.mongodb.net/Feedback?retryWrites=true&w=majority';

// const default_mongodb_uri = cloud_mongodb_uri;
const default_mongodb_uri = local_mongodb_uri;

const clients = {};
const connections = {};

let model = {};

let connect = function(db_name, mongodb_uri) {

    // Sanitizing the provided data
    db_name = db_name ?? 'EE-project2';
    mongodb_uri = mongodb_uri ?? default_mongodb_uri;

    // Extracting the client from the list of clients
    let client = clients[mongodb_uri];

    // Case in which the client is a new client
    if (client === undefined) {
        // Creating a new MongoDB instance
        client = new mongodb.MongoClient(mongodb_uri);
        // Adding the instance to the list
        clients[mongodb_uri] = client;

        // Printing information
        console.log("Connecting to mongodb server");

        // Creating the connection
        connections[mongodb_uri] = client.connect().then(client => {
            console.log("Connected to mongodb server");

            // TODO: Export each table function, see example:
            // let map_list = require("./map-list-db")(client, db_name, "maps");
            // model.map_list = map_list;

            return {
                client,
                marker_list,
                map_list,
                maps: client.db(db_name).collection("maps"),
                markers: client.db(db_name).collection("markers")
            }
        }).catch(err => console.error(err));
    }

    // Returning the connection to the DB (will be used by the routes)
    return connections[mongodb_uri];

}

// Exporting the connection the DB and the model
module.exports = {
    connect,
    model
};