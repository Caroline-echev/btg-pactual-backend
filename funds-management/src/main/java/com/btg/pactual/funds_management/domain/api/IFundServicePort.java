package com.btg.pactual.funds_management.domain.api;

import com.btg.pactual.funds_management.domain.model.Fund;

import java.util.List;


public interface IFundServicePort {
    List<Fund> getFunds(String category, boolean orderByName, boolean isAsc);
}
