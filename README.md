# Sample Jet Job with Dependencies

## Build

Requirements:

1. Java 11 or better.

```
$ make
```

That will create the `./build/libs/sample-job-1-1.0-SNAPSHOT-all.jar` file.

## Submitting the Job

Using [CLC](https://github.com/hazelcast/hazelcast-commandline-client):

```
$ clc -c CONFIG_NAME job submit --name j1 ./build/libs/sample-job-1-1.0-SNAPSHOT-all.jar MY-MAP MY-SALT
```

Hashids will be creted with salt `MY-SALT` and the output is saved to map `MY-MAP`.


