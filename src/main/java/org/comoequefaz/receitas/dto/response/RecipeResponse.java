package org.comoequefaz.receitas.dto.response;

import java.time.LocalDateTime;

public record RecipeResponse(
    Long id,
    String title,
    String description,
    String ingredients,
    String instructions,
    Integer preparationTimeMinutes,
    Integer servings,
    Boolean favorite,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
