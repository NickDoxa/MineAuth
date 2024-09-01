package net.oasisgames.spring.resources;

import lombok.RequiredArgsConstructor;
import net.oasisgames.spring.dto.ImageDto;
import net.oasisgames.spring.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ImageResource {

    private final ImageService imageService;

    @CrossOrigin
    @RequestMapping("/{image}")
    public ResponseEntity<ImageDto> getImage(@PathVariable String image) {
        return ResponseEntity.ok().body(imageService.getImageByName(image.toLowerCase()));
    }

    @RequestMapping("/add")
    public ResponseEntity<ImageDto> addImage(@RequestBody ImageDto image) {
        return ResponseEntity.ok().body(imageService.addNewImage(image));
    }

}
