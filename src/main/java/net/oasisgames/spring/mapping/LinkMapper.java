package net.oasisgames.spring.mapping;

import net.oasisgames.spring.dto.LinkDto;
import net.oasisgames.spring.entity.Link;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    @Mapping(target = "uuid")
    @Mapping(target = "link")
    LinkDto linkToLinkDto(Link link);

    @Mapping(target = "uuid")
    @Mapping(target = "link")
    Link linkDtoToLink(LinkDto linkDto);

}
