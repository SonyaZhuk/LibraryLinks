package org.library.mapper;

import org.library.dto.request.CreateContentDto;
import org.library.dto.request.UpdateContentDto;
import org.library.dto.response.ContentDto;
import org.library.model.UserContent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserContentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "contentTag.tag", source = "tag")
    UserContent toModel(CreateContentDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "contentTag.tag", source = "tag")
    UserContent toModel(UpdateContentDto dto);

    @Mapping(target = "tag", source = "contentTag.tag")
    ContentDto toDto(UserContent model);
}
