package org.library.mapper;

import org.library.dto.request.CreateContentTagDto;
import org.library.dto.response.ContentTagDto;
import org.library.model.ContentTag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ContentTagMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    ContentTag toModel(CreateContentTagDto dto);

    ContentTagDto toDto(ContentTag model);
}
