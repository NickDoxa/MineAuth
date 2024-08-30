package net.oasisgames.spring.mapping;

import net.oasisgames.spring.dto.ImageDto;
import net.oasisgames.spring.entity.Images;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(target = "id")
    @Mapping(target = "url")
    Images imageDtoToImage(ImageDto imageDto);

    @Mapping(target = "id")
    @Mapping(target = "url")
    ImageDto imageToImageDto(Images image);

}
