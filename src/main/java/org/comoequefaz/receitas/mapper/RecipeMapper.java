package org.comoequefaz.receitas.mapper;

import org.comoequefaz.receitas.dto.request.RecipeCreateRequest;
import org.comoequefaz.receitas.dto.request.RecipeUpdateRequest;
import org.comoequefaz.receitas.dto.response.RecipeResponse;
import org.comoequefaz.receitas.entity.Recipe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface RecipeMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Recipe toEntity(RecipeCreateRequest request);

    RecipeResponse toResponse(Recipe recipe);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    void updateEntityFromRequest(RecipeUpdateRequest request,
                      @MappingTarget Recipe recipe
    );


}
