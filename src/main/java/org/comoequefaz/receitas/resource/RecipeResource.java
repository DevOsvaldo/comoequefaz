package org.comoequefaz.receitas.resource;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.comoequefaz.receitas.dto.request.RecipeCreateRequest;
import org.comoequefaz.receitas.dto.request.RecipeUpdateRequest;
import org.comoequefaz.receitas.dto.response.RecipeResponse;
import org.comoequefaz.receitas.service.RecipeService;

import java.net.URI;
import java.util.List;

@Path("/recipes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RecipeResource {
    private final RecipeService recipeService;

    public RecipeResource(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @POST
    public Response create(@Valid RecipeCreateRequest request){
        RecipeResponse recipeResponse = recipeService.create(request);

        return Response
                .created(URI.create("/recipes/".concat(String.valueOf(recipeResponse.id()))))
                .entity(recipeResponse)
                .build();
    }

    @GET
    public Response listAll(@QueryParam("title") String title){
        List<RecipeResponse> recipes;

        if(title == null || title.isBlank()){
            recipes = recipeService.listAll();
        } else {
            recipes = recipeService.searchByTitle(title);
        }
        return Response.ok(recipes).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id){
        RecipeResponse response = recipeService.findById(id);

        return Response.ok(response).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(
            @PathParam("id") Long id,
            @Valid RecipeUpdateRequest request
    ){
        RecipeResponse recipeResponse = recipeService.update(id, request);
        return Response.ok(recipeResponse).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        recipeService.delete(id);
        return Response.noContent().build();
    }
}
