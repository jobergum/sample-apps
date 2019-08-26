<!-- Copyright 2019 Oath Inc. Licensed under the terms of the Apache 2.0 license. See LICENSE in the project root. -->
# Vespa sample applications - Basic Search

## Getting started
Prerequisites: git

1. Go to https://console.vespa-external.aws.oath.cloud/

2. Click "Create new tenant", then go to this tenant and click "Create application"

3. Download sample app:
```sh
$ git clone git@github.com:vespa-engine/sample-apps.git && cd sample-apps/basic-search
```

4. Edit properties _tenant_, _application_ and _instance_ in _pom.xml_ -
use values from the console (what was used to create the application) - use "default" as instance name

5. something here on how to zip

6. In the "Deploy to dev" console section, upload _application.zip_ - click Deploy

7. Click "deployment log" to track the deployment. "Installation succeeded!" in the bottom pane indicates success 

8. Click "Instances" at the top, then "endpoints". Click the endpoint to validate it is up. _Temporary workaround: use http (not https) and port 443) - example http://end.point.name:443_.
One can also use:
```sh
$ mvn -DprivateKeyFile=$HOME/Downloads/mytenantname.myappname.myinstancename.pem vespa:endpoints # test this!
```

9. Feed documents
```sh
$ curl -H "Content-Type:application/json" --data-binary  @music-data-1.json http://endpoint:443/document/v1/music/music/docid/1
$ curl -H "Content-Type:application/json" --data-binary  @music-data-2.json http://endpoint:443/document/v1/music/music/docid/2
```

10. Visit documents
```sh
$ curl http://endpoint:443/document/v1/music/music/docid?wantedDocumentCount=100
```

11. Search documents
```sh
$ curl http://endpoint:443/search/?query=bad
```
