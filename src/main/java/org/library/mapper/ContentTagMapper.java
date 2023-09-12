package org.library.mapper;

import org.library.dto.response.ContentTagDto;
import org.library.model.ContentTag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ContentTagMapper {

    ContentTagDto toDto(ContentTag model);
}
