package net.oasisgames.spring.resources;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.dto.ImageDto;
import net.oasisgames.spring.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/images")
public class ImageResource {

    private final ImageService imageService;

    @RequestMapping("/{image}")
    public ResponseEntity<ImageDto> getImage(@PathVariable String image) {
        return ResponseEntity.ok().body(imageService.getImageByName(image.toLowerCase()));
    }

    @RequestMapping("/add")
    public ResponseEntity<ImageDto> addImage(@RequestBody ImageDto image) {
        return ResponseEntity.ok().body(imageService.addNewImage(image));
    }

}
