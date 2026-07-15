package org.comoequefaz.receitas.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime timestamp,

        Integer status,

        String error,

        String message,

        List<String> details,

        String path
) {

}
