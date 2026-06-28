package org.comoequefaz.receitas.dto.request;

public class RecipeCreateRequest {
    public String title;
    public String description;
    public String ingredients;
    public String instructions;
    public Integer preparationTimeMinutes;
    public Integer servings;
    public boolean favorite;
}
