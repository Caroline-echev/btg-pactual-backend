package com.btg.pactual.funds_management.adapter.driving.http.mapper;

import com.btg.pactual.funds_management.adapter.driving.http.dto.response.SubscriptionResponse;
import com.btg.pactual.funds_management.domain.model.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import static com.btg.pactual.funds_management.adapter.driving.http.util.ApiConstants.SPRING_COMPONENT_MODEL;

@Mapper(componentModel = SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface ISubscriptionDtoMapper {

    SubscriptionResponse mapToDtoResponse(Subscription subscription);
}
