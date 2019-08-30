<!-- Copyright 2019 Oath Inc. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root. -->
# Vespa sample applications - Basic Search

## Getting started
Prerequisites: git

1. Go to https://console.vespa-external.aws.oath.cloud/

2. Click "Create new tenant", then go to this tenant and click "Create application"

3. Download sample app:
```sh
$ git clone git@github.com:vespa-cloud/sample-apps.git && cd sample-apps/basic-search
```

4. Create the application package
```sh
$ cd src/main/application && zip -r ../../../application.zip . && cd ../../..
```

5. Click Deploy. In the "Deploy to dev" console section, upload _application.zip_ - click Deploy

6. Click "deployment log" to track the deployment. "Installation succeeded!" in the bottom pane indicates success 

7. Click "Instances" at the top, then "endpoints". Click the endpoint to validate it is up. _Temporary workaround: use http (not https) and port 443) - example http://end.point.name:443_.

8. Feed documents
```sh
$ curl -H "Content-Type:application/json" --data-binary  @music-data-1.json http://endpoint:443/document/v1/music/music/docid/1
$ curl -H "Content-Type:application/json" --data-binary  @music-data-2.json http://endpoint:443/document/v1/music/music/docid/2
```

9. Visit documents
```sh
$ curl http://endpoint:443/document/v1/music/music/docid?wantedDocumentCount=100
```

10. Search documents
```sh
$ curl http://endpoint:443/search/?query=bad
```
