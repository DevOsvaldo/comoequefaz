package org.comoequefaz.receitas.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import org.comoequefaz.receitas.shared.entity.BaseEntity;

@Entity
@Data
@Table(name = "recipes")
public class Recipe extends BaseEntity {

    @Column(nullable = false, length = 150)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 5000)
    private String ingredients;

    @Column(nullable = false, length = 10000)
    private String instructions;

    private Integer preparationTimeMinutes;

    private Integer servings;

    private boolean favorite;


}
