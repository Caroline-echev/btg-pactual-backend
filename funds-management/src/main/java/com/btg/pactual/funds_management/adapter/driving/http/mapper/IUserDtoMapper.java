package com.btg.pactual.funds_management.adapter.driving.http.mapper;

import com.btg.pactual.funds_management.adapter.driving.http.dto.response.UserResponse;
import com.btg.pactual.funds_management.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.btg.pactual.funds_management.adapter.driving.http.util.ApiConstants.SPRING_COMPONENT_MODEL;

@Mapper(componentModel = SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserDtoMapper {
    UserResponse mapToDtoResponse(User user);
}
