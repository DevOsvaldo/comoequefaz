package org.comoequefaz.receitas.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecipeCreateRequest(
    @NotBlank
    @Size(max = 150, message = "O título da receita deve ter no máximo 150 caracteres.")
    String title,

    @Size(max = 500, message = "A descrição da receita deve ter no máximo 500 caracteres.")
    String description,

    @NotBlank
    @Size(max = 5000, message = "Os ingredientes da receita devem ter no máximo 5000 caracteres.")
    String ingredients,

    @NotBlank
    @Size(max = 10000, message = "As instruções da receita devem ter no máximo 10000 caracteres.")
    String instructions,

    @NotNull(message = "O tempo de preparo é obrigatório.")
    @Min(value = 1, message = "O tempo de preparo deve ser maior que zero.")
    Integer preparationTimeMinutes,

    @NotNull(message = "A quantidade de porções é obrigatória")
    @Min(value = 1, message = "A quantidade de porções deve ser maior que zero")
    Integer servings,

    Boolean favorite
) {}
