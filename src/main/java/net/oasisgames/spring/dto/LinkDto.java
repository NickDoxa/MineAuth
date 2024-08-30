package net.oasisgames.spring.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LinkDto {

    private UUID uuid;
    private String link;

}
