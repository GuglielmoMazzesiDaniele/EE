/**
 * Experimentation & Evaluation - Project 2
 *
 * Main Server Application
 *
 */

// Import Node.js modules for the express framework
const express = require('express');

/**
 * Initializes the Express application
 */
function initApp() {
    // Initializing the express application
    let app = express();

    // Connecting to the MongoDB database.
    // The connect method is provided by the model.
    require("./model").connect();

    return app;
}

/**
 * Initializes the Express's application with the default settings
 */
function initMiddleware(app) {
    // Log HTTP request and responses on the console
    app.use(logger('dev'));

    // Parse application/x-www-form-urlencoded
    // Used to parse the HTML form values placed in URL into a JS object.
    app.use(express.urlencoded( {
        extended: false
    }));

    // Parse application/json
    app.use(express.json({limit: '4MB'}));

    // Parse multipart/form-data but do not allow any file upload
    app.use("/map",multer().none());

    // Override the Express default
    let staticOptions = {
        "setHeaders" : function (res, path, stat) {
            if(path.includes(".js")){
                res.set("Content-type", "text/javascript")
            }
        }
    };

    // Setting the location of the public static folder
    app.use(express.static(path.join(__dirname, "public"), staticOptions));

    return app;
}

/**
 * Initializes the middleware controllers used by the application.
 * Initializes the default faulty request and server error handlers.
 */
function initControllers(app) {
    // Load all the controllers from the routes folder
    const routers = require("./routes");

    // TODO: Add controllers, see example: app.use("/map",routers["map"]);

    // Default faulty request handler -  Respond with 404
    app.use(function(req, res, next) {
        const err = new Error('Not Found');
        err.status = 404;
        next(err)
    });

    // Default server error handler - Respond with 500
    app.use(function(err, req, res, next) {
        res.status(err.status || 500);
        res.json({
            message: err.message,
            error: err
        })
    });

    return app;
}

/**
 * Initializes the HTTP server listening on the default port 8989
 */
function initServer(app) {

    // Setting the port of the webserver
    app.set("port", process.env.PORT || 8989);

    // Initialize the webserver
    let server = app.listen(app.get("port"));

    // Turning on the webserver, printing debug function
    server.on('listening', function() {
        console.log('Express server listening on http://localhost:' + server.address().port);
    });

    return server;
}