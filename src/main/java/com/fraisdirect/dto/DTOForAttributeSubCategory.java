package com.fraisdirect.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DTOForAttributeSubCategory {
    @NotNull
    private Long subcategoryID;
    @NotNull
    private Long[] longArrays;
}
