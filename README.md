# Random Films

[![Build Status](https://travis-ci.org/acp13udl/randomfilms.svg?branch=master)](https://travis-ci.org/acp13udl/randomfilms)

This is random films application providing a RESTFul JSON API also available from an HTML user interface.
...
To run locally, first build WAR package:

`mvn package`

Then run embedded Tomcat server:

`mvn exec:exec`

The application will be available at http://localhost:8080/

Deployment in Heroku : http://randomfilms.herokuapp.com/ (Spring server) http://randomfilms.herokuapp.com/app (AngularJS client)

Authors: Alejandro Allué, Adrián Carrera y Lluis Echeverria.
