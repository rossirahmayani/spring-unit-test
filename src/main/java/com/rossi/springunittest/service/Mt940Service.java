package com.rossi.springunittest.service;

import com.prowidesoftware.swift.model.field.Field61;
import com.prowidesoftware.swift.model.field.Field86;
import com.prowidesoftware.swift.model.mt.mt9xx.MT940;
import com.rossi.springunittest.model.response.DataResponse;
import com.rossi.springunittest.model.response.data.Mutation;
import com.rossi.springunittest.model.response.data.MutationResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class Mt940Service {

    public DataResponse parseMt940 (String string) {
        log.info("Start parsing");
        MT940 mt940 = MT940.parse(string);
        log.info(mt940.toJson());

        log.info("Get initial balance");
        BigDecimal initialBalance = mt940.getField60F().amount();

        log.info("Create mutations");
        BigDecimal prevBalance = initialBalance;
        List<Mutation> mutations = new ArrayList<>();
        int mutationSize = mt940.getField61().size();
        for (int idx = 0; idx < mutationSize; idx++){
            Mutation mutation = createMutation(prevBalance, mt940.getField86().get(idx), mt940.getField61().get(idx));
            prevBalance = mutation.getLastMutationBalance();
            mutations.add(mutation);
        }

        log.info("Get last balance");
        BigDecimal lastBalance = Optional.of(mt940.getField62F().amount())
                .filter(s -> s.equals(mutations.get(mutationSize-1).getLastMutationBalance()))
                .map(b -> mutations.get(mutationSize-1).getLastMutationBalance()).orElse(null);
        mutations.forEach(mutation -> log.info(mutation.toString()));

        log.info("Create response");
        MutationResponse data = MutationResponse.builder()
                .initialBalance(initialBalance)
                .lastBalance(lastBalance)
                .mutations(mutations).build();
        DataResponse response = new DataResponse();
        response.setResponseCode("0000");
        response.setResponseMsg("Success");
        response.setResponseData(data);
        return response;
    }

    private Mutation createMutation (BigDecimal prevBalance, Field86 field86, Field61 field61) {
        BigDecimal amount = getAmount(field61.getDCMark(), field61.amount());
        return Mutation.builder()
                .mutationType(field61.getDCMark())
                .date(field61.getEntryDate())
                .amount(amount)
                .description(field86.getNarrative())
                .lastMutationBalance(prevBalance.add(amount))
                .build();
    }

    private BigDecimal getAmount (String mutationType, BigDecimal rawAmount) {
        return Optional.of(mutationType)
                .filter(m -> m.equalsIgnoreCase("D"))
                .map(b -> rawAmount.negate())
                .orElse(rawAmount);
    }


}
