package com.store.project.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ErrorUtil {
    public static String returnErrorMessage(@NotNull BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        if (errors.isEmpty()) {
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error : allErrors) {
                errorMessage.append(error.getCode())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            return errorMessage.toString();
        } else {
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
        }
        return errorMessage.toString();
    }
}
