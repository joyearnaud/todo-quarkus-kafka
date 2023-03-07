package org.acme.panache;

import io.smallrye.common.annotation.Blocking;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/prices")
@OpenAPIDefinition(info = @Info(title = "todos API", version = "1.0"))
public class PriceResource {

    /**
     * We uses classic Hibernate, so the API is blocking, so we need to use @Blocking.
     * @return the list of prices
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Blocking
    @Operation(summary = "GET all prices",
            description = "Returns all prices.")
    public List<Price> getAllPrices() {
        return Price.listAll();
    }
}
