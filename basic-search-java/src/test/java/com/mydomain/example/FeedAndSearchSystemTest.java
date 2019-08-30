package com.mydomain.example;

import ai.vespa.hosted.cd.Endpoint;
import ai.vespa.hosted.cd.SystemTest;
import ai.vespa.hosted.cd.TestRuntime;
import com.yahoo.slime.JsonDecoder;
import com.yahoo.slime.Slime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SystemTest
@DisplayName("Test that the deployment")
class FeedAndSearchSystemTest {

    private final Endpoint endpoint = TestRuntime.get().deploymentToTest().endpoint("default");

    @Test
    @DisplayName("makes fed documents searchable")
    void feedAndSearch() {
        var documentPath = "/document/v1/my-music/docid/Got to be there";
        var document = "{\n"
                     + "    \"fields\": {\n"
                     + "         \"album\": \"Got to be there\"\n"
                     + "    }\n"
                     + "}\n";

        var yql = "SELECT * FROM SOURCES * WHERE album CONTAINS \"Got to be there\";";

        var deleteResult = endpoint.send(endpoint.request(documentPath).DELETE());
        assertEquals(200, deleteResult.statusCode());

        var emptyResult = endpoint.send(endpoint.request("/search",
                                                         Map.of("yql", yql)));
        var emptyInspector = new JsonDecoder().decode(new Slime(), emptyResult.body().getBytes(UTF_8)).get();
        assertEquals(200, emptyResult.statusCode());
        assertTrue(emptyInspector.field("root").field("fields").field("totalCount").valid());
        assertEquals(0, emptyInspector.field("root").field("fields").field("totalCount").asLong());

        var feedResult = endpoint.send(endpoint.request(documentPath)
                                               .POST(ofString(document)));
        assertEquals(200, feedResult.statusCode());

        var searchResult = endpoint.send(endpoint.request("/search",
                                                          Map.of("yql", yql)));
        var searchInspector = new JsonDecoder().decode(new Slime(), searchResult.body().getBytes(UTF_8)).get();
        assertEquals(200, searchResult.statusCode());
        assertTrue(searchInspector.field("root").field("fields").field("totalCount").valid());
        assertEquals(1, searchInspector.field("root").field("fields").field("totalCount").asLong());
    }

}
