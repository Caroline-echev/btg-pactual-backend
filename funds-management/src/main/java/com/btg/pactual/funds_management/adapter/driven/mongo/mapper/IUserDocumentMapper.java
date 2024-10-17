package com.btg.pactual.funds_management.adapter.driven.mongo.mapper;

import com.btg.pactual.funds_management.adapter.driven.mongo.document.UserDocument;
import com.btg.pactual.funds_management.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.btg.pactual.funds_management.adapter.driven.mongo.util.MapperConstants.SPRING_COMPONENT_MODEL;

@Mapper(componentModel = SPRING_COMPONENT_MODEL,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserDocumentMapper {
    User mapToModel(UserDocument userDocument);

    UserDocument mapToDocument(User user);
}
