package org.comoequefaz.receitas.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.comoequefaz.receitas.entity.Recipe;

import java.util.List;

@ApplicationScoped
public class RecipeRepository implements PanacheRepository<Recipe> {

    public List<Recipe> findByTitle(String title){
        return list(
                "LOWER(title) LIKE LOWER (?1)",
                "%"+ title.trim() +"%"
        );
    }
    public boolean existsByTitleAndIdNot(String title, Long id) {
        return count(
                "LOWER(title) = LOWER(?1) AND id <> ?2",
                title.trim(),
                id
        ) > 0;
    }

    public boolean existsByTitle(String title) {
        return count(
                "LOWER(title) = LOWER(?1)",
                title.trim()
        ) > 0;
    }
}
