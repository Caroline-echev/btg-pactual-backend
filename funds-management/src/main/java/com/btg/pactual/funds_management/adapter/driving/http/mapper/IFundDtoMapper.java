package com.btg.pactual.funds_management.adapter.driving.http.mapper;

import com.btg.pactual.funds_management.adapter.driving.http.dto.response.FundResponse;
import com.btg.pactual.funds_management.domain.model.Fund;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.btg.pactual.funds_management.adapter.driving.http.util.ApiConstants.SPRING_COMPONENT_MODEL;

@Mapper(componentModel = SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IFundDtoMapper {

   FundResponse mapToDtoResponse(Fund fund);
}
