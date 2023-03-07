package org.acme.panache;

import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Path("/todos")
@OpenAPIDefinition(info = @Info(title = "todos API", version = "1.0"))
public class TodoResource {

    private final Logger logger = Logger.getLogger(TodoConsumer.class);
    @Inject
    TodoService service;
    @Inject
    TodoStorage storage;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    @Transactional
    @Operation(summary = "POST a todo",
            description = "Create a single todo based on JSON body")
    public CompletionStage<Response> send(@RequestBody @Valid Todo todo) {

        return service.create(todo)
                .thenApply(result -> Response.status(Response.Status.CREATED.getStatusCode()).build())
                .exceptionally(throwable -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).build())
                ;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    @Operation(summary = "GET all todos",
            description = "Returns all todos.")
    public List<Todo> getAllTodos() {
        return storage.findAllTodos();
    }
}
