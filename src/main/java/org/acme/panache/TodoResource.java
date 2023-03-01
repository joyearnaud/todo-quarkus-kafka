package org.acme.panache;

import io.smallrye.common.annotation.Blocking;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

@Path("/todos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final Logger logger = Logger.getLogger(TodoConsumer.class);

    @Inject
    Validator validator;
    @Inject
    TodoService service;

    @POST
    @Blocking
    public CompletionStage<Response> send(Todo todo) {
        Set<ConstraintViolation<Todo>> validate = validator.validate(todo);

        return service.create(todo)
                .thenApply(result -> {
                    if(validate.isEmpty()) {
                        return Response.status(Response.Status.ACCEPTED.getStatusCode()).build();
                    } else {
                        return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), validate.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"))).build();
                    }
                })
                .exceptionally(throwable -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).build());
    }

    @GET
    @Blocking
    public List<Todo> getAllTodos() {
        return Todo.listAll();
    }
}
