package org.acme.panache;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.common.annotation.Blocking;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class TodoResourceTest {

    @Test
    @Order(1)
    @Transactional
    @Blocking
    void testPostTodos() {
        final Todo todo = new Todo();
        todo.title = "tu1";
        todo.description = "desc1";
        todo.date = new Date();
        todo.tasks = Collections.singletonList("task1");
        todo.tags = Collections.singletonList("tag1");

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
    }

    @Test
    @Order(2)
    @Transactional
    @Blocking
    void testGetTodos() {
//        await().untilAsserted(() -> {
            given()
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .log().all()
            .when()
                .get("/todos")
            .then()
                .log().all()
                .statusCode(Response.Status.OK.getStatusCode())
                .body("[0].title", is("tu1"))
            ;
//        });
    }
}