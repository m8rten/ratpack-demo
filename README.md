# Ratpack and Docker demo
Creating a high performance micro service writing a short groovy script. The service is packaged in a Docker container.

## Prerequisite
Install docker and groovy
To post data, for example use curl from command line or postman from chrome.

## To run on you local machine
groovy ratpack-demo.groovy

## To build and run Docker container
docker build --tag marten/ratpack-demo:v1 .
docker run -d -p 5050:5050 marten/ratpack-demo:v1

## Serivce API

Entry point
```
http://localhost:5050/
```
Date for service
```
http://localhost:5050/date
```
The processes run on machine
```
http://localhost:5050/ps-ef
```
Returns the public/file.txt file
```
http://localhost:5050/file.txt
```
To post data to service
```
curl -XPOST -H "Content-Type: application/json" -d '{"company":{"name":"cinnober","adress":["ume","stockholm"]}}' localhost:5050/save-to-file
```
To run arbitrary command on machine
```
curl -XPOST -H "Content-Type: application/json" -d '{"command":"ps -ef"}' localhost:5050/run-command
```
