package net.oasisgames.spring.services;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.dto.ImageDto;
import net.oasisgames.spring.entity.Images;
import net.oasisgames.spring.mapping.ImageMapper;
import net.oasisgames.spring.repositories.ImageRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageDto getImageByName(final String name) {
        Images image = imageRepository.findById(name).orElse(new Images());
        return imageMapper.imageToImageDto(image);
    }

    public ImageDto addNewImage(ImageDto imageDto) {
        Images image = imageMapper.imageDtoToImage(imageDto);
        imageRepository.save(image);
        return imageMapper.imageToImageDto(imageRepository.findById(imageDto.getId()).orElseThrow());
    }

}
