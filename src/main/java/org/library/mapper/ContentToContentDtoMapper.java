package org.library.mapper;

import org.library.dto.CreateContentDto;
import org.library.dto.SearchContentDto;
import org.library.model.UserContent;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContentToContentDtoMapper {

    SearchContentDto toSearchDto(UserContent model);
    List<SearchContentDto> toDtos(List<UserContent> model);

    CreateContentDto toCreateDto(UserContent model);

    UserContent toModel(CreateContentDto model);
}
