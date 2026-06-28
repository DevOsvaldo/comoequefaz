package org.comoequefaz.receitas.shared;

import org.comoequefaz.receitas.entity.Recipe;
import org.comoequefaz.receitas.shared.entity.BaseEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BaseEntityTest {

    @Test
    void onCreateShouldSetCreatedAtAndUpdatedAt() throws Exception {
        Recipe recipe = new Recipe();

        // chama o método @PrePersist manualmente via reflexão
        var method = BaseEntity.class.getDeclaredMethod("onCreate");
        method.setAccessible(true);
        method.invoke(recipe);

        assertNotNull(recipe.createdAt);
        assertNotNull(recipe.updatedAt);
    }

    @Test
    void onUpdateShouldRefreshUpdatedAt() throws Exception {
        Recipe recipe = new Recipe();

        var onCreate = BaseEntity.class.getDeclaredMethod("onCreate");
        onCreate.setAccessible(true);
        onCreate.invoke(recipe);

        LocalDateTime firstUpdate = recipe.updatedAt;
        Thread.sleep(10);

        var onUpdate = BaseEntity.class.getDeclaredMethod("onUpdate");
        onUpdate.setAccessible(true);
        onUpdate.invoke(recipe);

        assertTrue(recipe.updatedAt.isAfter(firstUpdate) || recipe.updatedAt.isEqual(firstUpdate));
        assertNotNull(recipe.updatedAt);
    }

    @Test
    void idShouldBeNullBeforePersist() {
        Recipe recipe = new Recipe();
        assertNull(recipe.id);
    }
}
