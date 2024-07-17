package com.fraisdirect.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AttributeRequestDTO {
    @NotNull
    @NotEmpty
    private String attributeName;
    @NotNull
    @NotEmpty
    private String attributeDescription;
}
