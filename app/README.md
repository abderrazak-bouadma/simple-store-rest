# How to build

## Pre-requisites

Ensure you have the followin setup and configured on your system (this works on all OS)

* JDK 17 
* MAVEN

Download this repository and enter the ```/app``` folder, then run the following command
```bash
mvn clean package 
```

if every thing worked well you should endup with a ```.jar``` file located in the ```/target``` folder

## Run the Jar locally
just execute the following command (assuming you still in the ```/app``` folder)
```bash
java -jar -DABSOLUTE_WORK_DIR="/home/zak/data" target/demo-0.0.1-SNAPSHOT.jar
```

Replace ```ABSOLUTE_WORK_DIR``` with your working dir, if you're in Windows the folder name should look like ```-DABSOLUTE_WORK_DIR="C:\\data"```
the folder needs to exist or the application will fail

## Stopping the application 
just use [CTRL][C]

## do a test 
under linux you can use the ```curl``` command as follow
```bash
curl -v -X POST localhost:8080/api/store -H 'Content-Type: application/json' -d '{"field1":"123", "field2":"Hello World", "field3":"!"}'
```

if you don't use ```curl``` you can use a tool like postman

you should find a file with this kind of naming in the workind dir you designated ```1698014360832.json```

## Build the docker image
```bash
docker build -t your-image-name .
```
## run from the docker version
a docker version of the application is published on hub.docker.com, to use you need to have the Docker agent installed on your machine
to run the docker container on your machine just do the following 

```bash
docker run -d --mount source=wrk,target=/home/zak/data -e ABSOLUTE_WORK_DIR=/your/custom/directory -p 8080:8080 your-image-name
```