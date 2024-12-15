package com.eprit.productservice.dto;

import java.math.BigDecimal;

public record ProductResponse(String id, String name, String description,
                              String skuCode, BigDecimal price) {
}