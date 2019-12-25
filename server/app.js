const express = require('express');
const bodyParser = require('body-parser');
const logger = require('morgan');
const cors = require('cors');
const mongoose = require('mongoose');
require('dotenv').config();

const indexRouter = require('./routes/index');

const app = express();

mongoose
  .connect(process.env.DB_URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true
  })
  .then(db => {
    console.info('- Connected successfully to database');

    const corsOptions = {
      origin: '*',
      allowedHeaders: [
        'Content-Type',
        'Authorization',
        'Content-Length',
        'X-Requested-With',
        'Accept',
        'Origin'
      ],
      methods: ['GET', 'PUT', 'POST', 'DELETE', 'OPTIONS'],
      optionsSuccessStatus: 200 // some legacy browsers (IE11, various SmartTVs) choke on 204
    };

    app.use(cors(corsOptions));
    app.use(logger('dev'));
    app.use(bodyParser.json());
    app.use(bodyParser.urlencoded({ extended: false }));
    app.use(express.static('dist'));

    app.use('/', indexRouter);

    // catch 404 and forward to error handler
    app.use((req, res, next) => {
      next(createError(404));
    });

    // error handler
    app.use((err, req, res) => {
      // set locals, only providing error in development
      res.locals.message = err.message;
      res.locals.error = req.app.get('env') === 'development' ? err : {};

      // send the error
      res.status(err.status || 500);
      res.json(
        new Response(
          Strings.responseStatus.failure,
          'Error caught by Express error handler',
          err
        )
      );
    });
  })
  .catch(err => console.error('- Error while connecting to the database', err));

module.exports = app;
