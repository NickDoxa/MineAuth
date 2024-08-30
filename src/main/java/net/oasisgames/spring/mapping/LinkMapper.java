package net.oasisgames.spring.mapping;

import net.oasisgames.spring.dto.LinkDto;
import net.oasisgames.spring.entity.LinkGen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    @Mapping(target = "uuid")
    @Mapping(target = "link")
    LinkDto linkGenToLinkDto(LinkGen link);

    @Mapping(target = "uuid")
    @Mapping(target = "link")
    LinkGen linkDtoToLinkGen(LinkDto link);

}
