# Sample Jet Job with Dependencies

## Build

Requirements:

1. Java 11

```
$ ./gradlew build
```

That will create the `./build/libs/sample-job-1-1.0-SNAPSHOT-all.jar` file.

## Submitting the Job

Using [CLC](https://github.com/hazelcast/hazelcast-commandline-client):

```
$ clc -c CONFIG_NAME job submit --class clc.example.JetJob --name j1 ./build/libs/sample-job-1-1.0-SNAPSHOT-all.jar URL1 URL2 ...
```

Titles of the HTML documents will be printed in the member logs.



