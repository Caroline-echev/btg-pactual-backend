package com.btg.pactual.funds_management.adapter.driven.mongo.mapper;

import com.btg.pactual.funds_management.adapter.driven.mongo.document.FundDocument;
import com.btg.pactual.funds_management.domain.model.Fund;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.btg.pactual.funds_management.adapter.driven.mongo.util.MapperConstants.SPRING_COMPONENT_MODEL;


@Mapper(componentModel = SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IFundDocumentMapper {
    Fund mapToModel(FundDocument fundDocument);
}
