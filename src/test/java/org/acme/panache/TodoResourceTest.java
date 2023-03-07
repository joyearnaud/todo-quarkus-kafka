package org.acme.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.reactive.messaging.providers.connectors.InMemoryConnector;
import io.smallrye.reactive.messaging.providers.connectors.InMemorySink;
import io.smallrye.reactive.messaging.providers.connectors.InMemorySource;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TodoResourceTest {
    // 1. Switch the channels to the in-memory connector:
    @BeforeAll
    public static void switchMyChannels() {
        InMemoryConnector.switchIncomingChannelsToInMemory("todos-in");
        InMemoryConnector.switchOutgoingChannelsToInMemory("todos-out");
    }

    // 2. Don't forget to reset the channel after the tests:
    @AfterAll
    public static void revertMyChannels() {
        InMemoryConnector.clear();
    }

    // 3. Inject the in-memory connector in your test,
    // or use the bean manager to retrieve the instance
    @Inject
    @Any
    InMemoryConnector connector;

    @Test
    @Order(1)
    @Transactional
    void testPostTodos() {
        final Task task = new Task();
        task.name = "ta1";
        final Todo todo = new Todo();
        todo.title = "tu1";
        todo.description = "desc1";
        todo.date = new Date();
        todo.tasks = Collections.singletonList(task);
        todo.tags = Collections.singletonList("tag1");

        // 4. Send messages from ressource:
        given()
            .body(todo)
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
            .log().all()
        .when()
            .body(todo)
            .post("/todos")
        .then()
            .log().all()
            .statusCode(Response.Status.ACCEPTED.getStatusCode())
        ;

//        // 5. Retrieves the in-memory source to send message
//        InMemorySource<Todo> todos = connector.source("todos-in");
        // 6. Retrieves the in-memory sink to check what is received
        InMemorySink<Todo> results = connector.sink("todos-out");

        List<? extends Message<Todo>> received = results.received();
        System.out.println("Received messages: " + received);

        // 7. Check you have receives the expected messages
        Assertions.assertEquals(1, received.size());
        Assertions.assertEquals(received.get(0).getPayload().title, "tu1");
    }

    @Test
    @Order(2)
    @Transactional
    void testGetTodos() {
        final Task task = new Task();
        task.name = "ta1";
        final Todo todo = new Todo();
        todo.title = "tu2";
        todo.description = "desc1";
        todo.date = new Date();
        todo.tasks = Collections.singletonList(task);
        todo.tags = Collections.singletonList("tag1");

        InMemorySource<Todo> todos = connector.source("todos-in");
        todos.send(todo);

        given()
            .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
            .log().all()
        .when()
            .get("/todos")
        .then()
            .log().all()
            .statusCode(Response.Status.OK.getStatusCode())
            .body("[0].title", is("tu2"))
        ;
    }

    @Test
    @Order(3)
    @Transactional
    public void shouldFindAllPersistedTodos() {
        PanacheQuery<PanacheEntityBase> allTodos = Todo.findAll();

        assertThat(allTodos.stream().count(), Matchers.greaterThanOrEqualTo(1L));
    }


}