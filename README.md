## Introduction

Small project for REST APIs in Scala with Servlets.

Inspired by:

<https://parkergordon.io/2016/09/26/a-web-server-in-5-minutes-with-scala-jetty-sbt/>

## Development

Install `java` locally, using [SdkMan]:

```bash
sdk install java 11.0.3-zulu
```

Install `sbt` locally, using [SdkMan]:

```bash
sdk install sbt 1.2.8
```

Run the service, optionally specifying the port to listen on:

```bash
sbt "run 7102"
```

IntelliJ Configuration:

* Run > Edit Configurations
* Add > sbt Task
* Task Configuration
    * **Name:** `run`
    * **Tasks:** `"run 7102"`
    * **Uncheck:** Use sbt shell 

[SdkMan]: https://sdkman.io/install
