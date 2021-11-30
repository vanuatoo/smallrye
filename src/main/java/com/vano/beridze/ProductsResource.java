package com.vano.beridze;

import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

@Path("/products")
public class ProductsResource {

    @Inject
    ProductService service;

    @GET
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @Path("{id}")
    @GET
    public Product getProduct(@PathParam("id") @Min(1) Long productId) {
        return service.getProduct(productId);
    }

    @POST
    public Response createProduct(@NotNull @Valid Product product) {
        service.createProduct(product);
        return Response.status(Response.Status.CREATED).build();
    }

    @Path("{id}")
    @PUT
    public void updateProduct(@PathParam("id") @Min(1) Long productId,
            @NotNull @Valid Product product) {
        service.updateProduct(productId, product);
    }

    @Path("{id}")
    @DELETE
    public void deleteProduct(@PathParam("id") @Min(1) Long productId) {
        service.deleteProduct(productId);
    }
}
