package com.fraisdirect.dto.price;

import com.fraisdirect.entity.AROUND;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class WightBasedPriceResponseDTO {
    private long wightBasedPriceID;
    private String label;
    private float wight;
    private float price;
    private AROUND around;
}
