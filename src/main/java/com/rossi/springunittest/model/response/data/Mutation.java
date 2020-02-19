package com.rossi.springunittest.model.response.data;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Mutation {
    private String date;
    private String mutationType;
    private String description;
    private BigDecimal amount;
    private BigDecimal lastMutationBalance;
}
