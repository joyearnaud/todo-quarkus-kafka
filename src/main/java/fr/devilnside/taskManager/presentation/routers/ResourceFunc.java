package fr.devilnside.taskManager.presentation.routers;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.core.Response;
import java.util.function.Function;

@OpenAPIDefinition(info = @Info(title = "todos API", version = "1.0"))
public class ResourceFunc {
    public static Function<Throwable, Response> throwableResponseFunction = throwable -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    public <E> Function<E, Response> getFuncRes(Response.Status status) {
        return result -> Response.status(status.getStatusCode()).entity(result).build();
    }
}
