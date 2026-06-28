package org.comoequefaz.receitas.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RecipeTest {

    @Test
    void shouldCreateRecipeWithAllFields() {
        Recipe recipe = new Recipe();
        recipe.setTitle("Bolo de Chocolate");
        recipe.setDescription("Receita clássica");
        recipe.setIngredients("farinha, ovos, chocolate");
        recipe.setInstructions("Misture tudo e asse por 40 minutos");
        recipe.setPreparationTimeMinutes(50);
        recipe.setServings(8);
        recipe.setFavorite(true);

        assertEquals("Bolo de Chocolate", recipe.getTitle());
        assertEquals("Receita clássica", recipe.getDescription());
        assertEquals("farinha, ovos, chocolate", recipe.getIngredients());
        assertEquals("Misture tudo e asse por 40 minutos", recipe.getInstructions());
        assertEquals(50, recipe.getPreparationTimeMinutes());
        assertEquals(8, recipe.getServings());
        assertTrue(recipe.isFavorite());
    }

    @Test
    void shouldHaveFalseAsFavoriteByDefault() {
        Recipe recipe = new Recipe();
        assertFalse(recipe.isFavorite());
    }

    @Test
    void shouldAllowNullDescription() {
        Recipe recipe = new Recipe();
        recipe.setTitle("Macarrão");
        recipe.setIngredients("macarrão, sal");
        recipe.setInstructions("Cozinhe e sirva");
        recipe.setDescription(null);

        assertNull(recipe.getDescription());
    }

    @Test
    void shouldAllowNullOptionalFields() {
        Recipe recipe = new Recipe();
        assertNull(recipe.getPreparationTimeMinutes());
        assertNull(recipe.getServings());
    }
}
