package com.rossi.springunittest.model.response.data;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class MutationResponse {
    BigDecimal initialBalance;
    BigDecimal lastBalance;
    List<Mutation> mutations;
}
