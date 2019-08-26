<!-- Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root. -->
# Vespa sample applications - Basic stateless Vespa application

Extends the [basic-search](../basic-search) sample application with a Searcher component in Java
which does query and result processing.

Please refer to
[developing searchers](http://docs.vespa.ai/documentation/searcher-development.html)
for more information.


## Getting started
Prerequisites: git, Java 11, mvn 3.6.1

1. Go to https://console.vespa-external.aws.oath.cloud/

2. Click "Create new tenant", then go to this tenant and click "Create application"

3. Download this sample app:
 ```sh
 $ git clone git@github.com:vespa-cloud/sample-apps.git && cd sample-apps/basic-search-java
 ```
 
4. Edit the properties `tenant` and `application` in `pom.xml` —
use values from the console (what was used to create the application)

5. Build the java sample app:
 ```sh
 $ mvn clean package
 ```

6. In the "Deploy to dev" console section, upload _target/application.zip_ - click "Deploy"

7. Click "deployment log" to track the deployment. "Installation succeeded!" in the bottom pane indicates success 

8. Click "Instances" at the top, then "endpoints". Click the endpoint to validate it is up. _Temporary workaround: use http (not https) and port 443) - example http://end.point.name:443_.

9. Feed documents
```sh
$ curl -H "Content-Type:application/json" --data-binary  @music-data-feed.json http://endpoint:443/document/v1/music/music/docid/1
$ curl -H "Content-Type:application/json" --data-binary  @music-data-update.json http://endpoint:443/document/v1/music/music/docid/2
```

11. Visit documents
```sh
$ curl http://endpoint:443/document/v1/music/music/docid?wantedDocumentCount=100
```

12. Search documents
```sh
$ curl http://endpoint:443/search/?query=bad
```
