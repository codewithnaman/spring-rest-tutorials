package com.codewithnaman.dto.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FieldValidationErrorResponse {
    private String fieldName;

    private String errorMessage;
}
