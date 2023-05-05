package org.library.mapper;

import org.library.dto.request.CreateContentDto;
import org.library.dto.request.UpdateContentDto;
import org.library.dto.response.ContentDto;
import org.library.model.UserContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ContentMapper {

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    UserContent toModel(CreateContentDto dto);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    UserContent toModel(UpdateContentDto dto);

    ContentDto toDto(UserContent model);
}
