package com.btg.pactual.funds_management.domain.api.usecase;


import com.btg.pactual.funds_management.domain.api.IFundServicePort;
import com.btg.pactual.funds_management.domain.model.Fund;
import com.btg.pactual.funds_management.domain.spi.IFundPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
public class FundUseCase implements IFundServicePort {
    private final IFundPersistencePort fundPersistencePort;

    @Override
    public List<Fund> getFunds(String category, boolean orderByname, boolean isAsc) {
        List<Fund> funds = category != null
                ? new ArrayList<>(fundPersistencePort.findByCategory(category))
                : new ArrayList<>(fundPersistencePort.findAll());

        return orderByname ? filterByName(funds, isAsc) : filterByAmount(funds, isAsc);
    }

    private List<Fund> filterByName(List<Fund> funds, boolean isAsc) {
        if (isAsc) {
            funds.sort(Comparator.comparing(Fund::getName));
        } else {
            funds.sort(Comparator.comparing(Fund::getName).reversed());
        }
        return funds;
    }

    private List<Fund> filterByAmount(List<Fund> funds, boolean isAsc) {
        if (isAsc) {
            funds.sort(Comparator.comparing(Fund::getMinimumAmount));
        } else {
            funds.sort(Comparator.comparing(Fund::getMinimumAmount).reversed());
        }
        return funds;
    }
}