<!-- Copyright 2017 Yahoo Holdings. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root. -->
# Vespa sample applications - Basic stateless Vespa application

Extends the [basic-search](../basic-search) sample application with a Searcher component in Java
which does query and result processing.

Please refer to
[developing searchers](http://docs.vespa.ai/documentation/searcher-development.html)
for more information.


## Getting started
Prerequisites: git, Java 11, mvn 3.6.1

1.  Go to https://console.vespa-external.aws.oath.cloud/

1.  Click "Create new tenant", then go to this tenant and click "Create application"

1.  Download this sample app:
     ```sh
     $ git clone git@github.com:vespa-cloud/sample-apps.git && cd sample-apps/basic-search-java
     ```
 
1.  Edit the properties `tenant` and `application` in `pom.xml` — use the values you entered in the console in 2. 

1.  Build the java sample app:
     ```sh
     $ mvn clean package
     ```
 
1.  Deploy with a key pair:
    1. In the console, click "Deploy", and generate a deploy key (at the bottom); the key is downloaded to
       `$HOME/Downloads/tenant.application.instance.pem`.
    1. Set the `privateKeyFile` property in `pom.xml` to the absolute path of the key.
    1. Deploy with
       ```sh
       $ mvn vespa:deploy
       ```
    1. The endpoint URLs are printed when the deployment is successful.

1.  Alternatively, deploy through the console:
    1. In the "Deploy to dev" console section, upload _target/application.zip_, then click "Deploy".
    1. Click "deployment log" to track the deployment. "Installation succeeded!" in the bottom pane indicates success.
    1. Click "Zones" at the top, then "endpoints", to find the endpoint URLs.

1.  Open an endpoint in a browser to validate it is up.
    __Temporary workaround: change to http (not https) and port 443, e.g., http://end.point:443.__

1.  Feed documents
    ```sh
    $ curl -H "Content-Type:application/json" --data-binary  @music-data-feed.json http://endpoint:443/document/v1/music/music/docid/1
    $ curl -H "Content-Type:application/json" --data-binary  @music-data-update.json http://endpoint:443/document/v1/music/music/docid/1
    ```

1.  Visit documents
    ```sh
    $ curl http://endpoint:443/document/v1/music/music/docid?wantedDocumentCount=100
    ```
1.  Search documents
    ```sh
    $ curl http://endpoint:443/search/?query=bad
    ```

1.  If you downloaded the deploy key, run the included integration test _ExampleSystemTest_ with
    ```sh
    $ mvn test -Dtest.categories=system
    ```
    or run it directly from your IDEA.

