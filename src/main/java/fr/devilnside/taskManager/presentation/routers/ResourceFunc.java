package fr.devilnside.taskManager.presentation.routers;

import javax.ws.rs.core.Response;
import java.util.function.Function;

public class ResourceFunc {
    public static Function<Throwable, Response> throwableResponseFunction = throwable -> Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    public <E> Function<E, Response> getFuncRes(Response.Status status) {
        return result -> Response.status(status.getStatusCode()).entity(result).build();
    }
}
