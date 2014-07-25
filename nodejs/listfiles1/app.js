var hbs     = require('express-hbs');
var express = require('express');
var fs      = require('fs');
var url     = require('url') ;

// Mongo db configuration
var mongo = require('mongodb');
var monk = require('monk');
var db = monk('localhost:27017/resume');

var recursion = require('./recursion.js');
var resume = require('./resume.js');

var app     = express();


app.set('view engine', 'hbs');
app.set('views', __dirname + '/views');
app.engine('hbs', hbs.express3({
  partialsDir: __dirname + '/views/partials'
}));


// Make our db accessible to our resume
app.use(function(req,res,next){
    req.db = db;
    next();
});


var colors = {};

hbs.registerHelper('color', function() {

  return 'the ' + this + ' is ' + colors[this];
});

app.get('/', function(req, res){

  colors = {
    turtle  : 'green',
    giraffe : 'yellow',
    flamingo: 'pink'
  };
  
  var data = {animals:['flamingo','turtle','giraffe']}
    console.log("Hello "+data)
  res.render('index', data);
});


app.get('/listDir', function(req, res){
  var path="/Somnath/nodejs-ws/";
  recursion.listFiles(path, req, res, dirCallback,fileCallback);
  
  function dirCallback(results, res){
  var data = {files:results,parentDir:path};
  res.render('files',data);
  }

  function fileCallback(response, res){
   var data = {content:response};
   res.render('files',data); 
  }

});

app.get('/getDetails.js', function(req, res){
   var queryObject = url.parse(req.url,true).query;
   console.log(queryObject);
   var path = queryObject.path;
   console.log(path);
  recursion.listFiles(path, req, res, dirCallback,fileCallback);
  
  function dirCallback(results, res){
  var data = {files:results,parentDir:path+'/'};
  res.render('files',data);
  }

  function fileCallback(response, res){
   var data = {content:response};
   res.render('files',data); 
  }   
});

module.exports = app;