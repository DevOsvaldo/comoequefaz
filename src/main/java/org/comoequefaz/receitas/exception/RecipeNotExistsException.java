package org.comoequefaz.receitas.exception;

public class RecipeNotExistsException extends RuntimeException {
    public RecipeNotExistsException(String message) {
        super(message);
    }
}
