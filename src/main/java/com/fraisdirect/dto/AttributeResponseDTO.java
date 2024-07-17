package com.fraisdirect.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AttributeResponseDTO {
    @NotNull
    private Long attributeID;
    private String attributeName;
    private String attributeDescription;
}
